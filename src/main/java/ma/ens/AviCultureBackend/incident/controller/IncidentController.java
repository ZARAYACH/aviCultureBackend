package ma.ens.AviCultureBackend.incident.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.incident.mapper.IncidentMapper;
import ma.ens.AviCultureBackend.incident.modal.Incident;
import ma.ens.AviCultureBackend.incident.modal.dto.IncidentDto;
import ma.ens.AviCultureBackend.incident.service.IncidentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
    private final IncidentMapper mapper;

    @GetMapping
    public List<IncidentDto> getIncidentServices() {
        return mapper.toIncidentDtos(incidentService.getAllIncidents());
    }

    @GetMapping("/{incidentId}")
    public IncidentDto getIncidentById(@PathVariable(name = "incidentId") Long id) throws NotFoundException {
        return mapper.toIncidentDto(incidentService
                .getIncidentById(id));
    }

    @PostMapping("/add")
    public IncidentDto addIncident(@Validated @RequestBody IncidentDto incidentDto) throws BadRequestExeption, NotFoundException {
        try {
            return mapper.toIncidentDto(
                    incidentService.addIncident(incidentDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{incidentId}/modify")
    public IncidentDto ModifyIncident(@PathVariable(name = "incidentId") Long id,
                                      @Validated @RequestBody IncidentDto incidentDto) throws BadRequestExeption, NotFoundException {
        try {
            Incident Incident = incidentService.getIncidentById(id);
            return mapper.toIncidentDto(incidentService
                    .modifyIncident(Incident, incidentDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{incidentId}/delete")
    public void deleteIncident(@PathVariable(name = "incidentId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            incidentService.deleteIncident(incidentService.getIncidentById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

}
