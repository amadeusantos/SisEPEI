package br.upe.sisepei.api;

import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.api.representation.NoticeRepresentation;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import br.upe.sisepei.utils.exceptions.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.core.notice.NoticeService;
import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping
	public ResponseEntity<List<NoticeRepresentation>> listNotices(){
		return ResponseEntity.ok(noticeService.listNotices().stream()
				.map(this::convertToRepresentation).collect(Collectors.toList()));

	}

	@GetMapping("axle/{axle}")
	public ResponseEntity<List<NoticeRepresentation>> listNoticesByAxle(@PathVariable AxleEnum axle) {
		return ResponseEntity.ok(noticeService.findNoticesByAxle(axle).stream()
				.map(this::convertToRepresentation).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findNoticeById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(convertToRepresentation(noticeService.findNoticeById(id)));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/file")
	public ResponseEntity<?> getNoticeFile(@PathVariable Long id){
		try {
			return ResponseEntity.ok(noticeService.findNoticeById(id).getFile());
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(consumes = {"multipart/form-data"})
	public ResponseEntity<?> createNotice(
			@AuthenticationPrincipal Usuario coordinator,
			@RequestBody NoticeDTO noticeDTO,
			@RequestPart(value = "file") MultipartFile file
	){
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(convertToRepresentation(noticeService.createNotice(noticeDTO, coordinator, file)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateNotice(
			@PathVariable Long id,
			@AuthenticationPrincipal Usuario coordinator,
			@RequestBody NoticeDTO noticeDTO,
			@RequestPart(value = "file") MultipartFile file
	) {
		try{
			return ResponseEntity.ok(convertToRepresentation(noticeService.updateNotice(id, noticeDTO, coordinator, file)));
		} catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(
			@AuthenticationPrincipal Usuario coordinator,
			@PathVariable Long id
			){
		try {
			noticeService.deleteNotice(id, coordinator);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private NoticeRepresentation convertToRepresentation(Notice notice) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(notice, NoticeRepresentation.class);
	}

}
