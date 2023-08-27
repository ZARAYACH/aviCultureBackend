package ma.ens.AviCultureBackend.document.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.document.modal.Document;
import ma.ens.AviCultureBackend.document.modal.DocumentType;
import ma.ens.AviCultureBackend.document.modal.dto.DocumentDto;
import ma.ens.AviCultureBackend.document.repository.DocumentRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepo documentRepo;
    private final DocumentTypeService documentTypeService;

    public List<Document> getAllDocuments() {
        return documentRepo.findAll();
    }

    public Document getDocumentById(String id) throws NotFoundException {
        return documentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Document with id " + id + " not found"));
    }

    public Document addDocument(DocumentDto documentDto) throws IllegalArgumentException, NotFoundException {
        Assert.notNull(documentDto, "document provided is null");
        DocumentType documentType = documentTypeService.getDocumentTypeById(documentDto.typeId());
        return documentRepo.save(Document.builder()
                .type(documentType)
                .description(documentDto.description())
                .name(documentDto.name())
                .expirationDate(documentDto.expirationDate())
                .build());
    }

    public Document modifyDocument(Document document, DocumentDto documentDto) throws NotFoundException {
        Assert.notNull(documentDto, "breeding center provided is null");
        Assert.notNull(documentDto, "breeding center provided is null");
        DocumentType documentType = documentTypeService.getDocumentTypeById(documentDto.typeId());
        document.setDescription(documentDto.description());
        document.setName(documentDto.name());
        document.setExpirationDate(documentDto.expirationDate());
        document.setType(documentType);
        return documentRepo.save(document);
    }

    public void deleteDocument(Document document) throws IllegalArgumentException {
        documentRepo.delete(document);
    }
}
