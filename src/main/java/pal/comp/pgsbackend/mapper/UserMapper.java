package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.users.RequestUserDto;
import pal.comp.pgsbackend.dto.users.ResponseUserDto;
import pal.comp.pgsbackend.entity.UserEntity;

@Component
public class UserMapper {
    public ResponseUserDto toDto(UserEntity userEntity) {
        return new ResponseUserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getTelegramId(),
                userEntity.getRole(),
                userEntity.getUserPlots()

        );
    }

    public UserEntity toEntity(RequestUserDto userDto) {
        return new UserEntity(
                userDto.name(),
                userDto.surname(),
                userDto.telegramId(),
                userDto.role()
        );
    }
}
