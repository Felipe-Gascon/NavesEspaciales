package com.tuempresa.apinavesespaciales.controller;

import com.tuempresa.apinavesespaciales.dto.NaveEspacialDto;
import com.tuempresa.apinavesespaciales.entity.NaveEspacial;
import com.tuempresa.apinavesespaciales.service.NaveEspacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/naves")
public class NaveEspacialController {

    @Autowired
    private NaveEspacialService service;
    @Autowired
    public NaveEspacialController(NaveEspacialService service) {
        this.service = service;
    }
    @GetMapping
    public Page<NaveEspacial> listarTodas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.listarTodas(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaveEspacial> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<NaveEspacial> crearNuevaNave(@RequestBody NaveEspacialDto naveEspacialDto) {
        NaveEspacial nuevaNave = new NaveEspacial();
        nuevaNave.setNombre(naveEspacialDto.getNombre());
        nuevaNave.setSerieOmovie(naveEspacialDto.getSerieOmovie());
        NaveEspacial naveGuardada = service.guardar(nuevaNave);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(naveGuardada.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(naveGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaveEspacial> actualizarNave(@PathVariable Long id, @RequestBody NaveEspacialDto naveEspacialDto) {
        return service.buscarPorId(id)
                .map(naveExistente -> {
                    naveExistente.setNombre(naveEspacialDto.getNombre());
                    naveExistente.setSerieOmovie(naveEspacialDto.getSerieOmovie());
                    NaveEspacial naveActualizada = service.guardar(naveExistente);
                    return ResponseEntity.ok(naveActualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        return service.buscarPorId(id).map(nave ->{
            service.eliminar(id);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<NaveEspacial> buscarNavesPorNombre(@RequestParam String nombre) {
        return service.buscarPorNombreConteniendo(nombre);
    }
}
