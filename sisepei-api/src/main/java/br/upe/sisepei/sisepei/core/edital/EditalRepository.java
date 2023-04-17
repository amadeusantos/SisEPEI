package br.upe.sisepei.sisepei.core.edital;

import br.upe.sisepei.sisepei.core.edital.modelo.TipoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.sisepei.core.edital.modelo.Edital;

import java.util.List;

public interface EditalRepository extends JpaRepository<Edital, Long> {

	
    public boolean existsByTitulo(String titulo);

    public List<Edital> findAllByTipo(TipoEnum tipo);

}
