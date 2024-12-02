package br.upe.sisepei.utils.functions;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.notice.model.NoticeDTO;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;
import br.upe.sisepei.core.user.model.User;
import br.upe.sisepei.utils.exceptions.ForbiddenException;

import java.util.List;
import java.util.Map;

public class ProfileVerifier {

    private static final Map<AxleEnum, ProfileEnum> roleMapping = Map.of(
            AxleEnum.EXTENSAO, ProfileEnum.COORDENADOR_EXTENSAO,
            AxleEnum.INOVACAO, ProfileEnum.COORDENADOR_INOVACAO,
            AxleEnum.PESQUISA, ProfileEnum.COORDENADOR_PESQUISA
    );

    public static boolean execute(AxleEnum axle, User coordinator) {
        List<ProfileEnum> role = coordinator.getProfiles().stream().map(Profile::getName).toList();

        return role.contains(roleMapping.get(axle));
    }

    public static void verifyPermissionCreateNotice(User user, NoticeDTO noticeDTO) {
        ProfileEnum roleRequired = roleMapping.get(noticeDTO.getAxle());

        if (user.getProfiles().stream().noneMatch(profile -> profile.getName().equals(roleRequired))) {
            throw new ForbiddenException("User does not have a profile to create a notice on this axle");
        }
    }
}