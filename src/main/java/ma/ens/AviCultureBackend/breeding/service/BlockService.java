package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BlockMapper;
import ma.ens.AviCultureBackend.breeding.modal.Block;
import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.dto.BlockDto;
import ma.ens.AviCultureBackend.breeding.repository.BlockRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepo blockRepo;
    private final BuildingService buildingService;
    private final BlockMapper blockMapper;

    public List<Block> getAllBlocks() {
        return blockRepo.findAll();
    }

    public Block getBlockById(Long id) throws NotFoundException {
        return blockRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding block with id " + id + " not found"));
    }

    public Block addBlock(BlockDto blockDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(blockDto, "breeding center provided is null");
        Building building = buildingService.getBuildingById(blockDto.buildingId());
        Block block = blockMapper.toBlock(blockDto);
        block.setBuilding(building);
        return blockRepo.save(block);
    }

    public Block modifyBlock(Block block, BlockDto blockDto) throws NotFoundException {
        Assert.notNull(blockDto, "breeding center dto provided is null");
        Assert.notNull(block, "breeding center provided is null");
        block.setDailyMortality(blockDto.dailyMortality());
        block.setBuilding(buildingService.getBuildingById(blockDto.buildingId()));
        block.setDailyGasCylinder(blockDto.dailyGasCylinder());
        block.setFoodNature(blockDto.foodNature());
        block.setWeightFirstWeek(blockDto.weightFirstWeek());
        block.setWeightEveryFeeding(blockDto.weightEveryFeeding());
        block.setWeightByTheEnd(blockDto.weightByTheEnd());
        block.setFoodQuantity(blockDto.foodQuantity());
        return blockRepo.save(block);
    }

    public void deleteBlock(Block block) throws IllegalArgumentException {
        blockRepo.delete(block);
    }
}
