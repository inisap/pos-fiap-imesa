package br.com.fiap.imesa.adapter.outboud.persistence.springdata;


import br.com.fiap.imesa.adapter.outboud.persistence.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
