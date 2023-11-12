package br.com.aula.projetosc2carro.service;

import br.com.aula.projetosc2carro.entity.Carro;
import br.com.aula.projetosc2carro.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    @Transactional(readOnly = true)
    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(
                () -> new RuntimeException("## CARRO NÃO ENCONTRADO ##")
        );
    }

    @Transactional(readOnly = true)
    public List<Carro> buscarTodos() {
        return carroRepository.findAll();
    }

    @Transactional
    public Carro editarInformacoes(Long id, Carro carroAtualizado) {
        Carro carro = buscarPorId(id);
        if (carroAtualizado.getModelo() != null) {
            carro.setModelo(carroAtualizado.getModelo());
        }

        if (carroAtualizado.getMarca() != null) {
            carro.setMarca(carroAtualizado.getMarca());
        }

        if (carroAtualizado.getCor() != null) {
            carro.setCor(carroAtualizado.getCor());
        }

        if (carroAtualizado.getAno() != null) {
            carro.setAno(carroAtualizado.getAno());
        }
        return carroRepository.save(carro);
    }

    @Transactional
    public void excluirPorId(Long id) {
        Carro carro = buscarPorId(id);

        if (carro == null) {
            throw new RuntimeException("Carro com ID " + id + " não encontrado.");
        }

        carroRepository.delete(carro);
    }

    @Transactional
    public Carro atualizarCarro(Long id, Carro carroAtualizado) {
        Carro carro = buscarPorId(id);

        if (carro == null) {
            throw new RuntimeException("Carro com ID " + id + " não encontrado.");
        }
        if (
                carroAtualizado.getModelo() != null &&
                        carroAtualizado.getMarca() != null &&
                        carroAtualizado.getCor() != null &&
                        carroAtualizado.getAno() != null
        ) {

            carro.setModelo(carroAtualizado.getModelo());
            carro.setMarca(carroAtualizado.getMarca());
            carro.setCor(carroAtualizado.getCor());
            carro.setAno(carroAtualizado.getAno());

            return carroRepository.save(carro);
        } else {
            throw new IllegalArgumentException("Todos os campos devem ser fornecidos para atualização.");
        }
    }
}
