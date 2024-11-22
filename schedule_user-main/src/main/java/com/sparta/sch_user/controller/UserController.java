package com.sparta.sch_user.controller;

import com.sparta.sch_user.dto.LoginRequestDto;
import com.sparta.sch_user.dto.UserRequestDto;
import com.sparta.sch_user.dto.UserResponseDto;
import com.sparta.sch_user.entity.User;
import com.sparta.sch_user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: 이 클래스가 RESTful 웹 컨트롤러임을 나타냅니다.
// @RequestMapping("/users"): "/users" 경로로 시작하는 요청들을 처리합니다.
// @RequiredArgsConstructor: final 필드를 포함하는 생성자를 자동으로 생성합니다.
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    // UserService를 주입받아 사용자 관련 비즈니스 로직을 처리합니다.
    private final UserService userService;

    // 모든 사용자 목록을 조회하는 메서드
    // @GetMapping: HTTP GET 요청을 처리하며, "/users" URL로 요청이 들어올 때 실행됩니다.
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        // 서비스에서 모든 사용자 데이터를 조회한 후 HTTP 응답으로 반환합니다.
        return ResponseEntity.ok().body(userService.findAll());
    }

    // 새로운 사용자를 생성하는 메서드
    // @PostMapping: HTTP POST 요청을 처리하며, "/users" URL로 요청이 들어올 때 실행됩니다.
    // @RequestBody: HTTP 요청 본문에 담긴 JSON 데이터를 Java 객체로 변환합니다.
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        // 서비스에서 사용자 데이터를 생성하고, 생성된 데이터를 HTTP 응답으로 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    // 특정 사용자를 삭제하는 메서드
    // @DeleteMapping: HTTP DELETE 요청을 처리하며, "/users/{id}" URL로 요청이 들어올 때 실행됩니다.
    // @PathVariable: URL 경로에 포함된 변수를 메서드의 파라미터로 매핑합니다.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // 서비스에서 ID를 기준으로 사용자를 삭제합니다.
        userService.deleteUser(id);
        // 삭제 성공 메시지를 반환합니다.
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }

    // 사용자가 로그인하는 메서드
    // @PostMapping: HTTP POST 요청을 처리하며, "/users/login" URL로 요청이 들어올 때 실행됩니다.
    // HttpServletRequest: 요청 객체를 통해 세션을 생성하거나 관리할 수 있습니다.
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        // 서비스에서 로그인 요청 데이터를 검증하고, 로그인에 성공한 사용자 객체를 반환받습니다.
        User loginedUser = userService.loginUser(loginRequestDto);
        // HttpSession을 생성하고 사용자 ID를 세션에 저장합니다.
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_KEY", loginedUser.getId());

        // 로그인 성공 메시지를 반환합니다.
        return ResponseEntity.ok().body("정상적으로 로그인되었습니다.");
    }

    // 사용자가 로그아웃하는 메서드
    // @PostMapping: HTTP POST 요청을 처리하며, "/users/logout" URL로 요청이 들어올 때 실행됩니다.
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 현재 요청에서 세션 객체를 가져옵니다. 세션이 없으면 null을 반환합니다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션을 무효화하여 로그아웃 처리합니다.
            session.invalidate();
        }
        // 로그아웃 성공 메시지를 반환합니다.
        return ResponseEntity.ok("로그아웃 성공");
    }
}
