package br.com.aula.projetosc2carro.web.controller;

import br.com.aula.projetosc2carro.entity.Carro;
import br.com.aula.projetosc2carro.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> create(@RequestBody Carro carro) {
        Carro carroCriado = carroService.salvar(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroCriado);
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAll() {
        List<Carro> carros = carroService.buscarTodos();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable Long id) {
        Carro carro = carroService.buscarPorId(id);
        return ResponseEntity.ok(carro);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carro> updateInfo(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        Carro carro = carroService.editarInformacoes(id, carroAtualizado);
        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            carroService.excluirPorId(id);
            return ResponseEntity.ok("Carro excluído com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateById(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        try {
            Carro carro = carroService.atualizarCarro(id, carroAtualizado);
            return ResponseEntity.ok(carro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
