/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weblisteners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Tudor Onofrei
 */
@WebListener
public class CategoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String category = servletContext.getInitParameter("initCategory");
        servletContext.setAttribute("initParameter", category);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
