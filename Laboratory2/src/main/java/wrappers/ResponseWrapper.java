/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Tudor Onofrei
 */
public class ResponseWrapper extends HttpServletResponseWrapper{
    
    public final StringWriter outputWriter;
    
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        outputWriter = new StringWriter();
    }
    
    @Override
    public PrintWriter getWriter(){
        return new PrintWriter(outputWriter);
    }
    
    @Override
    public String toString(){
        return outputWriter.toString();
    }
    
    
}
