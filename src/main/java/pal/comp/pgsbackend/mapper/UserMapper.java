package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserPlotsDto;
import pal.comp.pgsbackend.entity.Users;

@Component
public class ProjectManagerMapper {
    public ResponseUserDto toDto(Users projectManagerEntity) {
        return new ResponseUserDto(
                projectManagerEntity.getId(),
                projectManagerEntity.getName(),
                projectManagerEntity.getSurname(),
                projectManagerEntity.getTelegramId(),
                projectManagerEntity.getRole(),
                projectManagerEntity.getUserPlots().stream().map(
                        plotEntity -> new ResponseUserPlotsDto(plotEntity.getPlot().getId(), plotEntity.getPlot().getName())
                ).toList()
        );
    }

    public Users toEntity(RequestUserDto projectManagerDto) {
        return new Users(
                projectManagerDto.name(),
                projectManagerDto.surname(),
                projectManagerDto.telegramId(),
                projectManagerDto.role()
        );
    }
}
