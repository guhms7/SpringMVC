package com.curso.spring.SpringBoot.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.spring.SpringBoot.Model.Pessoa;
import com.curso.spring.SpringBoot.Repository.PessoaRepository;

@Controller 
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/cadastroPessoa")
    public ModelAndView inicio(){
        ModelAndView andView = new ModelAndView("cadastro/cadastroPessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        andView.addObject("pessoas", pessoasIt);
        andView.addObject("pessoaObj", new Pessoa());
        return andView;
    }

    @GetMapping("/editarPessoa/{idPessoa}")
    public ModelAndView editar(@PathVariable("idPessoa") Long idpessoa){
        ModelAndView pessoaAndView = new ModelAndView("cadastro/cadastroPessoa");
        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
        pessoaAndView.addObject("pessoaObj", pessoa.orElse(new Pessoa()));
        return pessoaAndView ;
    }
    
    @GetMapping("/excluirPessoa/{idPessoa}")
    public String excluir(@PathVariable("idPessoa") Long idPessoa) {
        pessoaRepository.deleteById(idPessoa);
        return "redirect:/cadastroPessoa";
    }

    @PostMapping("/salvarpessoa")
    public String salvar(Pessoa pessoa){
        pessoaRepository.saveAndFlush(pessoa);
        return "redirect:/cadastroPessoa";
    }
    
    @PostMapping("/buscarNomePessoa")
    public ModelAndView buscarNome(@RequestParam("buscarNomePessoa") String buscarNomePessoa){
        ModelAndView andView = new ModelAndView("cadastro/cadastroPessoa");
        andView.addObject("pessoas", pessoaRepository.findByNome(buscarNomePessoa));
        andView.addObject("pessoaObj", new Pessoa());
        return andView;
    }
}
