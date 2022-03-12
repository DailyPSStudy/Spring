package com.tc.springboot.Service;

import com.tc.springboot.Dto.UserLoginRequestDto;
import com.tc.springboot.Dto.UserResponseDto;
import com.tc.springboot.Dto.UserSaveRequestDto;
import com.tc.springboot.Dto.UserUpdateRequestDto;
import com.tc.springboot.entity.User;
import com.tc.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findtc_passwordBytc_id(username);
    }
    @Transactional
    public int save (UserSaveRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getTc_num();
    }

    public int update(String tc_id, UserUpdateRequestDto requestDto){
        User entity = userRepository.findUserBytc_id(tc_id);
        entity.update(requestDto.getTc_password(), requestDto.getTc_email());
        return entity.getTc_num();
    }
    public UserResponseDto findById(String tc_id){
        User entity = userRepository.findUserBytc_id(tc_id);
        return new UserResponseDto(entity);
    }

    public User findByIdforjwt(String tc_id){
        User entity = userRepository.findUserBytc_id(tc_id);
        return entity;
    }


    public User findPasswordById (UserLoginRequestDto requestDto){
        User entity = userRepository.findtc_passwordBytc_id(requestDto.getTc_id());

        if(entity==null){
            System.out.println("해당 계정이 아에 존재하지않음");
            return null;
        }
        else if(!entity.getTc_password().equals(requestDto.getTc_password())){
            System.out.println(entity.getTc_id()+ "의 검색한 비밀번호 : " + entity.getTc_password());
            System.out.println("입력받은 비밀번호 : "+requestDto.getTc_password());
            return null;
        }

        return entity;
    }


}
