package com.tc.springboot.Dto;
import com.tc.springboot.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private int tc_num;
    private String tc_id;
    private String tc_password;
    private String tc_email;

    public UserResponseDto(User entity) {
        this.tc_num = entity.getTc_num();
        this.tc_id = entity.getTc_id();
        this.tc_password = entity.getTc_password();
        this.tc_email = entity.getTc_email();
    }
}
