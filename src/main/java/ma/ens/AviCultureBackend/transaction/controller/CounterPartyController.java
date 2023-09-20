package ma.ens.AviCultureBackend.transaction.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.transaction.mapper.CounterPartyMapper;
import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import ma.ens.AviCultureBackend.transaction.model.dto.CounterPartyDto;
import ma.ens.AviCultureBackend.transaction.service.CounterPartyService;
import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/counter-parties")
@RequiredArgsConstructor
@Secured({UserRole.Role.ROLE_MANAGER_VALUE, UserRole.Role.ROLE_ADMIN_VALUE})
public class CounterPartyController {

	private final CounterPartyService counterPartyService;
	private final CounterPartyMapper counterPartyMapper;

	@GetMapping
	public List<CounterPartyDto> getAllCounterParties(@RequestParam(name = "type" , required = false) CounterParty.Type type) {
		if (type != null){
			return counterPartyMapper.toCounterPartyDtos(counterPartyService.getCounterPartiesByType(type));
		}
		return counterPartyMapper.toCounterPartyDtos(counterPartyService.getAllCounterParties());
	}

	@GetMapping("/{counterPartyId}")
	public CounterPartyDto getCounterPartyById(@PathVariable Long counterPartyId) throws NotFoundException {
		return counterPartyMapper.toCounterPartyDto(counterPartyService.getCounterPartyById(counterPartyId));
	}

	@PostMapping("/add")
	public CounterPartyDto addCounterParty(@Valid @RequestBody CounterPartyDto counterPartyDto) throws NotFoundException {
		return counterPartyMapper.toCounterPartyDto(counterPartyService.addCounterParty(counterPartyDto));
	}

	@DeleteMapping("/{counterPartyId}/delete")
	public void deleteCounterParty(@PathVariable Long counterPartyId) throws NotFoundException {
		counterPartyService.deleteCounterParty(counterPartyService.getCounterPartyById(counterPartyId));
	}

}
