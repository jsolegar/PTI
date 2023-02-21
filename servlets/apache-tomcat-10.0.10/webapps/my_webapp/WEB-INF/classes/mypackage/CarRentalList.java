package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class CarRentalList extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    String password = req.getParameter("password");
    if (nombre != null && nombre.equals("admin") && password != null && password.equals("1234")) { //Imprimir JSON
		 JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("orders.json")) {
	
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
           // out.println(jsonObject);

           
            // loop array
            JSONArray orders = (JSONArray) jsonObject.get("lista");
            Iterator<JSONObject> CO2 = orders.iterator();
            Iterator<JSONObject> ENGINE = orders.iterator();
            Iterator<JSONObject> DAYS = orders.iterator();
            Iterator<JSONObject> UNITS = orders.iterator();
            Iterator<JSONObject> DISCOUNT = orders.iterator();
            while (CO2.hasNext()) {
               String co2 = (String) CO2.next().get("CO2_rating");
               String engine = (String)  ENGINE.next().get("Engine");
               String rentDays = (String) DAYS.next().get("Number_of_days");
               String numUnits = (String) UNITS.next().get("Number_of_units");
               String discount = (String) DISCOUNT.next().get("Discount");
               out.println("<html><big>CO2 rating:"+ co2 +"</big><br></html>");
               out.println("<html><big>Engine:"+engine +"</big><br></html>");
	       out.println("<html><big>Number of days:" +rentDays + "</big><br></html>");
	       out.println("<html><big>Number of units:" + numUnits+ "</big><br></html>");
	       out.println("<html><big>Discount(%):" + discount +  "</big><br></html>");
	       out.println("<html><br></html>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    else {	
	    out.println("<html>" + 
	    	"<h1>User or Password incorrect</h1>" +
	    "</html>");

    	
    }



  }
  

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
