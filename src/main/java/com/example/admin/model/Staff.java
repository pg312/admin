package com.example.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String firstName;

    @Column
    private String middleName;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    @Size(min=10, max=10)
    private String mobileNumber;

    @Column
    @NotBlank
    private String emailId;


    @Column
    @NotBlank
    private String description;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "staff_roles",
    joinColumns = {@JoinColumn(name = "staff_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roles;
}
