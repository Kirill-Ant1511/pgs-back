package pal.comp.pgsbackend.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.Lint;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_plots", // Имя вашей таблицы из скриншота
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plot_id")
    )
    private List<PlotEntity> userPlots;

    public UserEntity() {
    }

    public UserEntity(String name, String surname, String telegramId, Role role) {
        this.name = name;
        this.surname = surname;
        this.telegramId = telegramId;
        this.role = role;
    }

    public List<PlotEntity> getUserPlots() {
        return userPlots;
    }

    public void setUserPlots(List<PlotEntity> userPlots) {
        this.userPlots = userPlots;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
