package com.tc.springboot.Dto;
import com.tc.springboot.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String tc_id;
    private String tc_password;
    private String tc_email;
    private List<String> roles = new ArrayList<>();

    @Builder
    public UserSaveRequestDto(User entity) {
        this.tc_id = entity.getTc_id();
        this.tc_password = entity.getTc_password();
        this.tc_email = entity.getTc_email();
    }

    public User toEntity(){
        return User.builder()
                .tc_id(tc_id).tc_password(tc_password).tc_email(tc_email).build();
    }
}
