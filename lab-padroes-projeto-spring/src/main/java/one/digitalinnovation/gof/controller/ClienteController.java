package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.exception.ClienteNaoCadastradoException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) throws ClienteNaoCadastradoException {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserir (@RequestBody Cliente cliente){
        return clienteService.inserir(cliente);
    }

    @PutMapping ("/{id}")
    public Cliente atualizar (@PathVariable Long id, @RequestBody Cliente cliente) throws ClienteNaoCadastradoException {
        return clienteService.atualizar(id,cliente);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Long id) throws ClienteNaoCadastradoException{
         clienteService.deletar(id);
        }
}

