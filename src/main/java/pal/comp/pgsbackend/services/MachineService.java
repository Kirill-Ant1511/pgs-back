package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.machine.RequestMachineDto;
import pal.comp.pgsbackend.dto.machine.ResponseMachineDto;
import pal.comp.pgsbackend.mapper.MachineMapper;
import pal.comp.pgsbackend.repository.MachineRepository;

import java.util.List;

@Service
public class MachineService {
    private final MachineRepository repository;
    private final MachineMapper mapper;

    public MachineService(MachineRepository machineRepository, MachineMapper mapper) {
        this.repository = machineRepository;
        this.mapper = mapper;
    }

    public List<ResponseMachineDto> findAll() {
        var machineEntity = repository.findAll();
        return machineEntity.stream().map(mapper::toDto).toList();
    }

    public ResponseMachineDto findById(Long id) {
        var machineEntity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Machine with id " + id + " not found")
        );
        return mapper.toDto(machineEntity);
    }

    public ResponseMachineDto create(RequestMachineDto requestDto) {
        var machineEntity = mapper.toEntity(requestDto);
        var createdMachine = repository.save(machineEntity);
        return mapper.toDto(createdMachine);
    }

    public ResponseMachineDto update(Long id, RequestMachineDto requestDto) {
        var foundMachine = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Such machine not found")
        );
        foundMachine.setName(requestDto.name());
        var updatedMachine = repository.save(foundMachine);
        return mapper.toDto(updatedMachine);
    }

    public void delete(Long id) {
        var foundMachine = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Such machine not found")
        );
        repository.delete(foundMachine);
    }


}
