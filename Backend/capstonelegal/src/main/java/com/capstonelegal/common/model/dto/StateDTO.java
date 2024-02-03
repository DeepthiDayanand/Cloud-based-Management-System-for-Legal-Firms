package com.capstonelegal.common.model.dto;

import com.capstonelegal.common.model.entities.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

public class StateDTO implements Serializable {
    private String stateId;
    private String stateName;
    private String stateInitials;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryDTO country;
    /**
     * Convert a State entity to a StateDTO.
     *
     * @param state the State entity
     * @return a StateDTO object with corresponding fields from the State entity
     */
    public static StateDTO fromStateEntity(State state){
        StateDTO stateDTO = null;
        if(state != null){
            stateDTO = new StateDTO();
            stateDTO.setStateId(state.getStateId());
            stateDTO.setStateName(state.getStateName());
            stateDTO.setStateInitials(state.getStateInitials());

            if (state.getCountry() != null) {
                CountryDTO countryDTO = new CountryDTO();
                countryDTO.setCountryId(state.getCountry().getCountryId());
                countryDTO.setCountryName(state.getCountry().getCountryName());
                countryDTO.setCountryInitials(state.getCountry().getCountryInitials());
                stateDTO.setCountry(countryDTO);
            }

        }
        return stateDTO;
    }
    /**
     * Convert a State entity to a StateDTO.
     *
     * @param state the State entity
     * @return a StateDTO object with corresponding fields from the State entity this will not include associated Country Entity
     */
    public static StateDTO fromStateEntityWithoutAssociatedEntities(State state){
        StateDTO stateDTO = null;
        if(state != null){
            stateDTO = new StateDTO();
            stateDTO.setStateId(state.getStateId());
            stateDTO.setStateName(state.getStateName());
            stateDTO.setStateInitials(state.getStateInitials());
        }
        return stateDTO;
    }
}