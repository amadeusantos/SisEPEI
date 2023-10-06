package br.upe.sisepei.core.notice;

import java.io.IOException;
import java.util.List;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.usuario.modelo.Usuario;
import br.upe.sisepei.utils.exceptions.NaoEncontradoException;
import br.upe.sisepei.utils.exceptions.ValidacaoException;
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

	public Notice findNoticeById(Long id) throws NaoEncontradoException {
		return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Notice not found"));
	}

	public Notice createNotice(NoticeDTO noticeDTO, Usuario coordinator, MultipartFile file) throws IOException, ValidacaoException {

		if (isCoordinator(noticeDTO.getAxle(), coordinator)) {
			throw  new ValidacaoException("User not authorized to create notice for axle");
		}

		Notice notice = convertToModel(noticeDTO);
		notice.setCoordinator(coordinator);
		notice.setFile(file.getBytes());
		return repository.save(notice);
	}

	//TODO AS: IOException
	public Notice updateNotice(Long id, NoticeDTO noticeDTO, Usuario coordinator, MultipartFile file) throws NaoEncontradoException, ValidacaoException, IOException {
		Notice notice = findNoticeById(id);

		if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
			throw  new ValidacaoException("User not authorized to edit notice");
		}

		if (!file.isEmpty()) {
			notice.setFile(file.getBytes());
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(noticeDTO, notice);

		return repository.save(notice);
	}

	public void deleteNotice(Long id, Usuario coordinator) throws Exception {
		Notice notice = findNoticeById(id);

		if (!notice.getCoordinator().getEmail().equals(coordinator.getEmail())) {
			throw new Exception("User not authorized to delete notice");
		}
		repository.deleteById(id);
	}

	private boolean isCoordinator(AxleEnum axle, Usuario coordinator) {
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


