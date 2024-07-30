package com.avaliacao.crud.controller;

import com.avaliacao.crud.dto.BeneficiarioRequestDTO;
import com.avaliacao.crud.model.Beneficiario;
import com.avaliacao.crud.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Beneficiario editBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioRequestDTO beneficiarioRequestDTO){
        System.out.println("Iniciando atualização do beneficiário id: " + id);
        return beneficiarioService.editBeneficiario(id, beneficiarioRequestDTO);
    }

    @DeleteMapping("{id}")
    public void deleteBeneficiario(@PathVariable Long id){
        System.out.println("Iniciando remoção do beneficiário id: " + id);
        beneficiarioService.deleteBeneficiario(id);
    }
}
