package com.avaliacao.crud.service;

import com.avaliacao.crud.dto.BeneficiarioRequestDTO;
import com.avaliacao.crud.model.Beneficiario;
import com.avaliacao.crud.model.Documento;
import com.avaliacao.crud.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoService documentoService;

    public List<Beneficiario> listAllBeneficiarios(){
        return beneficiarioRepository.findAll();
    }

    public Beneficiario addBeneficiario(BeneficiarioRequestDTO beneficiarioRequestDTO){

        Beneficiario beneficiario = new Beneficiario();

        beneficiario.setNome(beneficiarioRequestDTO.getNome());
        beneficiario.setTelefone(beneficiarioRequestDTO.getTelefone());
        beneficiario.setDataNascimento(LocalDate.parse(beneficiarioRequestDTO.getDataNascimento()));
        beneficiario.setDataInclusao(LocalDateTime.now());

        return beneficiarioRepository.save(beneficiario);
    }

    public void editBeneficiario(Long id, BeneficiarioRequestDTO beneficiarioRequestDTO) throws Exception {

        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(id);

        if(!beneficiario.isPresent()){
            throw new Exception("Nenhum beneficiário encontrado com esse id");
        }

        beneficiario.get().setNome(beneficiarioRequestDTO.getNome());
        beneficiario.get().setTelefone(beneficiarioRequestDTO.getTelefone());
        beneficiario.get().setDataNascimento(LocalDate.parse(beneficiarioRequestDTO.getDataNascimento()));
        beneficiario.get().setDataAtualizacao(LocalDateTime.now());

        beneficiarioRepository.save(beneficiario.get());
    }

    public void deleteBeneficiario(Long id) throws Exception {

        List<Documento> documentosByIdBeneficiario = documentoService.findByIdBeneficiario(id);

        if(!documentosByIdBeneficiario.isEmpty()){
            documentosByIdBeneficiario.forEach( d -> {
                try {
                    documentoService.deleteDocumento(d.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(id);

        if(!beneficiario.isPresent()){
            throw new Exception("Nenhum beneficiário encontrado com esse id");
        }

        beneficiarioRepository.delete(beneficiario.get());
    }

}
