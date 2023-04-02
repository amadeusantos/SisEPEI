package br.upe.sisepei.sisepei.core.edital;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upe.sisepei.sisepei.core.edital.modelo.Edital;

public interface EditalRepository extends JpaRepository<Edital, Long> {

	
    public boolean existsByTitulo(String titulo);

}
