package com.capstonelegal.common.model.mapper;
import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.common.model.entities.State;
import org.modelmapper.ModelMapper;

public class StateMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static StateDTO toDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    public static State toEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }
}

