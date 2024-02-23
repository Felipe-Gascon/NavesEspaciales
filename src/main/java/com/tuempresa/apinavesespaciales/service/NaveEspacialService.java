package com.tuempresa.apinavesespaciales.service;

import com.tuempresa.apinavesespaciales.entity.NaveEspacial;
import com.tuempresa.apinavesespaciales.repository.NaveEspacialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaveEspacialService {
    @Autowired
    private NaveEspacialRepository repository;

    public Page<NaveEspacial> listarTodas(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Cacheable(value = "navesEspaciales", key = "#id", unless = "#result == null || !#result.isPresent()")
    public Optional<NaveEspacial> buscarPorId(Long id){
        return repository.findById(id);
    }

    @CachePut(value = "navesEspaciales", key = "#result.id")
    public NaveEspacial guardar(NaveEspacial naveEspacial){
        return repository.save(naveEspacial);
    }

    @CacheEvict(value = "navesEspaciales", key = "#id")
    public void eliminar(long id){
         repository.deleteById(id);
    }
    @CachePut(value = "navesEspaciales", key = "#naveEspacial.id")
    public NaveEspacial actualizarNave(Long id, NaveEspacial naveEspacialActualizada) {
        return repository.findById(id)
                .map(naveExistente -> {
                    naveExistente.setNombre(naveEspacialActualizada.getNombre());
                    naveExistente.setSerieOmovie(naveEspacialActualizada.getSerieOmovie());
                    return repository.save(naveExistente);
                }).orElseThrow(() -> new RuntimeException("Nave no encontrada con el id " + id));
    }

    public List<NaveEspacial> buscarPorNombreConteniendo(String nombre) {
        return repository.findByNombreContaining(nombre);
    }
}
