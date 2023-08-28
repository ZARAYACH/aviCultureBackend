package ma.ens.AviCultureBackend.Sanction.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.incident.modal.Sanction;
import ma.ens.AviCultureBackend.incident.modal.dto.SanctionDto;
import ma.ens.AviCultureBackend.incident.repository.SanctionRepo;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SanctionService {

    private final SanctionRepo sanctionRepo;
    private final UserService userService;

    public List<Sanction> getAllSanction() {
        return sanctionRepo.findAll();
    }

    public Sanction getSanctionById(Long id) throws NotFoundException {
        return sanctionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Sanction with id " + id + " not found"));
    }

    public Sanction addSanction(SanctionDto sanctionDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(sanctionDto, "SanctionDto provided is null");
        List<User> sanctionedUsers = userService.getUserByIds(sanctionDto.sanctionedUserIds());
        return sanctionRepo.save(Sanction.builder()
                .sanctionedUsers(sanctionedUsers)
                .summary(sanctionDto.summary())
                .description(sanctionDto.description())
                .startDate(sanctionDto.startDate())
                .endDate(sanctionDto.endDate())
                .build());
    }

    public Sanction modifySanction(Sanction sanction, SanctionDto sanctionDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(sanction, "Sanction provided is null");
        Assert.notNull(sanctionDto, "SanctionDto provided is null");
        List<User> sanctionedUsers = userService.getUserByIds(sanctionDto.sanctionedUserIds());
        sanction.setSanctionedUsers(sanctionedUsers);
        sanction.setDescription(sanctionDto.description());
        sanction.setSummary(sanctionDto.summary());
        sanction.setStartDate(sanctionDto.startDate());
        sanction.setEndDate(sanctionDto.endDate());
        return sanctionRepo.save(sanction);
    }

    public void deleteSanction(Sanction sanction) throws IllegalArgumentException {
        sanctionRepo.delete(sanction);
    }
}
