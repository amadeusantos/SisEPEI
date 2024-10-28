package br.upe.sisepei.api;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.upe.sisepei.api.representation.NoticeRepresentation;
import br.upe.sisepei.core.notice.model.AxleEnum;
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
		return ResponseEntity.ok(convertToRepresentation(noticeService.findNoticeById(id)));
	}

	@GetMapping("/{id}/file")
	public ResponseEntity<?> getNoticeFile(@PathVariable Long id){
		return ResponseEntity.ok(noticeService.findNoticeById(id).getFile());
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createNotice(
			@AuthenticationPrincipal User coordinator,
			@ModelAttribute NoticeDTO noticeDTO
	) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(convertToRepresentation(noticeService.createNotice(noticeDTO, coordinator, noticeDTO.getFile())));
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateNotice(
			@PathVariable Long id,
			@AuthenticationPrincipal User coordinator,
			@Valid @RequestBody NoticeDTO noticeDTO,
			@RequestPart(value = "file", required = false) MultipartFile file
	) {
		return ResponseEntity.ok(convertToRepresentation(noticeService.updateNotice(id, noticeDTO, coordinator, file)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotice(
			@AuthenticationPrincipal User coordinator,
			@PathVariable Long id
			) throws Exception {
		noticeService.deleteNotice(id, coordinator);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private NoticeRepresentation convertToRepresentation(Notice notice) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(notice, NoticeRepresentation.class);
	}

}
