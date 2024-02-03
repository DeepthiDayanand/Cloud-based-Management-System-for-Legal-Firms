package com.capstonelegal.judge.model;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.District;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("City Model Test")
public class CityTest {

    @Test
    @DisplayName("Test City Getters and Setters")
    public void testGettersAndSetters() {
        // Arrange
        String cityId = "1";
        String cityName = "New York";
        District district = new District();

        // Act
        City city = new City(cityId, cityName, district);

        // Assert
        Assertions.assertEquals(cityId, city.getCityId());
        Assertions.assertEquals(cityName, city.getCityName());
        Assertions.assertEquals(district, city.getDistrict());

        String newCityName = "San Francisco";
        city.setCityName(newCityName);
        Assertions.assertEquals(newCityName, city.getCityName());

        District newDistrict = new District();
        city.setDistrict(newDistrict);
        Assertions.assertEquals(newDistrict, city.getDistrict());
    }

    @Test
    @DisplayName("Test City toJSON Method")
    public void testToJSON() {
        // Arrange
        String cityId = "1";
        String cityName = "New York";
        District district = new District();

        City city = new City(cityId, cityName, district);

        // Act
        String json = city.toJSON();

        // Assert
        Assertions.assertEquals("{\"cityId\":\"1\",\"cityName\":\"New York\",\"district\":{}}", json);
    }
}
