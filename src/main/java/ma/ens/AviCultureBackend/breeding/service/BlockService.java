package ma.ens.AviCultureBackend.breeding.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.breeding.model.BlockDto;
import ma.ens.AviCultureBackend.breeding.repository.BlockRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepo blockRepo;

    public List<Block> getAllBlocks() {
        return blockRepo.findAll();
    }

    public Block getBlockById(Long id) throws NotFoundException {
        return blockRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("breeding block with id " + id + " not found"));
    }

    public Block addBlock(Block block) throws IllegalArgumentException {
        Assert.notNull(block, "breeding center provided is null");
        return blockRepo.save(block);
    }

    public Block modifyBlock(Block block, BlockDto blockDto) {
        Assert.notNull(blockDto, "breeding center provided is null");
        block.setDailyMortality(blockDto.dailyMortality());
//        block.setBuilding(); TODO://figure out later
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
