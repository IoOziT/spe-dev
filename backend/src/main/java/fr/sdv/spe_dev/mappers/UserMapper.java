package fr.sdv.spe_dev.mappers;

import fr.sdv.spe_dev.dtos.InputUserDTO;
import fr.sdv.spe_dev.dtos.OutputUserDTO;
import fr.sdv.spe_dev.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    OutputUserDTO toUserDTO(User user);


    User toUser(InputUserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(InputUserDTO dto, @MappingTarget User user);
}

