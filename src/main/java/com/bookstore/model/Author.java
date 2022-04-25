package com.bookstore.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name")  private String lastName;
    @Column(name = "birth_date") private Date birthDate;

    @OneToMany(mappedBy = "author",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @Column(name = "book_list") private List<Book> bookList;
}
