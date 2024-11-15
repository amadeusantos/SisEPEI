package br.upe.sisepei.api.representation;

import java.io.Serializable;

import br.upe.sisepei.core.profile.model.Profile;
import br.upe.sisepei.core.profile.model.ProfileEnum;

import lombok.Data;
import lombok.Getter;

@Getter
public class ProfileRepresentation implements Serializable {

    private final Long id;
	private final String name;

    public ProfileRepresentation(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName().name();
    }
}