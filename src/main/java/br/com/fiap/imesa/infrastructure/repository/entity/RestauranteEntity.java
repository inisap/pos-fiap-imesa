package br.com.fiap.imesa.infrastructure.repository.entity;


import br.com.fiap.imesa.domain.entities.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipos_cozinha_id", nullable = false)
    private TipoCozinhaEntity tipoCozinha;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioFuncionamentoEntity> horariosFuncionamento = new ArrayList<>();

    // métodos auxiliares
    public void adicionarHorario(HorarioFuncionamentoEntity horario) {
        horario.setRestaurante(this); // garante relação bidirecional
        this.horariosFuncionamento.add(horario);
    }

    public void removerHorario(HorarioFuncionamentoEntity horario) {
        horario.setRestaurante(null);
        this.horariosFuncionamento.remove(horario);
    }

}
