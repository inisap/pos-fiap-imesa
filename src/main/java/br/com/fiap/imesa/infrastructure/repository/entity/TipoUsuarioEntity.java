package br.com.fiap.imesa.infrastructure.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipos_usuarios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, name = "descricao_tipo_usuario")
    private String descricaoTipoUsuario;

    public Integer getId() {
        return id;
    }

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }
}
