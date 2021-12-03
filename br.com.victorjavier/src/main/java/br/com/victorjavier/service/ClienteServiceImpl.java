package br.com.victorjavier.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.victorjavier.dao.ClienteDao;
import br.com.victorjavier.domain.Cliente;

@Service @Transactional(readOnly = false)
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteDao dao;

	@Override
	public void salvar(Cliente cliente) {
		dao.save(cliente);
		
	}

	@Override
	public void editar(Cliente cliente) {
		dao.update(cliente);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override @Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}
	
	@Override
	public List<Cliente> buscarPorNome(String nome){
		return dao.findByNome(nome);
	}
	
	@Override
	public List<Cliente> buscarPorProfissao(Integer profissao){
		return dao.findByProfissao(profissao);
	}
	
	@Override
	public List<Cliente> buscarPorData(LocalDate data){
		return dao.findByDate(data);
	}

}
