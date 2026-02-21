package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.plot.PlotDto;
import pal.comp.pgsbackend.mapper.PlotMapper;
import pal.comp.pgsbackend.repository.PlotRepository;

import java.util.List;

@Service
public class PlotService {

    private static final Logger log = LoggerFactory.getLogger(PlotService.class);
    private final PlotRepository plotRepository;
    private final PlotMapper plotMapper;

    public PlotService(PlotRepository plotRepository, PlotMapper plotMapper) {
        this.plotRepository = plotRepository;
        this.plotMapper = plotMapper;
    }

    public List<PlotDto> getAll(String nameSubstring) {
        log.info("Getting all plots");
        var plots = this.plotRepository.findAll(nameSubstring);
        return plots.stream().map(plotMapper::toDto).toList();
    }

    public PlotDto getById(Long id) {
        log.info("Getting plots by id = {}", id);
        var plot = this.plotRepository.findById(id);
        if(plot.isEmpty())
            throw new EntityNotFoundException("Такой участок не существует. Id = " + id);


        return plotMapper.toDto(plot.get());
    }

    public List<PlotDto> getPlaningPlot() {
        log.info("Getting planing plot");
        var plots = this.plotRepository.findPlaningPlots();
        return plots.stream().map(plotMapper::toDto).toList();
    }

    public PlotDto create(PlotDto plotToCreate) {
        log.info("Creating new plot: {}", plotToCreate.toString());
        var plotEntity = plotMapper.toEntity(plotToCreate);
        var plot = this.plotRepository.save(plotEntity);
        return plotMapper.toDto(plot);
    }

    @Transactional
    public PlotDto update(Long id, PlotDto plotToUpdate) {
        log.info("Updating plot by id = {}; new name = {}", id, plotToUpdate.name());
        var plot = this.plotRepository.findById(id);
        if(plot.isEmpty())
            throw new EntityNotFoundException("Такой участок не существует. Id = " + id);

        plot.get().setName(plotToUpdate.name());
        var updatedPlot = this.plotRepository.save(plot.get());
        return plotMapper.toDto(updatedPlot);
    }

    public void delete(Long id) {
        log.info("Deleting plot by id = {}", id);
        var plotToDelete = this.plotRepository.findById(id);
        if (plotToDelete.isEmpty()) {
            throw new EntityNotFoundException("Такого участка не существует");
        }

        this.plotRepository.delete(plotToDelete.get());
        log.info("Plot with id = {} was deleted", id);
    }

}
