package com.curso.spring.SpringBoot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.SpringBoot.Model.Pessoa;


@Repository
@Transactional
public interface PessoaRepository extends JpaRepository <Pessoa, Long> {

    @Query(value = "select p from Pessoa p where p.nome like %?1%")
    List<Pessoa> findByNome(String nome);
    
}
