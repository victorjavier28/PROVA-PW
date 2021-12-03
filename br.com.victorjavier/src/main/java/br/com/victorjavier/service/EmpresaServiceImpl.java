package br.com.victorjavier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.victorjavier.dao.EmpresaDao;
import br.com.victorjavier.domain.Empresa;

@Service @Transactional(readOnly = false)
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDao dao;
	
	@Override
	public void salvar(Empresa empresa) {
		dao.save(empresa);
		
	}

	@Override
	public void editar(Empresa empresa) {
		dao.update(empresa);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override @Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}

	@Override
	public boolean empresaTemProfissoes(Long id) {
		if(buscarPorId(id).getProfissoes().isEmpty()) {
		return false;
	}
	return true;

	}
}
