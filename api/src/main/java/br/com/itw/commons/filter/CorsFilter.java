/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.com.itw.commons.filter;

/**
 * Created by thiago.gama on 01/10/2014.
 */

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The same-origin policy is an important security concept implemented by web browsers to prevent Javascript code from making requests
 * against a different origin (e.g., different domain) than the one from which it was served. Although the same-origin policy is effective
 * in preventing resources from different origins, it also prevents legitimate interactions between a server and clients of a known and
 * trusted origin.
 * <p/>
 * Cross-Origin Resource Sharing (CORS) is a technique for relaxing the same-origin policy, allowing Javascript on a web page to consume a REST API served from a different origin.
 * <p/>
 * For implementation tutorial, visit http://spring.io/guides/gs/rest-service-cors/
 */
@Component
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        //request origins allowed
        response.setHeader("Access-Control-Allow-Origin", "*");
        //request methods allowed
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        //one hour max age
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Expose-Headers", "title, message");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
