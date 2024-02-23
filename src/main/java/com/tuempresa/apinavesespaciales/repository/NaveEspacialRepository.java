package com.tuempresa.apinavesespaciales.repository;

import com.tuempresa.apinavesespaciales.entity.NaveEspacial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NaveEspacialRepository extends JpaRepository<NaveEspacial,Long> {
List<NaveEspacial> findByNombreContaining(String nombre);

}
