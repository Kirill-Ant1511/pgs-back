package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.projectmanager.ResponseProjectManagerDto;
import pal.comp.pgsbackend.entity.ProjectManagerEntity;

@Component
public class ProjectManagerMapper {
    public ResponseProjectManagerDto toDto(ProjectManagerEntity projectManagerEntity) {
        return new ResponseProjectManagerDto(
                projectManagerEntity.getId(),
                projectManagerEntity.getName(),
                projectManagerEntity.getSurname(),
                projectManagerEntity.getTelegramId()
        );
    }

    public ProjectManagerEntity toEntity(ResponseProjectManagerDto projectManagerDto) {
        return new ProjectManagerEntity(
                projectManagerDto.id(),
                projectManagerDto.name(),
                projectManagerDto.surname(),
                projectManagerDto.telegramId()
        );
    }
}
