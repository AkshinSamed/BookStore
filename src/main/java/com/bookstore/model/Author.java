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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(
                    name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "id"))

    @Column(name = "book_list") private List<Book> bookList;


}
