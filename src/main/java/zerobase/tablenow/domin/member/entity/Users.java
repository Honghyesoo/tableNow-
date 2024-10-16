package zerobase.tablenow.domin.member.entity;

import jakarta.persistence.*;
import zerobase.tablenow.domin.baseEntity.BaseEntity;
import zerobase.tablenow.domin.member.constant.Role;

@Entity
@Table(name = "User")
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Role role;
}
