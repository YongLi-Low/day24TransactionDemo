package paf.iss.Day24_DemoTransaction.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import paf.iss.Day24_DemoTransaction.model.Book;
import paf.iss.Day24_DemoTransaction.model.Reservation;
import paf.iss.Day24_DemoTransaction.model.ReservationDetails;
import paf.iss.Day24_DemoTransaction.repository.BookRepository;
import paf.iss.Day24_DemoTransaction.repository.ReservationDetailsRepository;
import paf.iss.Day24_DemoTransaction.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private ReservationDetailsRepository resvDetailsRepo;

    @Autowired
    private ReservationRepository resvRepo;

    @Transactional // (propagation = Propagation.REQUIRED)
    public Boolean reserveBooks(List<Book> books, String reservePersonName) {

        Boolean bReservationCompleted = false;

        // Check for book availability by quantity
        Boolean bBooksAvailable = true;
        List<Book> libBooks = bookRepo.findAll();
        for (Book book: books) {

            // return a List with a single value, which is the Id of the book if they match
            List<Book> filteredBook = libBooks.stream().filter(b -> b.getId().equals(book.getId())).collect(Collectors.toList());

            if (!filteredBook.isEmpty()) {
                if (filteredBook.get(0).getQuantity() == 0) {
                    bBooksAvailable = false;
                }
                else {
                    // Update the quantity
                    // If books available, minus quantity from book (requires transaction)
                    bookRepo.update(book.getId());
                }
            }
            else {
                bBooksAvailable = false;
                break;
            }
        }

        // Create the reservation record (requires transaction)
        Reservation reservation = new Reservation();
        reservation.setFullName(reservePersonName);
        reservation.setReservationDate(Date.valueOf(LocalDate.now()));
        Integer reservationId = resvRepo.createReservation(reservation);

        // Create the reservation details record (requires transaction)
        for (Book book: books) {
            ReservationDetails reservationDetail = new ReservationDetails();
            reservationDetail.setBookId(book.getId());
            reservationDetail.setReservationId(reservationId);

            resvDetailsRepo.createReservationDetails(reservationDetail);
        }
        
        bReservationCompleted = true;

        return bReservationCompleted;
    }
}
