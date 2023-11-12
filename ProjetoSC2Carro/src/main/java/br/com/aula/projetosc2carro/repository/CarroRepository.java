package br.com.aula.projetosc2carro.repository;

import br.com.aula.projetosc2carro.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
