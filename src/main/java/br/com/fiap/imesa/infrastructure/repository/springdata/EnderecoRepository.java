package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
