package com.capstonelegal.court.model.dto;

import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.court.model.entities.Court;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link Court}
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
public class CourtDTO implements Serializable {
    private String courtId;
    private String courtTypeId;
    private String courtTypeName;
    private CountryDTO courtCountry;
    private StateDTO courtState;
    private DistrictDTO courtDistrict;
    private CityDTO courtCity;
    private String courtStreet1;
    private String courtStreet2;
    private String courtStreetZipcode;
    public static CourtDTO fromCourtEntity(Court court) {
        return new CourtDTO()
                .setCourtId(court.getCourtId())
                .setCourtTypeId(court.getCourtTypeId())
                .setCourtTypeName(court.getCourtTypeName())
                .setCourtCountry(CountryDTO.fromCountryEntity(court.getCourtCountry()))
                .setCourtState(StateDTO.fromStateEntity(court.getCourtState()))
                .setCourtDistrict(DistrictDTO.fromDistrictEntity(court.getCourtDistrict()))
                .setCourtCity(CityDTO.fromCityEntity(court.getCourtCity()))
                .setCourtStreet1(court.getCourtStreet1())
                .setCourtStreet2(court.getCourtStreet2())
                .setCourtStreetZipcode(court.getCourtStreetZipcode());
    }
}