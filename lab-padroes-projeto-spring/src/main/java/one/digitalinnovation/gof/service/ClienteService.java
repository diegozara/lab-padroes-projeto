package one.digitalinnovation.gof.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.gof.exception.ClienteNaoCadastradoException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

    private ClienteRepository clienteRepository;

    private EnderecoRepository enderecoRepository;

    private ViaCepService viaCepService;

    public List<Cliente> listarTodos() {

        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) throws ClienteNaoCadastradoException{

       Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoCadastradoException(id));
        return cliente ;
    }

    public Cliente inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) throws ClienteNaoCadastradoException {

      clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoCadastradoException(id));
      salvarClienteComCep(cliente);
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) throws ClienteNaoCadastradoException{
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {

        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        cliente.setEndereco(endereco);
    }
}
