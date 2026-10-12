package fr._42.orm.models;

import fr._42.orm.annotations.*;

@OrmEntity(table = "simple_user")
public class User {
    @OrmColumnId
    private Long id;

    @OrmColumn(name = "first_name", length = 50)
    private String firstName;

    @OrmColumn(name = "last_name", length = 50)
    private String lastName;

    @OrmColumn(name = "age")
    private Integer age;

    public User() {
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}