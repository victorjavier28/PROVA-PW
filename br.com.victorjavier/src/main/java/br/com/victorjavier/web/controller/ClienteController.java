package br.com.victorjavier.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.victorjavier.domain.Cliente;
import br.com.victorjavier.domain.Empresa;
import br.com.victorjavier.domain.Profissao;
import br.com.victorjavier.domain.UF;
import br.com.victorjavier.service.ClienteService;
import br.com.victorjavier.service.EmpresaService;
import br.com.victorjavier.service.ProfissaoService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ProfissaoService profissaoService;
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "/cliente/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos() );
		return "/cliente/lista";
	}
	
	@PostMapping("/salvar")
	public String Salvar(Cliente cliente, RedirectAttributes attr) {
		clienteService.salvar(cliente);
		attr.addFlashAttribute("success", "Cliente cadastrado com sucesso.");
		return "redirect:/clientes/cadastrar";

	}
	
	@ModelAttribute("profissoes")
	public List<Profissao> getProfissaos(){
		return profissaoService.buscarTodos();
	
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs(){
		return UF.values();
		
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "/cliente/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Cliente cliente, RedirectAttributes attr) {
		clienteService.editar(cliente);
		attr.addFlashAttribute("success", "Cliente alterado(a) com sucesso.");
		return "redirect:/clientes/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
			clienteService.excluir(id);
			attr.addFlashAttribute("sucess", "Cliente excluido com sucesso.");
		
			return "redirect:/clientes/listar";

	}
	
	@GetMapping("/buscar/nome")
	public String buscarNome(String nome, ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarPorNome(nome) );
		return "/cliente/lista";
	}
	
	@GetMapping("/buscar/profissao")
	public String buscarProfissao(@RequestParam("profissao") Integer profissao, ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarPorProfissao(profissao) );
		return "/cliente/lista";
	}

	@GetMapping("/buscar/data")
	public String buscarDatas(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarPorData(data));
		return "/cliente/lista";
	}

}
