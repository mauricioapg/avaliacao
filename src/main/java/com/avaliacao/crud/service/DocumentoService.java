package com.avaliacao.crud.service;

import com.avaliacao.crud.dto.DocumentoRequestDTO;
import com.avaliacao.crud.model.Documento;
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

    public List<Documento> listAllDocuments(){
        return documentoRepository.findAll();
    }

    public List<Documento> findByIdBeneficiario(Long idBeneficiario){
        return documentoRepository.findByIdBeneficiario(idBeneficiario);
    }

    public Documento addDocumento(DocumentoRequestDTO documentoRequestDTO) {

        Documento documento = new Documento();

        documento.setTipoDocumento(documentoRequestDTO.getTipoDocumento());
        documento.setDescricao(documentoRequestDTO.getDescricao());
        documento.setDataInclusao(LocalDateTime.now());
        documento.setIdBeneficiario(documentoRequestDTO.getIdBeneficiario());

        return documentoRepository.save(documento);
    }

    public Documento editDocumento(Long id, DocumentoRequestDTO documentoRequestDTO){

        Optional<Documento> documento = documentoRepository.findById(id);

        documento.get().setTipoDocumento(documentoRequestDTO.getTipoDocumento());
        documento.get().setDescricao(documentoRequestDTO.getDescricao());
        documento.get().setDataAtualizacao(LocalDateTime.now());
        documento.get().setIdBeneficiario(documentoRequestDTO.getIdBeneficiario());

        return documentoRepository.save(documento.get());
    }

    public void deleteDocumento(Long id){

        Optional<Documento> documento = documentoRepository.findById(id);
        documentoRepository.delete(documento.get());
    }

}
