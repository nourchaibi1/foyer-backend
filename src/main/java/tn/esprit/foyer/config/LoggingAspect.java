package tn.esprit.foyer.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    // Log avant chaque méthode service
    @Before("execution(* tn.esprit.foyer.services.*.*(..))")
    public void logBefore(JoinPoint jp) {
        logger.info("→ Appel: " + jp.getSignature().getName());
    }

    // Log après succès
    @AfterReturning(pointcut = "execution(* tn.esprit.foyer.services.*.*(..))", returning = "result")
    public void logAfter(JoinPoint jp, Object result) {
        logger.info("✓ Succès: " + jp.getSignature().getName());
    }

    // Log erreurs services
    @AfterThrowing(pointcut = "execution(* tn.esprit.foyer.services.*.*(..))", throwing = "ex")
    public void logError(JoinPoint jp, Exception ex) {
        logger.severe("✗ Erreur dans " + jp.getSignature().getName() + " : " + ex.getMessage());
    }
}