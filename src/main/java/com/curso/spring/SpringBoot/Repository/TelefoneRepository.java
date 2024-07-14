package com.curso.spring.SpringBoot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.SpringBoot.Model.Telefone;


@Repository
@Transactional
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    
    @Query("Select t from Telefone t where t.pessoa.id =?1")
    public List<Telefone> getTelefones(Long pessoId);

}
