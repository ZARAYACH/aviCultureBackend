package ma.ens.AviCultureBackend.medical.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.medical.modal.Disease;
import ma.ens.AviCultureBackend.medical.repository.DiseaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepo diseaseRepo;

    public Disease getDiseaseById(Long id) throws NotFoundException {
        return diseaseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("disease with id " + id + " not found"));
    }

    public List<Disease> getDiseasesByIds(List<Long> ids) throws NotFoundException {
        return diseaseRepo.findAllById(ids);
    }
}
