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
import com.curso.spring.SpringBoot.Model.Telefone;
import com.curso.spring.SpringBoot.Repository.PessoaRepository;
import com.curso.spring.SpringBoot.Repository.TelefoneRepository;

@Controller 
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

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

    @GetMapping("/telefones/{idPessoa}")
    public ModelAndView telefones(@PathVariable("idPessoa") Long idpessoa){
        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
        ModelAndView pessoaAndView = new ModelAndView("cadastro/telefones");
        pessoaAndView.addObject("pessoaObj", pessoa.get());
        pessoaAndView.addObject("telefones", telefoneRepository.getTelefones(idpessoa));
        return pessoaAndView ;
    }

    @PostMapping("/adicionarNumero/{idPessoa}")
    public ModelAndView adicionarNumero(Telefone telefone, @PathVariable("idPessoa") Long idPessoa) {
        ModelAndView modelAndView = new ModelAndView("cadastro/telefones");

        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        telefone.setPessoa(pessoa);
        telefoneRepository.save(telefone);
        modelAndView.addObject("pessoaObj", pessoa);
        modelAndView.addObject("telefones", pessoa.getTelefones());
        
        return modelAndView;
    }

    @PostMapping("/editarTelefone/{idTelefone}")
    public ModelAndView editarTelefone(@PathVariable("idTelefone") Long idtelefone){
        ModelAndView telefoneAndView = new ModelAndView("cadastro/telefones");
        Optional<Telefone> telefone = telefoneRepository.findById(idtelefone);
        telefoneAndView.addObject("telefone", telefone.orElse(new Telefone()));
        return telefoneAndView ;
    }

    @GetMapping("/excluirTelefone/{idTelefone}")
    public String excluirTelefone(@PathVariable("idTelefone") Long idTelefone) {
        telefoneRepository.deleteById(idTelefone);
        return "redirect:/telefones";
    }
}


