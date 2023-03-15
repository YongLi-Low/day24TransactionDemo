package paf.iss.Day24_DemoTransaction.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import paf.iss.Day24_DemoTransaction.model.Reservation;

@Repository
public class ReservationRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_SQL = "select * from reservation;";
    private final String INSERT_SQL = "insert into reservation (reservation_date, full_name) values (?, ?);";
    
    public List<Reservation> findAll() {
        return jdbcTemplate.query(SELECT_SQL, BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    public int createReservation(Reservation reservation) {

        // Create GeneratedKeyHolder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"id"});

                ps.setDate(1, reservation.getReservationDate());
                ps.setString(2, reservation.getFullName());
                return ps;
            }
        };

        jdbcTemplate.update(psc, generatedKeyHolder);

        // Get auto incremented ID and cast it to an integer
        Integer returnedId = generatedKeyHolder.getKey().intValue();

        return returnedId;
    }
}
