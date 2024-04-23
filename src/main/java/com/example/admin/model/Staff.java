package com.example.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String firstName;

    @Column
    private String middleName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private String mobileNumber;

    @Column
    @NotNull
    private String emailId;


    @Column
    @NotNull
    private String description;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "staff_roles",
    joinColumns = {@JoinColumn(name = "staff_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roles;
}
