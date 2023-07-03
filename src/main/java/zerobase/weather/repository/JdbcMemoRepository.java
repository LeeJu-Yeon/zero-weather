package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository   // 스프링부트에게 이 클래스가 Repository 라고 알려주는것
public class JdbcMemoRepository {   // 스프링부트와 memo 테이블을 jdbc 를 사용하여 연동해줄 객체

    private final JdbcTemplate jdbcTemplate;

    @Autowired   // application.properties 에 입력한 datasource 를 가져옴
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Memo save(Memo memo) {
        String sql = "insert into memo values (?, ?)";   // Jdbc 는 sql 문을 직접 작성하여야 한다
        jdbcTemplate.update(sql, memo.getId(), memo.getText());   // insert 는 update
        return memo;   // 저장한 memo 반환
    }

    public List<Memo> findAll() {
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());   // select 는 query
        // jdbcTemplate 이 mySql 에 가서 sql 던지고
        // 반환된 ResultSet 을 memoRowMapper 메소드를 사용하여 Memo 형태로 가져옴
    }

    public Optional<Memo> findById(int id) {
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
        // 스프링은 id 로 조회한 값이 한개인지 몰라 빨간줄 -> .stream().findFirst()
        // 혹시 null 일 경우, Optional 로 랩핑해서 null 값 처리하기 쉽게 만들어줌
    }

    private RowMapper<Memo> memoRowMapper() {
        // ResultSet
        // {id = 1, text = 'this is memo~'}
        // rs 를 Memo 로 맵핑
        return (rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text")
        );
    }

}
