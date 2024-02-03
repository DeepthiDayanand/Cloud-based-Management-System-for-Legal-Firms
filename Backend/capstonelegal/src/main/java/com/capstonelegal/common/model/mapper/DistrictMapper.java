package com.capstonelegal.common.model.mapper;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.entities.District;
import org.modelmapper.ModelMapper;

public class DistrictMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static DistrictDTO toDTO(District district) {
        return modelMapper.map(district, DistrictDTO.class);
    }

    public static District toEntity(DistrictDTO districtDTO) {
        return modelMapper.map(districtDTO, District.class);
    }
}


