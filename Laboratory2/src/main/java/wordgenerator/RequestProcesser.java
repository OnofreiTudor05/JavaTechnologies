/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgenerator;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sequencegenerator.ArrangementGenerator;
import sequencegenerator.WordChecker;

/**
 *
 * @author Tudor Onofrei
 */
public class RequestProcesser {
    static void offerAnswer(HttpServletRequest request, HttpServletResponse response){
        try{
            String userAgent = request.getHeader("User-Agent");
            if(userAgent.contains("Mozilla") || userAgent.contains("Chrome") 
                || userAgent.contains("Safari") || userAgent.contains("Edge")){
                webRespond(request, response);
            }
            else{
                desktopRespond(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(RequestProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String orderLetters(String word){
        StringBuilder htmlOrderedList = new StringBuilder();
        char[] letterArray = word.toCharArray();
        
        htmlOrderedList.append("<ol>");
        for(char letter: letterArray){
            htmlOrderedList.append("<li>" + letter + "</li>");
        }
        htmlOrderedList.append("</ol>");
        
        return htmlOrderedList.toString();
    }
    
    static void webRespond(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String requestParameters = parseRequest(request);
        String[] parameters = requestParameters.split("&");
        String word = parameters[0];
        int permutationLength = Integer.parseInt(parameters[1]);

        ArrangementGenerator a = new ArrangementGenerator(word, permutationLength);
        Set<String> dictionary = WordChecker.getInstance();
        Set<String> filteredList = WordChecker.filterList(dictionary, a.getWords());
        
        sendListToResultPage(request, response, filteredList);
    }
    
    static void desktopRespond(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (PrintWriter out = response.getWriter()) {
            String requestParameters = parseRequest(request);
            String[] parameters = requestParameters.split("&");
            String word = parameters[0];
            int permutationLength = Integer.parseInt(parameters[1]);
            
            ArrangementGenerator a = new ArrangementGenerator(word, permutationLength);
            Set<String> dictionary = WordChecker.getInstance();
            Set<String> filteredList = WordChecker.filterList(dictionary, a.getWords());
            
            out.println(filteredList);
        }
    }
    
    static String parseRequest(HttpServletRequest request){
        StringBuilder parameters = new StringBuilder();
        String word = request.getParameter("word");
        
        parameters.append(word);
        parameters.append("&");
        
        int permutationLength = Integer.parseInt(request.getParameter("size"));
        if(permutationLength > word.length() || permutationLength < 0){
            permutationLength = 0;
        }
        else if(request.getParameter("size") == null){
            permutationLength = word.length();
        }
        parameters.append(permutationLength);
        
        return parameters.toString();
    }
    
    static void sendListToResultPage(HttpServletRequest request, HttpServletResponse response, Set<String> sequences){
        try{
            request.setAttribute("sequences", sequences);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(RequestProcesser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestProcesser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
