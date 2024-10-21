package zerobase.tablenow.domin.member.dto;

import lombok.*;

@Data
public class UserDto {
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String phone;
}
