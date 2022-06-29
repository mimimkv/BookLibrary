package com.example.booklibrary.model;

import com.example.booklibrary.dto.PlainUserDto;
import com.example.booklibrary.dto.UserDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "UniqueEmail", columnNames = {"email"})})
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Email
    @Column
    private String email;

    @Column
    @CreationTimestamp
    private LocalDate joinedDate;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows = new ArrayList<>();

    public static User from(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return user;
    }

    public static User from(PlainUserDto plainUserDto) {
        User user = new User();
        user.setId(plainUserDto.getId());
        user.setFirstName(plainUserDto.getFirstName());
        user.setLastName(plainUserDto.getLastName());
        user.setEmail(plainUserDto.getEmail());
        user.setJoinedDate(plainUserDto.getJoinedDate());

        return user;
    }

    public void borrow(Borrow borrow) {
        borrows.add(borrow);
    }
}
