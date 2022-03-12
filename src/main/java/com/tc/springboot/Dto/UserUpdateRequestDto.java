package com.tc.springboot.Dto;
import com.tc.springboot.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String tc_password;
    private String tc_email;

    @Builder
    public UserUpdateRequestDto(User entity) {
        this.tc_password = entity.getTc_password();
        this.tc_email = entity.getTc_email();
    }
}
