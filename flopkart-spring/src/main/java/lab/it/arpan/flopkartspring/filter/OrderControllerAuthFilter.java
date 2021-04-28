package lab.it.arpan.flopkartspring.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

public class OrderControllerAuthFilter implements Filter {

    private static final JwtConsumer JWT_CONSUMER = new JwtConsumerBuilder()
        .setRequireExpirationTime()
        .setAllowedClockSkewInSeconds(30)
        .setRequireSubject()
        .setExpectedIssuer("flopkartApi")
        .setVerificationKey(new HmacKey("secret".getBytes()))
        .setRelaxVerificationKeyValidation()
        .setJwsAlgorithmConstraints(ConstraintType.PERMIT, AlgorithmIdentifiers.HMAC_SHA256)
        .build();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getMethod().equalsIgnoreCase("options")) {
            System.out.println("doFilter(request, response) called");
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendError("Invalid auth header.", response);
            return;
        }

        String rawToken = authHeader.substring("Bearer ".length());

        try {
            JWT_CONSUMER.process(rawToken);
        } catch (InvalidJwtException e) {
            e.printStackTrace();
            sendError("Invalid JWT in auth header.", response);
            return;
        }

        System.out.println("doFilter(request, response) called");
        chain.doFilter(request, response);
    }

    private void sendError(String message, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpResponse.getWriter().write(JSONObject.toJSONString(Map.of("message", message)));
    }
}
