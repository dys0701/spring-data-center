package org.data.center.repository.mysql.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true, nullable = false, length = 64)
    private String userName;
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Column(name = "nick_name", nullable = false)
    private String nickName;
    /**
     *
     * other column ...
     *
     */
}
