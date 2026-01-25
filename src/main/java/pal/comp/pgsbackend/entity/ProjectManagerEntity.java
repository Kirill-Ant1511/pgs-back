package pal.comp.pgsbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_managers")
public class ProjectManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telegram_id")
    private String telegramId;


    public ProjectManagerEntity() {}

    public ProjectManagerEntity(Long id, String name, String surname, String telegramId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telegramId = telegramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
