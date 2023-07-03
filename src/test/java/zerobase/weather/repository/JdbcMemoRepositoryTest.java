package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest   // 이건 테스트야
@Transactional    // 테스트하고 롤백 -> 원래 DB 상태 보존
public class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        Memo newMemo = new Memo(2, "insertMemoTest");

        // when
        jdbcMemoRepository.save(newMemo);

        // then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals("insertMemoTest", result.get().getText());
    }

    @Test
    void findAllMemoTest() {
        // given

        // when
        List<Memo> memoList = jdbcMemoRepository.findAll();

        // then
        System.out.println(memoList);
        assertNotNull(memoList);
    }

}
