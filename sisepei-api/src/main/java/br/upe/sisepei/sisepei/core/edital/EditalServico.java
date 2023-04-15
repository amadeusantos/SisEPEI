package br.upe.sisepei.sisepei.core.edital;

import java.util.List;
import java.util.Optional;

import br.upe.sisepei.sisepei.config.JwtService;
import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import br.upe.sisepei.sisepei.core.perfil.modelo.Perfil;
import br.upe.sisepei.sisepei.core.usuario.UsuarioRepositorio;
import br.upe.sisepei.sisepei.core.usuario.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.core.edital.modelo.Edital;
import br.upe.sisepei.sisepei.core.edital.modelo.EditalDTO;

@Service
public class EditalServico {

	@Autowired
	private EditalRepository repositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private JwtService jwtService;

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

	public Edital criarEdital(EditalDTO edital, String token) throws Exception {
		if(repositorio.existsByTitulo(edital.getTitulo())){
			throw new ValidacaoException("Título já existente");
		}

		var autor = jwtService.extractUserEmail(token);

		Optional<Usuario> coordenador = usuarioRepositorio.findByEmail(autor);

		if (!eCoordenador(edital.getTipo(), coordenador.get())) {
			throw new Exception("Esse coordenador não faz parte desse eixo!");
		}

		Edital editalSalvar = new Edital();
		editalSalvar.setTitulo(edital.getTitulo());
		editalSalvar.setDescricao(edital.getDescricao());
		editalSalvar.setEdital(edital.getEdital());
		editalSalvar.setRequisitos(edital.getRequisitos());
		editalSalvar.setTipo(edital.getTipo());
		editalSalvar.setCoordenador(coordenador.get());
		editalSalvar.setPrazo(edital.getPrazo());

		return repositorio.save(editalSalvar);
	}

	public Edital updateEdital(Long id, EditalDTO edital, String token) throws NaoEncontradoException, ValidacaoException{
		Optional<Edital> editalExists = repositorio.findById(id);
		if(editalExists.isEmpty()) {
			throw new NaoEncontradoException("Não existe nenhum edital correspondente a esse id");
		}

		var autor = jwtService.extractUserEmail(token);

		if (!editalExists.get().getCoordenador().getEmail().equals(autor)) {
			throw  new ValidacaoException("Um edital só pode ser editado por seu proprietário!");
		}

		if(!(editalExists.get().getTitulo().equals(edital.getTitulo())) && repositorio.existsByTitulo(edital.getTitulo())){
			throw new ValidacaoException("Titulo ja existente");
		}

		Edital editalExistente = repositorio.findById(id).get();

		editalExistente.setTitulo(edital.getTitulo());
		editalExistente.setDescricao(edital.getDescricao());
		editalExistente.setEdital(edital.getEdital());
		editalExistente.setRequisitos(edital.getRequisitos());
		editalExistente.setTipo(edital.getTipo());
		editalExistente.setPrazo(edital.getPrazo());

		return repositorio.save(editalExistente);
	}

	public void removerEdital(Long id) throws NaoEncontradoException{
		Optional<Edital> edital = repositorio.findById(id);
		if(edital.isEmpty()) {
			throw new NaoEncontradoException("Não existe nenhum edital correspondente a esse id");
		}
		repositorio.deleteById(id);
	}

	private boolean eCoordenador(TipoEnum eixo, Usuario coordenador) {
		List<String> perfis= coordenador.getPerfis().stream().map(Perfil::getAuthority).toList();
		if (eixo == TipoEnum.EXTENSÃO) {
			return perfis.contains("COORDENADOR_EXTENSAO");
		} else if (eixo == TipoEnum.INOVAÇÃO) {
			return perfis.contains("COORDENADOR_INOVACAO");
		} else if (eixo == TipoEnum.PESQUISA) {
			return perfis.contains("COORDENADOR_PESQUISA");
		}

		return false;
	}
}


