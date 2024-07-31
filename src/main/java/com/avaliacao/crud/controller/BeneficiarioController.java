package com.avaliacao.crud.controller;

import com.avaliacao.crud.dto.BeneficiarioRequestDTO;
import com.avaliacao.crud.model.Beneficiario;
import com.avaliacao.crud.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @GetMapping
    public List<Beneficiario> listAll(){
        System.out.println("Listando todos os beneficiários");
        return beneficiarioService.listAllBeneficiarios();
    }

    @PostMapping
    public Beneficiario addBeneficiario(@RequestBody BeneficiarioRequestDTO beneficiarioRequestDTO){
        System.out.println("Iniciando inclusão de novo beneficiário");
        return beneficiarioService.addBeneficiario(beneficiarioRequestDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> editBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioRequestDTO beneficiarioRequestDTO){
        System.out.println("Iniciando alteração do beneficiário id: " + id);

        try {
            beneficiarioService.editBeneficiario(id, beneficiarioRequestDTO);
            return ResponseEntity.ok("Beneficiário alterado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBeneficiario(@PathVariable Long id){
        System.out.println("Iniciando remoção do beneficiário id: " + id);

        try {
            beneficiarioService.deleteBeneficiario(id);
            return ResponseEntity.ok("Beneficiário removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
