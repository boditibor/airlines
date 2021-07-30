package controllers;

import models.Passenger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AirlineListener implements ServletContextListener {

    @Override
    @SuppressWarnings("unchecked")
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        List<Passenger> pList = (List<Passenger>) servletContext.getAttribute("passengers");

        if (pList == null) {
            System.out.println("No passenger list created yet. Creating now...");
        }

        pList = new ArrayList<>();

        servletContext.setAttribute("passengers", pList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
