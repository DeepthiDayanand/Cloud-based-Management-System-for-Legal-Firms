package com.capstonelegal.court.service;
import com.capstonelegal.court.model.entities.CourtType;
import com.capstonelegal.court.repository.CourtTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourtTypeService {
    @Autowired
    private CourtTypeRepository courtTypeRepository;
    public List<CourtType> getAllCourtTypes() {
        return courtTypeRepository.findAll();
    }

    public CourtType getCourtTypeById(String id) {
        return courtTypeRepository.findById(id).orElse(null);
    }

    public CourtType createOrUpdateCourtType(CourtType courtType) {
        return courtTypeRepository.save(courtType);
    }

    public void deleteCourtType(String id) {
        courtTypeRepository.deleteById(id);
    }
}