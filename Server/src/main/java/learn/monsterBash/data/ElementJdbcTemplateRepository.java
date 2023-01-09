package learn.monsterBash.data;

import learn.monsterBash.data.mappers.ElementMapper;
import learn.monsterBash.models.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ElementJdbcTemplateRepository implements ElementRepo {

    private final JdbcTemplate jdbcTemplate;

    public ElementJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Element> findAll() {
        final String sql = "select element_id, element_name "
                + "from element;";
        return jdbcTemplate.query(sql, new ElementMapper());
    }

    @Override
    @Transactional
    public Element findById(int elementId) {

        final String sql = "select element_id, element_name "
                + "from element "
                + "where element_id = ?;";

        Element element = jdbcTemplate.query(sql, new ElementMapper(), elementId).stream()
                .findFirst().orElse(null);

        return element;
    }

    @Override
    public Element add(Element element) {


        final String sql = """
                insert into element (element_name,element_id)
                 values (?,?);
                 """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, element.getElementName());
            ps.setInt(2, element.getElementId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        element.setElementId(keyHolder.getKey().intValue());
        return element;
    }

    @Override
    public boolean update(Element element) {

        final String sql = "update element set "
                + "element_name = ?"
                + "where element_id = ?;";

        return jdbcTemplate.update(sql,
                element.getElementName(),
                element.getElementId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int elementId) {
        return jdbcTemplate.update("delete from element where element_id = ?;", elementId) > 0;
    }
}
