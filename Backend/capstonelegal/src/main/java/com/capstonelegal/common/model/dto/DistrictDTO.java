package com.capstonelegal.common.model.dto;

import com.capstonelegal.common.model.entities.District;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

// @Data is a Lombok-provided annotation which tells Lombok to generate
// a getter, setter, equals, hashCode, toString methods, and a constructor for all final fields.
@Data

// @AllArgsConstructor is an annotation provided by Lombok
// which generates a constructor with 1 parameter for each field in your class.
@AllArgsConstructor

// @NoArgsConstructor is a Lombok-provided annotation which generates a no-args constructor.
@NoArgsConstructor

// @Accessors is a Lombok-provided annotation which can be configured
// to make setters return 'this' (the setter's class). 'chain' is set to true
// to allow for "method chaining" or "fluent-style" programming.
@Accessors(chain = true)

// @JsonIgnoreProperties is a Jackson annotation which indicates that other fields of the JSON
// data should be ignored and not included in the DTO during deserialization if they're not found in the DTO.
// 'ignoreUnknown' is set to true to ignore any unrecognized properties during deserialization.
@JsonIgnoreProperties(ignoreUnknown = true)

public class DistrictDTO implements Serializable {
    private String districtId;
    private String districtName;
    private StateDTO state;
    private CountryDTO country;
    /**
     * Convert a State entity to a DistrictDTO.
     *
     * @param district the District entity
     * @return a DistrictDTO object with corresponding fields from the State entity
     */
    public static DistrictDTO fromDistrictEntity(District district){
        DistrictDTO districtDTO = null;
        if(district != null){
            districtDTO = new DistrictDTO();
            districtDTO.setDistrictId(district.getDistrictId());
            districtDTO.setDistrictName(district.getDistrictName());
            if(district.getState() != null){
                StateDTO statedto = StateDTO.fromStateEntityWithoutAssociatedEntities(district.getState());
                districtDTO.setState(statedto);
                if(district.getState().getCountry() != null){
                    districtDTO.setCountry(CountryDTO.fromCountryEntity(district.getState().getCountry()));
                }
            }

        }
        return districtDTO;
    }
    
}