package ma.ens.AviCultureBackend.transaction.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import ma.ens.AviCultureBackend.transaction.model.dto.CounterPartyDto;
import ma.ens.AviCultureBackend.transaction.repository.CounterPartyRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounterPartyService {

    private final CounterPartyRepo counterPartyRepo;


    public List<CounterParty> getAllCounterParties() {
        return counterPartyRepo.findAll();
    }

    public CounterParty getCounterPartyById(Long id) throws NotFoundException {
        return counterPartyRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Bulb with id " + id + " not found"));
    }

    public CounterParty getCounterPartyByEmailAddress(String email) throws NotFoundException {
        return counterPartyRepo.findByEmailAddress(email)
                .orElseThrow(() -> new NotFoundException("Counter party with email " + email + " not found"));
    }

    public CounterParty addCounterParty(CounterPartyDto counterPartyDto) throws IllegalArgumentException {
        Assert.notNull(counterPartyDto, "counter Party Dto provided is null");
        Assert.isTrue(StringUtils.isNotBlank(counterPartyDto.emailAddress()) &&
                StringUtils.isNotBlank(counterPartyDto.name()),"Counter name and email address are required");

        if (counterPartyRepo.findByEmailAddress(counterPartyDto.emailAddress()).isPresent()) {
            throw new EntityExistsException("counter party with email " + counterPartyDto.emailAddress() + " Already exists");
        }
        return counterPartyRepo.save(CounterParty.builder()
                .name(counterPartyDto.name())
                .emailAddress(counterPartyDto.emailAddress())
                .type(counterPartyDto.type())
                .phoneNumber(counterPartyDto.phoneNumber())
                .suppliesType(counterPartyDto.suppliesType())
                .address(counterPartyDto.address())
                .build());
    }

    public CounterParty modifyCounterParty(CounterParty counterParty, CounterPartyDto counterPartyDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(counterParty, "Product bulb provided is null");
        Assert.notNull(counterPartyDto, "Product bulb dto provided is null");
        counterParty.setName(counterPartyDto.name());
        counterParty.setAddress(counterPartyDto.address());
        counterParty.setEmailAddress(counterPartyDto.emailAddress());
        counterParty.setSuppliesType(counterPartyDto.suppliesType());
        counterParty.setPhoneNumber(counterParty.getPhoneNumber());
        return counterPartyRepo.save(counterParty);
    }

    public void deleteCounterParty(CounterParty counterParty) throws IllegalArgumentException {
        counterPartyRepo.delete(counterParty);
    }
}
