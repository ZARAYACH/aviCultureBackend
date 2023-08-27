package ma.ens.AviCultureBackend.document.controller;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.document.mapper.DocumentMapper;
import ma.ens.AviCultureBackend.document.modal.Document;
import ma.ens.AviCultureBackend.document.modal.dto.DocumentDto;
import ma.ens.AviCultureBackend.document.service.DocumentService;
import ma.ens.AviCultureBackend.exeption.BadRequestExeption;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        return documentMapper.toDocumentDtos(documentService.getAllDocuments());
    }

    @PostMapping("/add")
    public DocumentDto addDocument(@Validated @RequestBody DocumentDto DocumentDto) throws BadRequestExeption, NotFoundException {
        try {
            return documentMapper.toDocumentDto(
                    documentService.addDocument(DocumentDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @GetMapping("/{DocumentId}")
    public DocumentDto getDocumentById(@PathVariable(name = "DocumentId") String id) throws NotFoundException {
        return documentMapper.toDocumentDto(documentService
                .getDocumentById(id));
    }

    @PutMapping("/{DocumentId}/modify")
    public DocumentDto ModifyDocument(@PathVariable(name = "DocumentId") String id,
                                    @Validated @RequestBody DocumentDto DocumentDto) throws BadRequestExeption, NotFoundException {
        try {
            Document Document = documentService.getDocumentById(id);
            return documentMapper.toDocumentDto(documentService
                    .modifyDocument(Document, DocumentDto));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }

    @DeleteMapping("/{DocumentId}/delete")
    public void deleteDocument(@PathVariable(name = "DocumentId") String id) throws BadRequestExeption, NotFoundException {
        try {
            documentService.deleteDocument(documentService.getDocumentById(id));
        } catch (IllegalArgumentException e) {
            throw new BadRequestExeption(e.getMessage());
        }
    }
}
