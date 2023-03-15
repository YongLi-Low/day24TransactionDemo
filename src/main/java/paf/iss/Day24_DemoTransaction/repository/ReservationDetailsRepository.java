package paf.iss.Day24_DemoTransaction.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import paf.iss.Day24_DemoTransaction.model.ReservationDetails;

@Repository
public class ReservationDetailsRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "insert into reservationdetails (book_id, reservation_id) values (?, ?);";

    public Integer createReservationDetails(ReservationDetails resvDetails) {
        
        // Create GeneratedKeyHolder object to obtain the auto generated ID of the new book
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setInt(1, resvDetails.getBookId());
                ps.setInt(2, resvDetails.getReservationId());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatedKeyHolder);

        // Get auto incremented ID
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }

}
