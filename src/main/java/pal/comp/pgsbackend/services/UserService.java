package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.entity.PlotEntity;
import pal.comp.pgsbackend.mapper.UserMapper;
import pal.comp.pgsbackend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository projectManagerRepository, UserMapper projectManagerMapper) {
        this.userRepository = projectManagerRepository;
        this.userMapper = projectManagerMapper;
    }

    public List<ResponseUserDto> getAll() {
        log.info("Getting all project manager");
        var prManagers = this.userRepository.findAll();
        return prManagers.stream().map(userMapper::toDto).toList();
    }

    public ResponseUserDto getByTelegramId(String telegramId) {
        log.info("Getting project manager by telegramId = {}", telegramId);
        var prManager = this.userRepository.findByTelegramId(telegramId);
        if (prManager.isEmpty())
            throw new EntityNotFoundException("Такого проектного менеджера не существует");
        return userMapper.toDto(prManager.get());
    }

    public ResponseUserDto create(RequestUserDto userToCreate) {
        log.info("Creating project manager: {}", userToCreate.toString());
        var userEntity = userMapper.toEntity(userToCreate);
        userToCreate.plotIds().forEach(System.out::println);
        var plots =  userToCreate.plotIds().stream().map(id -> new PlotEntity(id, null)).toList();

        userEntity.setUserPlots(plots);
        var createdPrManager = this.userRepository.save(userEntity);
        return userMapper.toDto(createdPrManager);
    }

    public void delete(Long id) {
        log.info("Deleting project manager by id = {}", id);
        var prManager = this.userRepository.findById(id);
        if (prManager.isEmpty())
            throw new EntityNotFoundException("Такого проектного менеджера не существует");
        this.userRepository.delete(prManager.get());
    }
}
