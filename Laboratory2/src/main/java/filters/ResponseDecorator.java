/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import wrappers.ResponseWrapper;

/**
 *
 * @author Tudor Onofrei
 */
@WebFilter(filterName = "ResponseDecorator", urlPatterns = {"/view/wordGen"})
public class ResponseDecorator implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseWrapper simpleResponse = new ResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, simpleResponse);
        
        
        String content = simpleResponse.toString();
        String extraResponse = "\n<p> Prelude: Mesaj adaugat in plus </p>\n";
        extraResponse += content;
        
        extraResponse += "\n<p> Coda: Mesaj adaugat in plus </b>\n";
        
        PrintWriter out = response.getWriter();
        out.write(extraResponse);
        
    }

    @Override
    public void destroy() {
    }
}
