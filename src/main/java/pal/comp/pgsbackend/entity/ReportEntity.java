package pal.comp.pgsbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private PlanEntity plan;


    @Column(name = "fact")
    private Float fact;


    @Column(name = "delta")
    private Float delta;

    @Column(name = "date")
    private LocalDate date;


    @Column(name = "who_send")
    private String whoSend;

    @Column(name = "machine")
    private String machine;

    @Column(name = "comment")
    private String comment;

    public ReportEntity() {
    }

    public ReportEntity(Long id, Long planId, Float fact, Float delta, LocalDate date, String whoSend, String machine, String comment) {
        this.id = id;
        this.plan = new PlanEntity();
        this.plan.setId(planId);
        this.fact = fact;
        this.delta = delta;
        this.date = date;
        this.whoSend = whoSend;
        this.machine = machine;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PlanEntity getPlan() {
        return plan;
    }

    public void setPlan(PlanEntity plan) {
        this.plan = plan;
    }

    public Float getFact() {
        return fact;
    }

    public void setFact(Float fact) {
        this.fact = fact;
    }

    public Float getDelta() {
        return delta;
    }

    public void setDelta(Float delta) {
        this.delta = delta;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getWhoSend() {
        return whoSend;
    }

    public void setWhoSend(String whoSend) {
        this.whoSend = whoSend;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
