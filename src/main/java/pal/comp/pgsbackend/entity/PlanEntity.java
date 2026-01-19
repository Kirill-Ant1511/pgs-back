package pal.comp.pgsbackend.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plans")
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "plot_id")
    private PlotEntity plot;


    @ManyToOne
    @JoinColumn(name = "type_work_id")
    private TypeWorkEntity typeWork;

    @ManyToOne
    @JoinColumn(name = "subtype_work_id")
    private SubtypeWorkEntity subtypeWork;

    @Column(name = "production_name")
    private String productionName;

    @Column(name = "volume")
    private Float volume;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "plan")
    private List<WorkReport> workReports;


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<WorkReport> getWorkReports() {
        return workReports;
    }

    public void setWorkReports(List<WorkReport> workReports) {
        this.workReports = workReports;
    }

    public PlotEntity getPlot() {
        return plot;
    }

    public void setPlot(PlotEntity plot) {
        this.plot = plot;
    }

    public TypeWorkEntity getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(TypeWorkEntity typeWork) {
        this.typeWork = typeWork;
    }

    public SubtypeWorkEntity getSubtypeWork() {
        return subtypeWork;
    }

    public void setSubtypeWork(SubtypeWorkEntity subtypeWork) {
        this.subtypeWork = subtypeWork;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
