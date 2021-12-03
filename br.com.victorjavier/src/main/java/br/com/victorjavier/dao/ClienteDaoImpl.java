package br.com.victorjavier.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.victorjavier.domain.Cliente;

@Repository
public class ClienteDaoImpl extends AbstractDao<Cliente, Long> implements ClienteDao {
	public List<Cliente> findByNome(String nome){
		return findByParam("nome", nome);
	}
	
	public List<Cliente> findByProfissao(Integer profissao){
		return findByParam("profissao_id_fk", profissao);
	}
	
	public List<Cliente> findByDate(LocalDate data){
		return findByParam("data_cadastro", data);
	}
}
