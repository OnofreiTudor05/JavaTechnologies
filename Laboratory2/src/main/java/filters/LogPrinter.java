/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import wordgenerator.WordGenerator;

/**
 *
 * @author Tudor Onofrei
 */
@WebFilter(filterName = "LogPrinter", urlPatterns = {"/view/wordGen"})
public class LogPrinter implements Filter {
    private static Logger logHandler;
    FileHandler fileHandler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try{
            fileHandler = new FileHandler("logFilter.txt", true);
            logHandler = Logger.getLogger(WordGenerator.class.getName());
            logHandler.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            Logger.getLogger(LogPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(LogPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        logHandler.info(getLogInformation(httpRequest));
        
        chain.doFilter(request, response);
    }
    
    private String getLogInformation(HttpServletRequest request){
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("\nHTTP Method used         : ").append(request.getMethod());
        requestInfo.append("\nIP-address of the client : ").append(request.getRemoteAddr());
        requestInfo.append("\nUser Agent               : ").append(request.getHeader("user-agent"));
        
        requestInfo.append("\nClient Languages         : ");
        Enumeration localeList = request.getLocales();
        Locale elementInList = (Locale)localeList.nextElement();
        requestInfo.append(elementInList.toString());
        while(localeList.hasMoreElements()){
            elementInList = (Locale)localeList.nextElement();
            requestInfo.append(", ").append(elementInList.toString());
        }
        requestInfo.append(";");
        
        requestInfo.append("\nParameters of the request: ");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry:  parameterMap.entrySet()){
            requestInfo.append(entry.getKey()).append(":");
            for(String entryValue: entry.getValue()){
                requestInfo.append(" ").append(entryValue);
            }
            requestInfo.append("; ");
        }
        requestInfo.append("\n----------------------------------------------------------------------------------------------\n");
        
        return requestInfo.toString();
    }

    @Override
    public void destroy() {
    }
    
    
    
}
