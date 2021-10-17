package one.digitalinnovation.gof.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Id
    private String cep;

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
