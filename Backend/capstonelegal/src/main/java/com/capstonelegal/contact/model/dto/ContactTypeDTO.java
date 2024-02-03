package com.capstonelegal.contact.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ContactTypeDTO is a Data Transfer Object (DTO) class. It is a lightweight representation of
 * the ContactType entity used to efficiently transport data between server and client,
 * or between different parts of the server itself.
 */

// The @Data is a convenient shortcut annotation in Lombok that bundles
// the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together.
// In other words, @Data generates boilerplate code for getters, setters,
// equals, hashCode, and a constructor for all final fields.
@Data

// @AllArgsConstructor is a Lombok annotation that generates a constructor with a parameter for each field in your class.
// Fields marked with @NonNull result in null checks on those parameters.
@AllArgsConstructor

// @NoArgsConstructor is a Lombok annotation that generates a constructor with no parameters.
@NoArgsConstructor

// @Accessors is a Lombok annotation that influences the way that setters are generated.
// The 'chain' attribute, when true, causes setters to return 'this' (the setter method's class)
// to allow for "method chaining", for example: myObject.setA(a).setB(b).setC(c)
@Accessors(chain = true)

// @JsonIgnoreProperties is a Jackson annotation that indicates that other fields of the JSON
// data should be ignored and not included in the POJO during deserialization if they are not found in the POJO.
// 'ignoreUnknown' is set to true to ignore any unrecognized properties during deserialization.
// This is a strategy that can be useful when only a subset of properties are of interest.
@JsonIgnoreProperties(ignoreUnknown = true)

public class ContactTypeDTO implements Serializable {
    private String contactTypeId;
    private String contactTypeName;
    private String contactTypeDescription;
}

