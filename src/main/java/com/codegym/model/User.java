package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table
@Component
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 45, message = "Ten khong dung do dai")
    private String firstName;

    @NotEmpty
    @Size(min = 5, max = 45, message = "Ten khong dung do dai")
    private String lastName;

    @Min(value = 18, message = "Khong du tuoi")
    private int age;

    @Email
    private String email;

    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        String phoneNumber = user.getPhoneNumber();
        ValidationUtils.rejectIfEmpty( errors, "phoneNumber", "number.empty" );
        if (phoneNumber.length() > 11 || phoneNumber.length() < 10) {
            errors.rejectValue( "phoneNumber", "number.length" );
        }
        if (!phoneNumber.startsWith( "0" )) {
            errors.rejectValue( "phoneNumber", "number.startsWith" );
        }
        if (!phoneNumber.matches( "(^$|[0-9]*$)" )) {
            errors.rejectValue( "phoneNumber", "number.matches" );
        }
    }
}
