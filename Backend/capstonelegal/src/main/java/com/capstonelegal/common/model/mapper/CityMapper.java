package com.capstonelegal.common.model.mapper;
import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.entities.City;
import org.modelmapper.ModelMapper;

public class CityMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CityDTO toDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

    public static City toEntity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }
}

