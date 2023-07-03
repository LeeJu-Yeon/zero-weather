package zerobase.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "memo")   // 이 클래스를 memo 테이블과 연결한다 => JPA 사용시 필수, JDBC 는 sql 을 직접 작성해서 필요x
public class Memo {   // DB 에 memo 테이블이 있고, id 와 text 컬럼이 있다

    @Id   // JPA Entity 지정하면 PK 도 명시해줘야
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 키생성은 DB 에 맡기겠다
    private int id;

    private String text;

}
