package pal.comp.pgsbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "plan")
    @JsonIgnore
    private List<ReportEntity> workReports;

    @ManyToMany
    @JoinTable(
            name = "machine_plans",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "machine_id")
    )
    private List<MachineEntity> machines;

    public PlanEntity() {}

    public PlanEntity(Long id, Long plot, Long typeWork, Long subtypeWork, String productionName, Float volume, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        this.id = id;
        this.plot = new PlotEntity();
        this.typeWork = new TypeWorkEntity();
        this.subtypeWork = new SubtypeWorkEntity();
        this.plot.setId(plot);
        this.typeWork.setId(typeWork);
        this.subtypeWork.setId(subtypeWork);
        this.productionName = productionName;
        this.volume = volume;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public List<MachineEntity> getMachines() {
        return machines;
    }

    public void setMachines(List<MachineEntity> machines) {
        this.machines = machines;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

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

    public List<ReportEntity> getWorkReports() {
        return workReports;
    }

    public void setWorkReports(List<ReportEntity> workReportEntities) {
        this.workReports = workReportEntities;
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
