package com.sparta.sch_user.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

// 로그인 여부를 확인하는 필터 클래스
@Slf4j // 로깅을 위한 Lombok 애노테이션
public class LoginFilter implements Filter {

    // 로그인 없이 접근 가능한 경로(화이트리스트)
    private static final String[] WHITE_LIST = {"/users", "/users/login"};

    @Override
    public void doFilter(
            ServletRequest request, // 클라이언트의 요청
            ServletResponse response, // 서버의 응답
            FilterChain chain // 다음 필터로 요청 전달
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request; // HTTP 요청으로 캐스팅
        String requestURI = httpRequest.getRequestURI(); // 요청된 URI 가져오기

        log.info("로그인 필터 로직 실행");

        // 화이트리스트에 포함되지 않은 URI의 경우 로그인 확인
        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false); // 세션이 존재하지 않을 경우 null 반환
            if (session == null || session.getAttribute("SESSION_KEY") == null) {
                // 세션이 없거나 로그인 정보가 없을 경우 예외 발생
                throw new RuntimeException("로그인 해주세요.");
            }

            log.info("로그인된 사용자 요청: {}", requestURI); // 로그인된 사용자 요청 로깅
        }

        // 다음 필터로 요청 전달
        chain.doFilter(request, response);
    }

    // 요청 URI가 화이트리스트에 포함되어 있는지 확인하는 메서드
    private boolean isWhiteList(String requestURI) {
        // 화이트리스트와 요청 URI를 비교하여 매칭 여부 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
