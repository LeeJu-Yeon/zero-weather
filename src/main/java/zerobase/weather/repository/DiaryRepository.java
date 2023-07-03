package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;

import java.time.LocalDate;
import java.util.List;

@Repository   // 클라이언트 - 컨트롤러 - 서비스 - 리포지토리 - DB
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    // 기본적인 메소드는 JpaRepository 에 구현되어있음

    // JPA 는 메소드 이름을 분석하여 적절한 SQL 쿼리를 자동으로 생성하고 실행
    List<Diary> findAllByDate(LocalDate date);

    List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    Diary getFirstByDate(LocalDate date);

    @Transactional   // 이걸 달아줘야 DB 에서 delete 가 된다
    void deleteAllByDate(LocalDate date);

}
