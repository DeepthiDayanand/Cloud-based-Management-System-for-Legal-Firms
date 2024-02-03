package com.capstonelegal.common.model.mapper;
import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.entities.Country;
import org.modelmapper.ModelMapper;

public class CountryMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CountryDTO toDTO(Country country) {
        return modelMapper.map(country, CountryDTO.class);
    }

    public static Country toEntity(CountryDTO countryDTO) {
        return modelMapper.map(countryDTO, Country.class);
    }
}

