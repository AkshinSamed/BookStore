package com.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    @Column(name = "is_publisher") private boolean isPublisher;

    public User(String username, String email, String password){
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @OneToMany(mappedBy = "user",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @Column(name = "book_list") private List<Book> bookList;

}
