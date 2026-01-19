package pal.comp.pgsbackend.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type_works")
public class TypeWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeWork")
    private List<SubtypeWorkEntity> subtypeWorks;

    @OneToMany(mappedBy = "typeWork")
    private List<PlanEntity> plans;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
