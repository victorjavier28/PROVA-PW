package br.com.victorjavier.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.victorjavier.domain.Empresa;
import br.com.victorjavier.domain.Profissao;
import br.com.victorjavier.service.EmpresaService;
import br.com.victorjavier.service.ProfissaoService;

@Controller
@RequestMapping("/profissoes")
public class ProfissaoController {
	
	@Autowired
	private ProfissaoService profissaoService;
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Profissao profissao) {
		return "/profissao/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissoes", profissaoService.buscarTodos());
		return "/profissao/lista";
	}
	
	@PostMapping("/salvar")
	public String Salvar(Profissao profissao, RedirectAttributes attr) {
		profissaoService.salvar(profissao);
		attr.addFlashAttribute("success", "Profissoal inserido com sucesso.");
		return "redirect:/profissoes/cadastrar";

	}
	
	@ModelAttribute("empresas")
	public List<Empresa> listaDeEmpresas(){
		return empresaService.buscarTodos();
		
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissao", profissaoService.buscarPorId(id));
		return "/profissao/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Profissao profissao, RedirectAttributes attr) {
		profissaoService.editar(profissao);
		attr.addFlashAttribute("success", "Profissão alterada com sucesso.");
		return "redirect:/profissoes/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if(profissaoService.profissaoTemCliente(id)) {
			model.addAttribute("fail", "Profissão não removido. Possui cliente(s) vinculado(s)");
			
			
		}else {
			profissaoService.excluir(id);
			model.addAttribute("sucess", "Profissão excluido com sucesso.");
		}
		return listar(model);

	}
	
}