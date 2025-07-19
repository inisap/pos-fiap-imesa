package br.com.fiap.imesa.infrastructure.repository.springdata;


import br.com.fiap.imesa.infrastructure.repository.entity.ItemCardapioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {

    Optional<ItemCardapioEntity> findByIdAndCardapioEntity_Id(Long id, Long idCardapio);

    Page<ItemCardapioEntity> findByCardapioEntity_Id(Long cardapioId, Pageable pageable);

}
