package br.upe.sisepei.sisepei.core.edital;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.edital.modelo.Edital;
import br.upe.sisepei.sisepei.core.edital.modelo.EditalDTO;

@Service
public class EditalServico {

	@Autowired
	private EditalRepository repositorio;
	
	public List<Edital> listarEditais(){
		return repositorio.findAll();
	}
	
	public Edital buscarEdital(Long id) throws NaoEncontradoException {
		Optional<Edital> edital = repositorio.findById(id);
		if(edital.isEmpty()) {
			throw new NaoEncontradoException("Não existe nenhum edital correspondente a esse id");
		}else {			
			return edital.get();
		}
	}
	
	public Edital criarEdital(EditalDTO edital) {
		Edital editalSalvar = new Edital();
		editalSalvar.setTitulo(edital.getTitulo());
		editalSalvar.setDescricao(edital.getDescricao());
		editalSalvar.setEdital(edital.getEdital());
		editalSalvar.setRequisitos(edital.getRequisitos());
		editalSalvar.setCoordenador(edital.getCoordenador());
		editalSalvar.setPrazo(edital.getPrazo());
		
		return repositorio.save(editalSalvar);
	}
	
	/*public Edital updateEdital(Long id, EditalDTO edital) throws NaoEncontradoException{
		Optional<Edital> editalExists = repositorio.findById(id);
		if(editalExists.isEmpty()) {
			throw new NaoEncontradoException("Não existe nenhum edital correspondente a esse id");
		}
		
		
	}*/
		
	public void removerEdital(Long id) throws NaoEncontradoException{
		Optional<Edital> edital = repositorio.findById(id);
		if(edital.isEmpty()) {
			throw new NaoEncontradoException("Não existe nenhum edital correspondente a esse id");
		}
		repositorio.deleteById(id);
	}
}
			
	
