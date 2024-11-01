package br.upe.sisepei.utils.functions;

import br.upe.sisepei.core.notice.model.AxleEnum;
import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.user.model.User;

import java.util.List;
import java.util.Map;

public class RoleVerifier {

    private static final Map<AxleEnum, String> roleMapping = Map.of(
            AxleEnum.EXTENSAO, "COORDENADOR_EXTENSAO",
            AxleEnum.INOVACAO, "COORDENADOR_INOVACAO",
            AxleEnum.PESQUISA, "COORDENADOR_PESQUISA"
    );

    public static boolean roleVerifier(AxleEnum axle, User coordinator) {
        List<String> role = coordinator.getProfiles().stream().map(Profile::getAuthority).toList();

        return role.contains(roleMapping.get(axle));
    }
}