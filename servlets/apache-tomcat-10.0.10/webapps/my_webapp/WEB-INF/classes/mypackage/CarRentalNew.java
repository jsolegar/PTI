package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CarRentalNew extends HttpServlet {

  int cont = 0;
  JSONArray list = new JSONArray();
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String co2 = req.getParameter("co2_rating");
    String engine = req.getParameter("sub_model_vehicle");
    String rentDays = req.getParameter("dies_lloguer");
    String numUnits = req.getParameter("num_vehicles");
    String discount =req.getParameter("descompte");
    if (!co2.isEmpty() && !engine.isEmpty() && !rentDays.isEmpty() && !numUnits.isEmpty() && !discount.isEmpty()) {
	    if (co2.equals("54")) co2 = "Extralow";
	    else if (co2.equals("71")) co2 = "Low";
	    else if (co2.equals("82")) co2 = "Medium";
	    else  if (co2.equals("139")) co2 = "High";
	    out.println("<html><big>CO2 rating:"+ co2 +"</big><br></html>");
	    out.println("<html><big>Engine:"+engine +"</big><br></html>");
	    out.println("<html><big>Number of days:" +rentDays + "</big><br></html>");
	    out.println("<html><big>Number of units:" + numUnits+ "</big><br></html>");
	    out.println("<html><big>Discount(%):" + discount +  "</big><br></html>");
	   
           JSONObject obj = new JSONObject();
          
	   obj.put("CO2_rating", co2);
	   obj.put("Engine", engine);
	   obj.put("Number_of_days", rentDays);
	   obj.put("Number_of_units", numUnits);
	   obj.put("Discount", discount);
	   list.add(obj);
	   try (FileWriter file = new FileWriter("orders.json")) {
		    file.write(list.toJSONString());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	   
	  else out.println("Faltan campos por rellenar");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
