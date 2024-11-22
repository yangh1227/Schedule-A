package com.sparta.sch_user.controller;

import com.sparta.sch_user.dto.ScheduleRequestDto;
import com.sparta.sch_user.dto.ScheduleResponseDto;
import com.sparta.sch_user.dto.ScheduleUpdateRequestDto;
import com.sparta.sch_user.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: 이 클래스가 Spring MVC의 RESTful 웹 컨트롤러임을 나타냅니다.
// @RequiredArgsConstructor: final로 선언된 필드를 생성자로 초기화하는 롬복 애노테이션입니다.
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    // 스케줄 관련 로직을 처리하는 서비스 클래스의 의존성을 주입받습니다.
    private final ScheduleService scheduleService;

    // 모든 스케줄 목록을 조회하는 메서드
    // @GetMapping: HTTP GET 요청을 처리하며, "/schedules" URL로 요청이 들어올 때 실행됩니다.
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        // 서비스에서 모든 스케줄 데이터를 조회하고 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    // 특정 ID에 해당하는 스케줄을 조회하는 메서드
    // @PathVariable: URL 경로에 포함된 변수를 메서드의 파라미터로 바인딩합니다.
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findOne(@PathVariable Long id) {
        // 서비스에서 ID를 기준으로 스케줄 데이터를 조회하고 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok().body(scheduleService.findById(id));
    }

    // 새로운 스케줄을 생성하는 메서드
    // @PostMapping: HTTP POST 요청을 처리하며, "/schedules" URL로 요청이 들어올 때 실행됩니다.
    // @RequestBody: HTTP 요청 본문에 담긴 데이터를 객체로 변환하여 매핑합니다.
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        // 서비스에서 스케줄을 생성하고, 생성된 데이터를 HTTP 응답으로 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(scheduleRequestDto));
    }

    // 특정 ID에 해당하는 스케줄을 삭제하는 메서드
    // @DeleteMapping: HTTP DELETE 요청을 처리하며, "/schedules/{id}" URL로 요청이 들어올 때 실행됩니다.
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        // 서비스에서 ID를 기준으로 스케줄을 삭제합니다.
        scheduleService.deleteSchedule(id);
        // 삭제 성공 메시지를 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }

    // 특정 ID에 해당하는 스케줄을 업데이트하는 메서드
    // @PatchMapping: HTTP PATCH 요청을 처리하며, "/schedules/{id}" URL로 요청이 들어올 때 실행됩니다.
    // @RequestBody: HTTP 요청 본문에 담긴 데이터를 객체로 변환하여 매핑합니다.
    @PatchMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody ScheduleUpdateRequestDto scheduleRequestDto,
                                                              @PathVariable Long id) {
        // 서비스에서 스케줄을 업데이트하고, 업데이트된 데이터를 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok().body(scheduleService.update(id, scheduleRequestDto));
    }
}
