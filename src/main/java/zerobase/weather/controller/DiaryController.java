package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

//@Controller   // 클라이언트 - 컨트롤러 - 서비스 - 리포지토리 - DB
@RestController   // 컨트롤러 기능 + 상태코드 기능
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 클라이언트가 "/create/diary" 주소로 post 요청 보내면
    // createDiary 메소드는 파라미터(형식지정된 날짜), 바디(일기내용) 받아서 처리
    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "클릭시 보여지는 설명")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "일기 날짜", example = "2023-06-01")
                     LocalDate date,
                     @ApiParam(value = "일기 내용", example = "일기 내용")
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2023-06-01")
                          LocalDate date) {
//        if (date.isAfter(LocalDate.ofYearDay(2024, 1))) {
//            throw new InvalidDateException();
//        }

        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 첫번째날", example = "2023-06-01")
                            LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막날", example = "2023-07-01")
                            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 첫번째 일기 글만 새로운 텍스트로 수정합니다")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "수정할 날짜", example = "2023-06-01")
                     LocalDate date,
                     @ApiParam(value = "새로운 일기 내용", example = "새로운 일기 내용")
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "삭제할 날짜", example = "2023-06-01")
                     LocalDate date) {
        diaryService.deleteDiary(date);
    }

}
