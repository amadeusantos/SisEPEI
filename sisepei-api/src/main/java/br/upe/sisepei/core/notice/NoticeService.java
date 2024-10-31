package br.upe.sisepei.core.notice;

import java.io.IOException;
import java.util.List;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.NotFoundException;
import br.upe.sisepei.utils.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.upe.sisepei.core.notice.model.Notice;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository repository;

	public List<Notice> listNotices(){
		return repository.findAll();
	}

	public List<Notice> findNoticesByAxle(AxleEnum axle) {
		return repository.findAllByAxle(axle);
	}

	public Notice findNoticeById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Notice not found"));
	}

	public Notice createNotice(NoticeDTO noticeDTO, User coordinator, byte[] file) throws IOException, ValidationException {

		if (isCoordinator(noticeDTO.getAxle(), coordinator)) {
			throw  new ValidationException("User not authorized to create notice for axle");
		}

		Notice notice = convertToModel(noticeDTO);
		notice.setCoordinator(coordinator);
		notice.setFile(file);
		return repository.save(notice);
	}

	//TODO AS: IOException
	public Notice updateNotice(Long id, NoticeDTO noticeDTO, User coordinator, byte[] file) {
		Notice notice = findNoticeById(id);

		if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
			throw  new ValidationException("User not authorized to edit notice");
		}

		notice.setFile(file);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(noticeDTO, notice);

		return repository.save(notice);
	}

	public void deleteNotice(Long id, User coordinator) throws Exception {
		Notice notice = findNoticeById(id);

		if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
			throw new Exception("User not authorized to delete notice");
		}
		repository.deleteById(id);
	}

	private boolean isCoordinator(AxleEnum axle, User coordinator) {
		List<String> role = coordinator.getProfiles().stream().map(Profile::getAuthority).toList();
		if (axle == AxleEnum.EXTENSAO) {
			return role.contains("COORDENADOR_EXTENSAO");
		} else if (axle == AxleEnum.INOVACAO) {
			return role.contains("COORDENADOR_INOVACAO");
		} else if (axle == AxleEnum.PESQUISA) {
			return role.contains("COORDENADOR_PESQUISA");
		}

		return false;
	}

	private Notice convertToModel(NoticeDTO noticeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(noticeDTO, Notice.class);
	}
}


