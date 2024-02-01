package com.swagger.demo.User;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_dto")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String roles;
    private String permissions;
    private int active;
    private int blocked;
    private String createdDate;
    private String createdBy;
    private String updatedDate;
    private String updatedBy;
    private String password;
}
