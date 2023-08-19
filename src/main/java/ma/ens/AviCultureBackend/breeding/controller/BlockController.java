package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BlockMapper;
import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.breeding.model.BlockDto;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-centers")
@RestController
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;
    private final BlockMapper blockMapper;

    @GetMapping
    public List<BlockDto> getAllBreedingCenters() {
        return blockMapper.toBlockDtos(blockService.getAllBlocks());
    }

    @PostMapping("/add")
    public BlockDto ModifyBreedingCenter(@RequestBody BlockDto BlockDto) throws BadRequestExeption {
        try {
            return blockMapper.toBlockDto(blockService
                    .addBlock(blockMapper.toBlock(BlockDto)));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{blockId}/modify")
    public BlockDto ModifyBreedingCenter(@PathVariable(name = "blockId") Long id,
                                         @RequestBody BlockDto BlockDto) throws BadRequestExeption, NotFoundException {
        try {
            Block block = blockService.getBlockById(id);
            return blockMapper.toBlockDto(blockService
                    .modifyBlock(block, BlockDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{breedingCenterId}/delete")
    public void ModifyBreedingCenter(@PathVariable(name = "breedingCenterId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            blockService.deleteBlock(blockService.getBlockById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
