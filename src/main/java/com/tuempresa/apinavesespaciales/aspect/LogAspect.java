package com.tuempresa.apinavesespaciales.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.tuempresa.apinavesespaciales.service.NaveEspacialService.buscarPorId(..)) && args(id,..)")
    public void logSiIdNegativo(JoinPoint joinPoint, Long id) {
        if (id != null && id < 0) {
            logger.warn("Se intentó buscar una nave con un ID negativo: {}", id);
        }
    }
}
