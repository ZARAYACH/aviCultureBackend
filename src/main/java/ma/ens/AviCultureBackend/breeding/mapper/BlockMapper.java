package ma.ens.AviCultureBackend.breeding.mapper;

import ma.ens.AviCultureBackend.breeding.model.Block;
import ma.ens.AviCultureBackend.breeding.model.BlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BlockMapper {
    BlockDto toBlockDto(Block block);
    List<BlockDto> toBlockDtos(List<Block> blocks);

    Block toBlock(BlockDto blockDto);

}
