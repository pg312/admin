package com.example.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @NotBlank(message = "FirstName can't be blank")
    private String firstName;

    @Column
    private String middleName;

    @Column
    @NotBlank(message = "LastName can't be blank")
    private String lastName;

    @Column
    @NotBlank(message = "Mobile number can't be null")
    @Size(min=10, max=10, message = "Mobile number should be 10 characters")
    private String mobileNumber;

    @Column
    @NotBlank(message = "EmailId can't be null")
    @Email(message = "Invalid email id format")
    private String emailId;


    @Column
    @NotBlank(message = "Description can't be null")
    private String description;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "staff_roles",
    joinColumns = {@JoinColumn(name = "staff_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private List<Role> roles;
}
