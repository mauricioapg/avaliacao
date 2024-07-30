package com.avaliacao.crud.repository;

import com.avaliacao.crud.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByIdBeneficiario(Long idBeneficiario);

}
