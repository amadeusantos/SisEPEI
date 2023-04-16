package br.upe.sisepei.sisepei.api;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import br.upe.sisepei.sisepei.api.representation.EditalRepresentation;
import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.base.exception.ValidacaoException;
import br.upe.sisepei.sisepei.core.edital.EditalServico;
import br.upe.sisepei.sisepei.core.edital.modelo.Edital;
import br.upe.sisepei.sisepei.core.edital.modelo.EditalDTO;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/edital")
public class EditalController {

	@Autowired
	private EditalServico editalServico;

	@GetMapping
	public ResponseEntity<List<EditalRepresentation>> listarEditais(){
		return ResponseEntity.ok(editalServico.listarEditais().stream()
				.map(this::converter).collect(Collectors.toList()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarEdital(@PathVariable Long id){
		try {
			return ResponseEntity.ok(converter(editalServico.buscarEdital(id)));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PostMapping
	public ResponseEntity<?> criarEdital(
			@RequestHeader(name = "Authorization", required = true) String token,
			@RequestParam(value = "titulo", required = true) String titulo,
			@RequestParam(value = "descricao", required = true) String descricao,
			@RequestParam(value = "requisitos", required = true) String requisitos,
			@RequestParam(value = "prazo", required = true) String prazo,
			@RequestParam(value = "tipo", required = true) TipoEnum tipo,
			@RequestPart(value = "arquivo", required = true) MultipartFile arquivo
	){
		try {
			String jwt =  token.substring(7);
			DateFormatter df = new DateFormatter("dd/mm/yyyy");
			EditalDTO editalDTO = new EditalDTO();
			editalDTO.setTitulo(titulo);
			editalDTO.setDescricao(descricao);
			editalDTO.setRequisitos(requisitos);
			editalDTO.setTipo(tipo);
			byte[] convertImage;
			convertImage =  arquivo.getBytes();
			editalDTO.setEdital(convertImage);
			editalDTO.setPrazo(df.parse(prazo, Locale.getDefault()));
			Edital result = editalServico.criarEdital(editalDTO, jwt);
			return ResponseEntity.status(HttpStatus.CREATED).body(converter(result));

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateEdital(
			@PathVariable Long id,
			@RequestHeader(name = "Authorization", required = true) String token,
			@RequestParam(value = "titulo", required = true) String titulo,
			@RequestParam(value = "descricao", required = true) String descricao,
			@RequestParam(value = "requisitos", required = true) String requisitos,
			@RequestParam(value = "prazo", required = true) String prazo,
			@RequestParam(value = "tipo", required = true) TipoEnum tipo,
			@RequestPart(value = "arquivo", required = true) MultipartFile arquivo
	) throws ValidacaoException {
		try{
			String jwt =  token.substring(7);
			DateFormatter df = new DateFormatter("dd/mm/yyyy");
			EditalDTO editalDTO = new EditalDTO();
			editalDTO.setTitulo(titulo);
			editalDTO.setDescricao(descricao);
			editalDTO.setRequisitos(requisitos);
			editalDTO.setTipo(tipo);
			byte[] convertImage;
			convertImage =  arquivo.getBytes();
			editalDTO.setEdital(convertImage);
			editalDTO.setPrazo(df.parse(prazo, Locale.getDefault()));
			return ResponseEntity.ok(converter(editalServico.updateEdital(id, editalDTO, jwt)));
		} catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEdital(@PathVariable Long id){
		try {
			editalServico.removerEdital(id);
		} catch(NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private EditalRepresentation converter(Edital edital) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(edital, EditalRepresentation.class);
	}

}
