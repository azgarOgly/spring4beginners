package eu.bigbank.tech.offsite.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Incoming request from {}", request.getRemoteAddr());
        chain.doFilter(request, response);
        log.info("Responding with {}", response.getContentType());
    }
}
