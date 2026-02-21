package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.typework.TypeWorkDto;
import pal.comp.pgsbackend.dto.typework.UpdateTypeWorkDto;
import pal.comp.pgsbackend.mapper.TypeWorkMapper;
import pal.comp.pgsbackend.repository.TypeWorkRepository;

import java.util.List;

@Service
public class TypeWorkService {


    private static final Logger log = LoggerFactory.getLogger(TypeWorkService.class);
    private final TypeWorkRepository typeWorkRepository;
    private final TypeWorkMapper typeWorkMapper;

    public TypeWorkService(TypeWorkRepository typeWorkRepository, TypeWorkMapper typeWorkMapper) {
        this.typeWorkRepository = typeWorkRepository;
        this.typeWorkMapper = typeWorkMapper;
    }

    public List<TypeWorkDto> getAll(String name, String code) {
        log.info("Getting all type works");
        var typeWorks = this.typeWorkRepository.findAll(name, code);
        return typeWorks.stream().map(typeWorkMapper::toDto).toList();
    }

    public TypeWorkDto getById(Long id) {
        log.info("Getting type work by id = {}", id);
        var typeWork = this.typeWorkRepository.findById(id);
        if (typeWork.isEmpty())
            throw new EntityNotFoundException("Такого вида работ не существует. Id = " + id);
        return typeWorkMapper.toDto(typeWork.get());
    }

    public List<TypeWorkDto> getPlaningTypeWork(Long plotId) {
        log.info("Getting planing type works for plot by id = {}", plotId);
        var typeWorks = this.typeWorkRepository.findPlaningTypeWork(plotId);
        return typeWorks.stream().map(typeWorkMapper::toDto).toList();
    }

    public TypeWorkDto getByName(String name) {
        log.info("Getting type work by name = {}", name);
        var typeWork = this.typeWorkRepository.findByName(name);
        if (typeWork == null)
            throw new EntityNotFoundException("Такого вида работ не существует. Name = " + name);
        return typeWorkMapper.toDto(typeWork);
    }

    public TypeWorkDto create(TypeWorkDto typeWorkToCreate) {
        log.info("Creating type work: {}", typeWorkToCreate.toString());
        var typeWorkEntity = this.typeWorkMapper.toEntity(typeWorkToCreate);
        var createdTypeWork = this.typeWorkRepository.save(typeWorkEntity);
        return this.typeWorkMapper.toDto(createdTypeWork);
    }

    @Transactional
    public TypeWorkDto update(Long id, UpdateTypeWorkDto typeWorkToUpdate) {
        log.info("Updating type work by id = {}; new name = {}", id, typeWorkToUpdate.toString());
        var typeWork = this.typeWorkRepository.findById(id);
        if (typeWork.isEmpty())
            throw new EntityNotFoundException("Такого вида работ не существует. Id = " + id);

        typeWork.get().setCode(typeWorkToUpdate.code() == null ? typeWork.get().getCode() : typeWorkToUpdate.code());
        typeWork.get().setName(typeWorkToUpdate.name() == null ? typeWork.get().getName() : typeWorkToUpdate.name());
        var updatedTypeWork = this.typeWorkRepository.save(typeWork.get());
        return typeWorkMapper.toDto(updatedTypeWork);
    }

    public void delete(Long id) {
        log.info("Deleting type work by id = {}", id);
        var typeWork = this.typeWorkRepository.findById(id);
        if (typeWork.isEmpty())
            throw new EntityNotFoundException("Такого вида работ не существует. Id = " + id);
        this.typeWorkRepository.delete(typeWork.get());
    }
}
