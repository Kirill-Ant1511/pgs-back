package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.subtypework.SubtypeWorkDto;
import pal.comp.pgsbackend.dto.subtypework.UpdateSubtypeWorkDto;
import pal.comp.pgsbackend.mapper.SubtypeWorkMapper;
import pal.comp.pgsbackend.repository.SubtypeWorkRepository;

import java.util.List;

@Service
public class SubtypeWorkService {

    private static final Logger log = LoggerFactory.getLogger(SubtypeWorkService.class);
    private final SubtypeWorkRepository subtypeWorkRepository;
    private final SubtypeWorkMapper subtypeWorkMapper;

    public SubtypeWorkService(SubtypeWorkRepository subtypeWorkRepository, SubtypeWorkMapper subtypeWorkMapper) {
        this.subtypeWorkRepository = subtypeWorkRepository;
        this.subtypeWorkMapper = subtypeWorkMapper;
    }

    public List<SubtypeWorkDto> getAll() {
        log.info("Getting all subtype works");
        var subtypeWorks = this.subtypeWorkRepository.findAll();
        return subtypeWorks.stream().map(subtypeWorkMapper::toDto).toList();
    }


    public List<SubtypeWorkDto> getAllWithFilters(String code, String name, Long typeWorkId, Integer pageNumber, Integer pageSize) {
        log.info("Getting all subtype work with filters {} {} {} ", code, name, typeWorkId );
        var size = pageSize != null ? pageSize : 10;
        var page = pageNumber != null ? pageNumber : 0;
        var pageable = Pageable.ofSize(size).withPage(page);
        var subtypeWorks = this.subtypeWorkRepository.findAll(code, name, typeWorkId, pageable);
        return  subtypeWorks.stream().map(subtypeWorkMapper::toDto).toList();
    }

    public SubtypeWorkDto getById(Long id) {
        log.info("Getting subtype work by id: {}", id);
        var subtypeWork = this.subtypeWorkRepository.findById(id);
        if (subtypeWork.isEmpty())
            throw new EntityNotFoundException("Такого типа работ не существует. Id = " + id);
        return subtypeWorkMapper.toDto(subtypeWork.get());
    }

    public List<SubtypeWorkDto> getByTypeWorkId(Long typeWorkId) {
        log.info("Getting all subtype works by type work id: {}", typeWorkId);
        var subtypeWorks = this.subtypeWorkRepository.findByTypeWorkId(typeWorkId);
        return subtypeWorks.stream().map(subtypeWorkMapper::toDto).toList();
    }

    public SubtypeWorkDto getByName(String name) {
        log.info("Getting subtype work by name: {}", name);
        var subtypeWork = this.subtypeWorkRepository.findByName(name);
        if (subtypeWork == null)
            throw new EntityNotFoundException("Такого типа работ не существует. Name = " + name);
        return subtypeWorkMapper.toDto(subtypeWork);
    }

    public List<SubtypeWorkDto> getPlaningSubtypeWork(Long plotId, Long typeWorkId) {
        log.info("Getting all planing subtype works by plot id: {} and type work id: {}", plotId, typeWorkId);
        var subtypeWorks = this.subtypeWorkRepository.findPlaningSubtypeWork(plotId, typeWorkId);
        return subtypeWorks.stream().map(subtypeWorkMapper::toDto).toList();
    }

    public SubtypeWorkDto create(SubtypeWorkDto subtypeWorkToCreate) {
        log.info("Creating new subtype work: {}", subtypeWorkToCreate.toString());
        var subtypeWorkEntity = subtypeWorkMapper.toEntity(subtypeWorkToCreate);
        var createdSubtypeWork = this.subtypeWorkRepository.save(subtypeWorkEntity);
        return subtypeWorkMapper.toDto(createdSubtypeWork);
    }

    @Transactional
    public SubtypeWorkDto update(Long id, UpdateSubtypeWorkDto subtypeWorkToUpdate) {
        log.info("Updating subtype work by id = {}; new data: {}", id, subtypeWorkToUpdate.toString());
        var subtypeWorkEntity = this.subtypeWorkRepository.findById(id);
        if (subtypeWorkEntity.isEmpty())
            throw new EntityNotFoundException("Такого типа работ не существует. Id = " + id);

        subtypeWorkEntity.get().setCode(
                subtypeWorkToUpdate.code() != null ? subtypeWorkToUpdate.code() : subtypeWorkEntity.get().getCode()
        );
        subtypeWorkEntity.get().setName(
                subtypeWorkToUpdate.name() != null ? subtypeWorkToUpdate.name() : subtypeWorkEntity.get().getName()
        );
        subtypeWorkEntity.get().setUnitMetring(
                subtypeWorkToUpdate.unitMetering() != null ? subtypeWorkToUpdate.unitMetering() : subtypeWorkEntity.get().getUnitMetring()
        );
        subtypeWorkEntity.get().setTypeWorkId(
                subtypeWorkToUpdate.typeWorkId() != null ? subtypeWorkToUpdate.typeWorkId() : subtypeWorkEntity.get().getTypeWorkId()
        );

        var updatedSubtype = this.subtypeWorkRepository.save(subtypeWorkEntity.get());
        return subtypeWorkMapper.toDto(updatedSubtype);
    }


    public void  delete(Long id) {
        log.info("Deleting subtype work by id: {}", id);
        var subtypeWork = this.subtypeWorkRepository.findById(id);
        if (subtypeWork.isEmpty())
            throw new EntityNotFoundException("Такого типа работ не существует. Id = " + id);
        this.subtypeWorkRepository.delete(subtypeWork.get());
    }
}
