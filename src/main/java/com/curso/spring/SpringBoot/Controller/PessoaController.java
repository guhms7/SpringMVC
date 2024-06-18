package com.curso.spring.SpringBoot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.curso.spring.SpringBoot.Model.Pessoa;
import com.curso.spring.SpringBoot.Repository.PessoaRepository;

@Controller 
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastroPessoa")
    public String inicio(){
        return "cadastro/cadastroPessoa";
    }

    
    @RequestMapping(method =RequestMethod.POST, value = "/salvarpessoa")
    public String salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return "cadastro/cadastroPessoa";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cadastroPessoas")
    public List<Pessoa> buscarTodos(){
        return pessoaRepository.findAll();
    }
    

    
}
