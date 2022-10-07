/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tudor Onofrei
 */
public class LogWriter {
    static String logFileName;       // both package level to enable testing
    static FileWriter logFileWriter;
    
    static ServletContext innerContext;
    
    public LogWriter(ServletContext servletApplicationContext){
        this.innerContext = servletApplicationContext;
        setup();
    }
    
    private static void setup(){
        try{
            if(logFileName == null){
                logFileName = "../logs/wordgeneratorlogs.txt";
                logFileWriter = new FileWriter(logFileName, true);
            }
        } catch (IOException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void outputRequest(HttpServletRequest request){
        try{
            if(innerContext != null){
                logFileWriter.write(extractRequestInfo(request));
                logFileWriter.flush();
            }
            else{
                logFileWriter.write("\nError\n");
                logFileWriter.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String extractRequestInfo(HttpServletRequest request){
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
}
