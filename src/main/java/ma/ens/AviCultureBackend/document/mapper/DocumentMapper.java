package ma.ens.AviCultureBackend.document.mapper;

import ma.ens.AviCultureBackend.document.modal.Document;
import ma.ens.AviCultureBackend.document.modal.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DocumentMapper {

    List<DocumentDto> toDocumentDtos(List<Document> allDocuments);

    @Mapping(source = "type.id", target = "typeId")
    DocumentDto toDocumentDto(Document document);

}
