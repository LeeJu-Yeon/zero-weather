package zerobase.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

@Repository
public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {
    // JpaRepository 를 사용할것이다, <Memo 클래스 연결, PK 타입은 Integer>

    // 기본적인 메소드는 JpaRepository 에 이미 정의되어있어서 바로 사용가능
}
