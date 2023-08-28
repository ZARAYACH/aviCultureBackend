package ma.ens.AviCultureBackend.incident.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.incident.modal.Incident;
import ma.ens.AviCultureBackend.incident.modal.dto.IncidentDto;
import ma.ens.AviCultureBackend.incident.repository.IncidentRepo;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepo incidentRepo;
    private final UserService userService;

    public List<Incident> getAllIncidents() {
        return incidentRepo.findAll();
    }

    public Incident getIncidentById(Long id) throws NotFoundException {
        return incidentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Incident with id " + id + " not found"));
    }

    public Incident addIncident(IncidentDto incidentDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(incidentDto, "incidentDto provided is null");
        User reporter = userService.getUserById(incidentDto.reporterId());
        return incidentRepo.save(Incident.builder()
                .reporter(reporter)
                .summary(incidentDto.summary())
                .description(incidentDto.description())
                .date(incidentDto.date())
                .build());
    }

    public Incident modifyIncident(Incident incident, IncidentDto incidentDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(incident, "Incident provided is null");
        Assert.notNull(incidentDto, "IncidentDto provided is null");
        User reporter = userService.getUserById(incidentDto.reporterId());
        incident.setReporter(reporter);
        incident.setDescription(incidentDto.description());
        incident.setSummary(incidentDto.summary());
        incident.setDate(incidentDto.date());
        return incidentRepo.save(incident);
    }

    public void deleteIncident(Incident incident) throws IllegalArgumentException {
        incidentRepo.delete(incident);
    }

}
