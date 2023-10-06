package br.upe.sisepei.api.representation;

import lombok.Data;

@Data
public class FileRepresentation {

    private byte[] file;

    public FileRepresentation(byte[] file) {
        this.file = file;
    }
}
