package ma.ens.AviCultureBackend.breeding.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.breeding.mapper.BlockMapper;
import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.breeding.model.dto.BlockDto;
import ma.ens.AviCultureBackend.breeding.service.BlockService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/breeding-blocks")
@RestController
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;
    private final BlockMapper blockMapper;

    @GetMapping
    public List<BlockDto> getAllBreedingBlocks() {
        return blockMapper.toBlockDtos(blockService.getAllBlocks());
    }

    @PostMapping("/add")
    public BlockDto addBreedingBlooks(@Validated @RequestBody BlockDto blockDto) throws BadRequestExeption, NotFoundException {
        try {
            return blockMapper.toBlockDto(blockService
                    .addBlock(blockDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @PutMapping("/{blockId}/modify")
    public BlockDto ModifyBreedingBlocks(@PathVariable(name = "blockId") Long id,
                                         @Validated @RequestBody BlockDto blockDto) throws BadRequestExeption, NotFoundException {
        try {
            Block block = blockService.getBlockById(id);
            return blockMapper.toBlockDto(blockService
                    .modifyBlock(block, blockDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{breedingCenterId}/delete")
    public void deleteBreedingBlock(@PathVariable(name = "breedingCenterId") Long id) throws BadRequestExeption, NotFoundException {
        try {
            blockService.deleteBlock(blockService.getBlockById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
