package zerobase.weather.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 프로젝트에 있는 모든 controller 의 예외를 잡아온다
public class GlobalExceptionHandler {

    /*
    @RestControllerAdvice => 전역의 예외 처리
    @ExceptionHandler => 해당 컨트롤러 예외만 처리

    지금의 구조는 @RestControllerAdvice 가 프로젝트에 있는 모든 controller 의 예외를
    GlobalExceptionHandler 클래스로 잡아오면,
    모인 전역 예외를 @ExceptionHandler 가 처리해주는것
     */

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   // 에러발생시 반환할 값 500
    @ExceptionHandler(Exception.class)   // 모든 exception 을 다루겠다
    public Exception handleAllException() {
        System.out.println("error from GlobalExceptionHandler");
//        log.error("error from GlobalExceptionHandler");   // 실무에선 이런식으로 한다
        return new Exception();
    }

}
