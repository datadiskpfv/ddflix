package uk.co.datadisk.ddflix.services.film.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.mapper.film.ClassificationFormMapper;
import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;
import uk.co.datadisk.ddflix.entities.film.Classification;
import uk.co.datadisk.ddflix.repositories.film.ClassificationRepository;
import uk.co.datadisk.ddflix.services.film.ClassificationService;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;
    private final ClassificationFormMapper classificationFormMapper;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository, ClassificationFormMapper classificationFormMapper) {
        this.classificationRepository = classificationRepository;
        this.classificationFormMapper = classificationFormMapper;
    }

    @Override
    public void createClassification(ClassificationFormDTO classificationFormDTO) {
        Classification classification = classificationFormMapper.ClassificationFormDTOToClassification(classificationFormDTO);
        classificationRepository.saveAndFlush(classification);
    }

    @Override
    public ClassificationFormDTO findClassification(Long id) {
        return classificationFormMapper.ClassificationToClassificationFormDTO(classificationRepository.getOne(id));
    }

    @Override
    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    @Override
    public Classification findByRating(String rating) {
        return classificationRepository.findByRating(rating);
    }

    @Override
    public void deleteClassificationById(Long id) {
        classificationRepository.deleteById(id);
    }
}