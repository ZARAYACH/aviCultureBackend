package ma.ens.AviCultureBackend.document.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.document.modal.DocumentType;
import ma.ens.AviCultureBackend.document.modal.dto.DocumentTypeDto;
import ma.ens.AviCultureBackend.document.repository.DocumentTypeRepo;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepo documentTypeRepo;

    public List<DocumentType> getAllDocuments() {
        return documentTypeRepo.findAll();
    }

    public DocumentType getDocumentTypeById(Long id) throws NotFoundException {
        return documentTypeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Document with id " + id + " not found"));
    }

    public DocumentType addDocumentType(DocumentTypeDto documentTypeDto) throws IllegalArgumentException {
        Assert.notNull(documentTypeDto, "document type provided is null");
        return documentTypeRepo.save(DocumentType.builder().name(documentTypeDto.name()).build());
    }

    public DocumentType modifyDocument(DocumentType documentType, DocumentTypeDto documentTypeDto) {
        Assert.notNull(documentTypeDto, "Document Type dto provided is null");
        Assert.notNull(documentType, "Document Type provided is null");
        documentType.setName(documentTypeDto.name());
        return documentTypeRepo.save(documentType);
    }

    public void deleteDocumentType(DocumentType documentType) throws IllegalArgumentException {
        documentTypeRepo.delete(documentType);
    }
}
