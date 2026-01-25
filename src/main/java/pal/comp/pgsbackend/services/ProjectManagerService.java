package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.projectmanager.ResponseProjectManagerDto;
import pal.comp.pgsbackend.mapper.ProjectManagerMapper;
import pal.comp.pgsbackend.repository.ProjectManagerRepository;

import java.util.List;

@Service
public class ProjectManagerService {
    private static final Logger log = LoggerFactory.getLogger(ProjectManagerService.class);
    private final ProjectManagerRepository projectManagerRepository;
    private final ProjectManagerMapper projectManagerMapper;


    public ProjectManagerService(ProjectManagerRepository projectManagerRepository, ProjectManagerMapper projectManagerMapper) {
        this.projectManagerRepository = projectManagerRepository;
        this.projectManagerMapper = projectManagerMapper;
    }

    public List<ResponseProjectManagerDto> getAll() {
        log.info("Getting all project manager");
        var prManagers = this.projectManagerRepository.findAll();
        return prManagers.stream().map(projectManagerMapper::toDto).toList();
    }

    public ResponseProjectManagerDto getByTelegramId(String telegramId) {
        log.info("Getting project manager by telegramId = {}", telegramId);
        var prManager = this.projectManagerRepository.findByTelegramId(telegramId);
        if (prManager.isEmpty())
            throw new EntityNotFoundException("Такого проектного менеджера не существует");
        return projectManagerMapper.toDto(prManager.get());
    }

    public ResponseProjectManagerDto create(ResponseProjectManagerDto prManagerToCreate) {
        log.info("Creating project manager: {}", prManagerToCreate.toString());
        var prManagerEntity = projectManagerMapper.toEntity(prManagerToCreate);
        var createdPrManager = this.projectManagerRepository.save(prManagerEntity);
        return projectManagerMapper.toDto(createdPrManager);
    }

    public void delete(Long id) {
        log.info("Deleting project manager by id = {}", id);
        var prManager = this.projectManagerRepository.findById(id);
        if (prManager.isEmpty())
            throw new EntityNotFoundException("Такого проектного менеджера не существует");
        this.projectManagerRepository.delete(prManager.get());
    }
}
