package com.capstonelegal.common.model.dto;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.model.entities.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CityDTO is a Data Transfer Object (DTO) class. It is a lightweight representation of
 * the City entity used to efficiently transport data between server and client,
 * or between different parts of the server itself.
 * This class also includes a reference to the DistrictDTO which reflects the relationship
 * between a City and its District in the original City model entity.
 */

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

public class CityDTO implements Serializable {
    private String cityId;
    private String cityName;
    private DistrictDTO district;

    /**
     * Converts a City entity object into a CityDTO object
     * @param city The City entity that will be converted into a CityDTO
     * @return The converted CityDTO object
     */
    public static CityDTO fromCityEntity(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityId(city.getCityId());
        cityDTO.setCityName(city.getCityName());

        cityDTO.setDistrict(DistrictDTO.fromDistrictEntity(city.getDistrict()));

        return cityDTO;
    }

}
