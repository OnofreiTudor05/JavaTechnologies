/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(filterName = "RequestDecorator", urlPatterns = {"/wordGen"})
public class RequestDecorator implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestQ = (HttpServletRequest) request;
        ServletRequestWrapper responseWrapper = new HttpServletRequestWrapper(requestQ){
            @Override
            public String getParameter(String key){
               
                StringBuilder newWord = new StringBuilder();
                newWord.append("You entered word and size.");
                
                return newWord.toString();
            }
        };
        chain.doFilter(responseWrapper, response);
    }

    @Override
    public void destroy() {
    }
    
    
}
*/