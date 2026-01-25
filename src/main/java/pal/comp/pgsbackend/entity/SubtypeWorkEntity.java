package pal.comp.pgsbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "unit_metering")
    private String unitMetring;

    public String getUnitMetring() {
        return unitMetring;
    }

    public void setUnitMetring(String unitMetring) {
        this.unitMetring = unitMetring;
    }

    @JoinColumn(name = "type_work_id")
    private Long typeWorkId;

    @OneToMany(mappedBy = "subtypeWork")
    @JsonIgnore
    private List<PlanEntity> plans;

    public Long getTypeWorkId() {
        return typeWorkId;
    }

    public void setTypeWorkId(Long typeWorkId) {
        this.typeWorkId = typeWorkId;
    }

    public SubtypeWorkEntity() {
    }

    public SubtypeWorkEntity(Long id, String code, String name, Long typeWorkId, String unitMetring) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.unitMetring = unitMetring;
        this.typeWorkId = typeWorkId;
    }

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

    public List<PlanEntity> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanEntity> plans) {
        this.plans = plans;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
