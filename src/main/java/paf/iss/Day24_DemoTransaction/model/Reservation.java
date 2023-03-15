package paf.iss.Day24_DemoTransaction.model;

import java.sql.Date;

public class Reservation {
    
    private Integer id;

    private Date reservationDate;

    private String fullName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Reservation() {
    }

    public Reservation(Integer id, Date reservationDate, String fullName) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.fullName = fullName;
    }

}
