package pal.comp.pgsbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.users.RequestUpdateUserDto;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.entity.PlotEntity;
import pal.comp.pgsbackend.mapper.UserMapper;
import pal.comp.pgsbackend.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        if (userToCreate.plotIds() != null) {
            var plots =  userToCreate.plotIds().stream().map(id -> new PlotEntity(id, null)).toList();
            userEntity.setUserPlots(plots);
        }
        var createdPrManager = this.userRepository.save(userEntity);
        return userMapper.toDto(createdPrManager);
    }

    public ResponseUserDto addPlotForUser(Long userId, Long plotId) {
        log.info("Adding plot for user {}, plot id = {}", userId, plotId);
        var userEntity = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Such user not exists")
        );
        userRepository.addPlotForUser(userId, plotId);
        return userMapper.toDto(userEntity);
    }


    public ResponseUserDto update(Long id, RequestUpdateUserDto updateUserDto) {
        log.info("Update user by id = {}, and request body = {}", id, updateUserDto.toString());
        var userEntity = this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Such user nit exists")
        );

        userEntity.setName(updateUserDto.name());
        userEntity.setSurname(updateUserDto.surname());
        userEntity.setTelegramId(updateUserDto.telegramId());
        userEntity.setRole(updateUserDto.role());
        if (updateUserDto.plotIds() != null) {
            userEntity.setUserPlots(
                    updateUserDto.plotIds().stream().map(plotId -> new PlotEntity(plotId, null)).toList()
            );
        }

        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    public void delete(Long id) {
        log.info("Deleting project manager by id = {}", id);
        var prManager = this.userRepository.findById(id);
        if (prManager.isEmpty())
            throw new EntityNotFoundException("Такого проектного менеджера не существует");
        this.userRepository.delete(prManager.get());
    }
}
