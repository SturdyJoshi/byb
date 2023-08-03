package com.byb.bookYourBook.entity;

import com.byb.bookYourBook.entity.Address;
import com.byb.bookYourBook.entity.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    private String userName;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private long phoneNumber;

    @OneToOne
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

}
