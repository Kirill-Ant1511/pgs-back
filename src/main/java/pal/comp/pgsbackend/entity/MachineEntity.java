package pal.comp.pgsbackend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "machines")
public class MachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    public MachineEntity() {}

    public MachineEntity(Long id) {
        this.id = id;
    }

    public MachineEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
