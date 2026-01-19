package pal.comp.pgsbackend.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subtype_works")
public class SubtypeWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "code")
    private String code;


    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_work_id")
    private TypeWorkEntity typeWork;

    @OneToMany(mappedBy = "subtypeWork")
    private List<PlanEntity> plans;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
