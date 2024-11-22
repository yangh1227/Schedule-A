package com.sparta.sch_user.service;

import com.sparta.sch_user.dto.LoginRequestDto;
import com.sparta.sch_user.dto.UserRequestDto;
import com.sparta.sch_user.dto.UserResponseDto;
import com.sparta.sch_user.entity.User;
import com.sparta.sch_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

// 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service // Spring에서 서비스 컴포넌트로 인식
@RequiredArgsConstructor // final 필드를 포함하는 생성자를 자동으로 생성
public class UserService {

    private final UserRepository userRepository; // 사용자 데이터를 관리하는 리포지토리

    // 모든 사용자 데이터를 조회하는 메서드
    public List<UserResponseDto> findAll() {
        // 데이터베이스에서 모든 사용자 엔티티를 조회
        List<User> users = userRepository.findAll();

        // 사용자 데이터를 생성 날짜 기준 내림차순으로 정렬 후 DTO 리스트로 변환하여 반환
        return users
                .stream()
                .sorted(Comparator.comparing(User::getCreatedAt).reversed())
                .map(UserResponseDto::toDto)
                .toList();
    }

    // 새로운 사용자를 생성하는 메서드
    @Transactional // 트랜잭션 관리를 통해 데이터베이스 작업의 일관성 유지
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // 요청받은 데이터를 엔티티로 변환 후 저장
        User savedUser = userRepository.save(userRequestDto.toEntity());

        // 저장된 데이터를 DTO로 변환하여 반환
        return UserResponseDto.toDto(savedUser);
    }

    // 특정 ID에 해당하는 사용자를 삭제하는 메서드
    @Transactional
    public void deleteUser(Long id) {
        // 사용자 존재 여부를 확인 (예외 처리를 위해)
        findUserById(id);

        // 사용자 데이터 삭제
        userRepository.deleteById(id);
    }

    // 특정 ID에 해당하는 사용자 엔티티를 조회하는 메서드
    public User findUserById(Long id) {
        // 사용자 엔티티를 조회, 없을 경우 예외를 발생시킴
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    // 사용자의 로그인 요청을 처리하는 메서드
    public User loginUser(LoginRequestDto loginRequestDto) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(loginRequestDto.getEmail());

        // 사용자가 존재하지 않거나 비밀번호가 일치하지 않을 경우 예외 발생
        if (user == null || !Objects.equals(user.getPassword(), loginRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자 이름 혹은 잘못된 비밀번호");
        }

        // 로그인에 성공한 사용자 반환
        return user;
    }
}
