package com.tuempresa.apinavesespaciales.service;

import com.tuempresa.apinavesespaciales.entity.NaveEspacial;
import com.tuempresa.apinavesespaciales.repository.NaveEspacialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NaveEspacialServiceTest {
    @Mock
    private NaveEspacialRepository naveEspacialRepository;

    @InjectMocks
    private NaveEspacialService naveEspacialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarNaveEspacial() {
        NaveEspacial nave = new NaveEspacial();
        nave.setNombre("X-Wing");
        nave.setSerieOmovie("Película");

        when(naveEspacialRepository.save(any(NaveEspacial.class))).thenReturn(nave);

        NaveEspacial guardada = naveEspacialService.guardar(nave);

        verify(naveEspacialRepository).save(any(NaveEspacial.class));
        assert guardada.getNombre().equals(nave.getNombre());
        assert guardada.getSerieOmovie().equals(nave.getSerieOmovie());
    }
}