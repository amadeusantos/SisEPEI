package br.upe.sisepei.api;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.api.representation.NoticeFullRepresentation;
import br.upe.sisepei.api.representation.NoticeRepresentation;
import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.useCases.*;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.upe.sisepei.core.notice.model.NoticeDTO;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

	private final ListNoticesUseCase listNoticesUseCase;
	private final FindNoticesByAxleUseCase findNoticesByAxleUseCase;
	private final FindNoticeByIdUseCase findNoticeByIdUseCase;
	private final CreateNoticeUseCase createNoticeUseCase;
	private final UpdateNoticeUseCase updateNoticeUseCase;
	private final DeleteNoticeUseCase deleteNoticeUseCase;

	@GetMapping
	public ResponseEntity<List<NoticeRepresentation>> listNotices(){
		return ResponseEntity.ok(listNoticesUseCase.execute().stream()
				.map(NoticeRepresentation::new).collect(Collectors.toList()));

	}

	@GetMapping("axle/{axle}")
	public ResponseEntity<List<NoticeRepresentation>> listNoticesByAxle(@PathVariable AxleEnum axle) {
		return ResponseEntity.ok(findNoticesByAxleUseCase.execute(axle).stream()
				.map(NoticeRepresentation::new).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findNoticeById(@PathVariable Long id){
		return ResponseEntity.ok(new NoticeFullRepresentation(findNoticeByIdUseCase.execute(id)));
	}

	@GetMapping("/{id}/file")
	public ResponseEntity<?> getNoticeFileById(@PathVariable Long id){
		return ResponseEntity.ok(findNoticeByIdUseCase.execute(id).getFile());
	}

	@PostMapping
	public ResponseEntity<?> createNotice(
			@AuthenticationPrincipal User user,
			@Valid @RequestBody NoticeDTO noticeDTO,
			BindingResult bindingResult
	) {
	if (bindingResult.hasFieldErrors()) {
		throw new UnprocessableEntityException("Error when create notice", bindingResult.getFieldErrors());
	}
		byte[] file = noticeDTO.getFile().getBytes(StandardCharsets.UTF_8);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new NoticeRepresentation(createNoticeUseCase.execute(noticeDTO, user.getId(), file)));
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateNotice(
			@PathVariable Long id,
			@AuthenticationPrincipal User user,
			@Valid @RequestBody NoticeDTO noticeDTO,
			BindingResult bindingResult
	) {
		if (bindingResult.hasFieldErrors()) {
			throw new UnprocessableEntityException("Error when edit notice", bindingResult.getFieldErrors());
		}
		byte[] file = noticeDTO.getFile().getBytes(StandardCharsets.UTF_8);
		return ResponseEntity.ok(new NoticeRepresentation(updateNoticeUseCase.execute(id, noticeDTO, user.getId(), file)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(
			@PathVariable Long id,
			@AuthenticationPrincipal User user
			) {
		deleteNoticeUseCase.execute(id, user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
