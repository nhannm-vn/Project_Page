/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class MyContextListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Deploying...............");
        ServletContext context = sce.getServletContext();
        String siteMapsFile = context.getInitParameter("SITEMAPS_FILE_PATH");
        InputStream is = null;
        Properties siteMaps = new Properties();
        
        try {
            is = context.getResourceAsStream(siteMapsFile);
            siteMaps.load(is);
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("IO: " + ex.getMessage());
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("IO: " + ex.getMessage());
                }
            }
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce){
        System.out.println("Undeploying...............");
    }
    
}
//Log chi o hai noi ServletObject va ContextScope