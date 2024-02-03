package com.capstonelegal.judge.service;

import com.capstonelegal.judge.model.entities.JudgeType;
import com.capstonelegal.judge.repository.JudgeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeTypeService {
    @Autowired
    private JudgeTypeRepository judgeTypeRepository;

    public List<JudgeType> getAllJudgeTypes() {
        return judgeTypeRepository.findAll();
    }

    public JudgeType getJudgeTypeById(String id) {
        return judgeTypeRepository.findById(id).orElse(null);
    }

    public JudgeType createOrUpdateJudgeType(JudgeType judgeType) {
        return judgeTypeRepository.save(judgeType);
    }

    public void deleteJudgeType(String id) {
        judgeTypeRepository.deleteById(id);
    }
}
