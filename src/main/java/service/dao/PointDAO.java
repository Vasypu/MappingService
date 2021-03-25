package service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import service.models.Point;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PointDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Point> index() {
        return jdbcTemplate.query("SELECT * FROM point", new BeanPropertyRowMapper<>(Point.class));
    }

    public Point show(int id) {
        return jdbcTemplate.query("SELECT * FROM point WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Point.class))
                .stream().findAny().orElse(null);
    }

    public void save(Point point) {
        jdbcTemplate.update("INSERT INTO point VALUES(1, ?, ?)", point.getCoordinates(), point.getDescription());
    }

    public void update(int id, Point updatedPoint) {
        jdbcTemplate.update("UPDATE Point SET coordinates=?, description=? WHERE id=?", updatedPoint.getCoordinates(),
                updatedPoint.getDescription(), id);
    }

    public void delete(int id) { jdbcTemplate.update("DELETE FROM Point WHERE id=?", id); }
}