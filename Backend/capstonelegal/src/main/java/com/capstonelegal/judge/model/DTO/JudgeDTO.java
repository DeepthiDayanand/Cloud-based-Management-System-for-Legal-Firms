    package com.capstonelegal.judge.model.DTO;

    import com.capstonelegal.common.model.dto.CityDTO;
    import com.capstonelegal.common.model.dto.CountryDTO;
    import com.capstonelegal.common.model.dto.DistrictDTO;
    import com.capstonelegal.common.model.dto.StateDTO;
    import com.capstonelegal.court.model.dto.CourtDTO;
    import com.capstonelegal.credentials.model.dto.CredentialsDTO;
    import com.capstonelegal.judge.model.entities.Judge;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.Accessors;

    import java.io.Serializable;

    /**
     * DTO for {@link com.capstonelegal.judge.model.entities.Judge}
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
    public class JudgeDTO implements Serializable {
        private String id;
        private JudgeTypeDTO judgeType;
        private CourtDTO court;
        private String firstName;
        private String lastName;
        private String email;
        private CountryDTO country;
        private StateDTO state;
        private DistrictDTO district;
        private CityDTO city;
        private String street1;
        private String street2;
        private String zipcode;
        private CredentialsDTO credentials;
        public static JudgeDTO fromJudgeEntity(Judge judge) {
            return new JudgeDTO()
                    .setId(judge.getId())
                    .setJudgeType(JudgeTypeDTO.fromJudgeTypeEntity(judge.getJudgeType()))
                    .setCourt(CourtDTO.fromCourtEntity(judge.getCourt()))
                    .setFirstName(judge.getFirstName())
                    .setLastName(judge.getLastName())
                    .setEmail(judge.getEmail())
                    .setCountry(CountryDTO.fromCountryEntity(judge.getCountry()))
                    .setState(StateDTO.fromStateEntity(judge.getState()))
                    .setDistrict(DistrictDTO.fromDistrictEntity(judge.getDistrict()))
                    .setCity(CityDTO.fromCityEntity(judge.getCity()))
                    .setStreet1(judge.getStreet1())
                    .setStreet2(judge.getStreet2())
                    .setZipcode(judge.getZipcode())
                    .setCredentials(CredentialsDTO.fromEntity(judge.getCredentials()));
        }
    }