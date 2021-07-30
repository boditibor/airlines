package controllers;

import models.Gender;
import models.Passenger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {

    public AddPassenger() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("first_name", "");
        req.setAttribute("last_name", "");
        req.setAttribute("dob", "");

        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");

        view.forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", false);

        Passenger passenger = new Passenger();

        String firstName = req.getParameter("first-name");

        if (firstName.length() == 0) {
            req.setAttribute("errors", true);
            req.setAttribute("firstname error", true);
            req.setAttribute("first_name", "");
        } else {
            passenger.setFirstName(firstName);
            req.setAttribute("first_name", firstName);
        }

        String lastName = req.getParameter("last-name");

        if (lastName.length() == 0) {
            req.setAttribute("errors", true);
            req.setAttribute("lastname error", true);
            req.setAttribute("last_name", "");
        } else {
            passenger.setLastName(lastName);
            req.setAttribute("last_name", lastName);
        }

        String dobRaw = req.getParameter("dob");

        final String[] dobArray = dobRaw.split("\\/");

        String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(dobRaw);

        if (m.find()) {
            final String month = dobArray[0];
            final String day = dobArray[0];
            final String year = dobArray[0];

            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            final Date dob = cal.getTime();

            passenger.setDob(dob);

            req.setAttribute("dob", dobRaw);
        } else {
            req.setAttribute("errors", true);
            req.setAttribute("dob error", true);
            req.setAttribute("dob", dobRaw);

            if (dobRaw.length() == 0) {
                req.setAttribute("dob", "");
            }
        }

        String gender = req.getParameter("gender");
        passenger.setGender(Gender.valueOf(gender));

        if ((Boolean) req.getAttribute("errors")) {
            RequestDispatcher view = req.getRequestDispatcher("WEB_INF/views/add_passenger.jsp");

            view.forward(req, resp);
        } else {
            ServletContext sc = this.getServletContext();

            synchronized (this) {
                List<Passenger> pList = (List<Passenger>) sc.getAttribute("passengers");

                pList.add(passenger);

                sc.setAttribute("passengers", pList);
            }

            resp.sendRedirect("");
        }
    }
}
