package org.meli.apptestemigration.repository;

import org.meli.apptestemigration.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}

