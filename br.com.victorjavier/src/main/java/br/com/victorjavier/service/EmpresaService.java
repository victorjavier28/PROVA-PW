package br.com.victorjavier.service;

import java.util.List;

import br.com.victorjavier.domain.Empresa;

public interface EmpresaService {
	
	void salvar(Empresa empresa);
	
	void editar(Empresa empresa);
	
	void excluir(Long id);
	
	Empresa buscarPorId(Long id);
	
	List<Empresa> buscarTodos();

	boolean empresaTemProfissoes(Long id);

	

}
