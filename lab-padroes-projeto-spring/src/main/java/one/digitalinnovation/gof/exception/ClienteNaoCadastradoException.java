package one.digitalinnovation.gof.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoCadastradoException extends Exception {

    public ClienteNaoCadastradoException(Long id){
        super("Cliente não cadastrado com id: "+ id);
    }
}

