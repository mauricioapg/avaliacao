package com.avaliacao.crud.service;

import com.avaliacao.crud.dto.DocumentoRequestDTO;
import com.avaliacao.crud.model.Beneficiario;
import com.avaliacao.crud.model.Documento;
import com.avaliacao.crud.repository.BeneficiarioRepository;
import com.avaliacao.crud.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    public List<Documento> listAllDocuments(){
        return documentoRepository.findAll();
    }

    public List<Documento> findByIdBeneficiario(Long idBeneficiario){
        return documentoRepository.findByIdBeneficiario(idBeneficiario);
    }

    public void addDocumento(DocumentoRequestDTO documentoRequestDTO) throws Exception {

        Documento documento = new Documento();

        documento.setTipoDocumento(documentoRequestDTO.getTipoDocumento());
        documento.setDescricao(documentoRequestDTO.getDescricao());
        documento.setDataInclusao(LocalDateTime.now());
        documento.setIdBeneficiario(documentoRequestDTO.getIdBeneficiario());

        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(documentoRequestDTO.getIdBeneficiario());

        if(!beneficiario.isPresent()){
            throw new Exception("Nenhum beneficiário encontrado com esse id");
        }

        documentoRepository.save(documento);
    }

    public void editDocumento(Long id, DocumentoRequestDTO documentoRequestDTO) throws Exception {

        Optional<Documento> documento = documentoRepository.findById(id);

        if(!documento.isPresent()){
            throw new Exception("Nenhum documento encontrado com esse id");
        }

        documento.get().setTipoDocumento(documentoRequestDTO.getTipoDocumento());
        documento.get().setDescricao(documentoRequestDTO.getDescricao());
        documento.get().setDataAtualizacao(LocalDateTime.now());
        documento.get().setIdBeneficiario(documentoRequestDTO.getIdBeneficiario());

        documentoRepository.save(documento.get());
    }

    public void deleteDocumento(Long id) throws Exception {

        Optional<Documento> documento = documentoRepository.findById(id);

        if(!documento.isPresent()){
            throw new Exception("Nenhum documento encontrado com esse id");
        }

        documentoRepository.delete(documento.get());
    }

}
