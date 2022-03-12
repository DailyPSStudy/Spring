package com.tc.springboot.Dto;
import com.tc.springboot.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String tc_id;
    private String tc_password;

    @Builder
    public UserLoginRequestDto(User entity) {
        this.tc_id = entity.getTc_id();
        this.tc_password = entity.getTc_password();
    }
}
