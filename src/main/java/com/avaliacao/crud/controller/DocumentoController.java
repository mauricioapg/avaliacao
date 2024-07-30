package com.avaliacao.crud.controller;

import com.avaliacao.crud.dto.DocumentoRequestDTO;
import com.avaliacao.crud.model.Documento;
import com.avaliacao.crud.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Documento addDocumento(@RequestBody DocumentoRequestDTO documentoRequestDTO) {
        System.out.println("Iniciando inclusão de novo documento");
        return documentoService.addDocumento(documentoRequestDTO);
    }

    @PutMapping("{id}")
    public Documento editDocumento(@PathVariable Long id, @RequestBody DocumentoRequestDTO documentoRequestDTO){
        System.out.println("Iniciando atualização do documento id: " + id);
        return documentoService.editDocumento(id, documentoRequestDTO);
    }

    @DeleteMapping("{id}")
    public void deleteDocumento(@PathVariable Long id){
        System.out.println("Iniciando remoção do documento id: " + id);
        documentoService.deleteDocumento(id);
    }
}
