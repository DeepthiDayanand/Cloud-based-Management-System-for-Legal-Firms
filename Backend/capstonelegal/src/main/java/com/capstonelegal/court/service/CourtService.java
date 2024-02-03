package com.capstonelegal.court.service;
import com.capstonelegal.court.model.entities.Court;
import com.capstonelegal.court.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourtService {
    @Autowired
    private CourtRepository courtRepository;
    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    public Court getCourtById(String id) {
        return courtRepository.findById(id).orElse(null);
    }

    public Court createOrUpdateCourt(Court court) {
        return courtRepository.save(court);
    }

    public void deleteCourt(String id) {
        courtRepository.deleteById(id);
    }
}
