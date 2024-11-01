package br.upe.sisepei.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.api.representation.NoticeRepresentation;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.service.*;
import br.upe.sisepei.core.notice.service.CreateNotice;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

	private final ListNotices listNotices;
	private final FindNoticesByAxle findNoticesByAxle;
	private final FindNoticeById findNoticeById;
	private final CreateNotice createNotice;
	private final DeleteNotice deleteNotice;
	private final UpdateNotice updateNotice;

	@GetMapping
	public ResponseEntity<List<NoticeRepresentation>> listNotices(){
		return ResponseEntity.ok(
				listNotices
				.execute()
				.stream()
				.map(this::convertToRepresentation)
				.collect(Collectors.toList())
		);
	}

	@GetMapping("axle/{axle}")
	public ResponseEntity<List<NoticeRepresentation>> listNoticesByAxle(@PathVariable AxleEnum axle) {
		return ResponseEntity.ok(
				findNoticesByAxle
						.execute(axle)
						.stream()
						.map(this::convertToRepresentation)
						.collect(Collectors.toList())
				);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findNoticeById(@PathVariable Long id){
			return ResponseEntity.ok(convertToRepresentation(findNoticeById.execute(id)));
	}

	@GetMapping("/{id}/file")
	public ResponseEntity<?> getNoticeFile(@PathVariable Long id){
			return ResponseEntity.ok(findNoticeById.execute(id).getFile());
	}

	@PostMapping
	public ResponseEntity<?> createNotice(
			@AuthenticationPrincipal User coordinator,
			@Valid @RequestBody NoticeDTO noticeDTO,
			BindingResult bindingResult
	){
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(String.join("; ", bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).toList()));
		}
			byte[] file = noticeDTO.getFile().getBytes(StandardCharsets.UTF_8);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(convertToRepresentation(createNotice.execute(noticeDTO, coordinator, file)));
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateNotice(
			@PathVariable Long id,
			@AuthenticationPrincipal User coordinator,
			@Valid @RequestBody NoticeDTO noticeDTO
	) {
			byte[] file = noticeDTO.getFile().getBytes(StandardCharsets.UTF_8);
			return ResponseEntity.ok(convertToRepresentation(updateNotice.execute(id, noticeDTO, coordinator, file)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(
			@AuthenticationPrincipal User coordinator,
			@PathVariable Long id
			){
			deleteNotice.execute(id, coordinator);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// TODO: Remover
	private NoticeRepresentation convertToRepresentation(Notice notice) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(notice, NoticeRepresentation.class);
	}
}