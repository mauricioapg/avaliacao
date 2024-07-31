package com.avaliacao.crud.controller;

import com.avaliacao.crud.dto.DocumentoRequestDTO;
import com.avaliacao.crud.model.Documento;
import com.avaliacao.crud.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> listAll(){
        System.out.println("Listando todos os documentos");
        return documentoService.listAllDocuments();
    }

    @GetMapping("beneficiario/id/{id}")
    public List<Documento> findByIdBeneficiario(@PathVariable Long id){
        System.out.println("Listando os documentos pelo id do beneficiário:" + id);
        return documentoService.findByIdBeneficiario(id);
    }

    @PostMapping
    public ResponseEntity<?> addDocumento(@RequestBody DocumentoRequestDTO documentoRequestDTO) {
        System.out.println("Iniciando inclusão de novo documento");

        try {
            documentoService.addDocumento(documentoRequestDTO);
            return ResponseEntity.ok("Documento cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editDocumento(@PathVariable Long id, @RequestBody DocumentoRequestDTO documentoRequestDTO){
        System.out.println("Iniciando alteração do documento id: " + id);

        try {
            documentoService.editDocumento(id, documentoRequestDTO);
            return ResponseEntity.ok("Documento alterado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDocumento(@PathVariable Long id){
        System.out.println("Iniciando remoção do documento id: " + id);

        try {
            documentoService.deleteDocumento(id);
            return ResponseEntity.ok("Documento removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
