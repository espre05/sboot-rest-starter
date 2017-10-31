package pm.sboot.sdrest.conf.aspect;

import java.security.Principal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
	static final Logger log = LoggerFactory.getLogger(SecurityAspect.class);

	@Autowired
	private KeycloakSecurityContext securityContext;
	@Autowired
	private AccessToken accessToken;
	
//	@Autowired
//	private Principal principal;

    @Pointcut("execution(public * pm.sboot.sdrest.ws.*.*(..))")
    public void pointcutForWS() {
    }
    @Pointcut("execution(public * pm.sboot.sdrest.repo.*.get*(..))")
    public void pointcutForRest() {
    	
    }

    @Around("pointcutForWS()")
    public Object logSecureEndpointAccess(ProceedingJoinPoint pjp) throws Throwable {
        log.error(
                "Entering SecurityAspect.advice() in class "
                        + pjp.getSignature().getDeclaringTypeName()
                        + " - method: " + pjp.getSignature().getName());

        log.error("AccessToken: " + securityContext.getTokenString());
        log.error("User: {} / {}", accessToken.getPreferredUsername(), accessToken.getName());
        //log.error("Principal: {}", principal.getName());
        return pjp.proceed();
    }
}