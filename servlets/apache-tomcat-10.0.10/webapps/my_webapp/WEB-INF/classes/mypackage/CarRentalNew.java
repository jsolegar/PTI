package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class CarRentalNew extends HttpServlet {

 
  
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
	   
	   File f = new File("orders.json");
	   JSONArray list = new JSONArray();
	   if (f.exists()) {
	   	try (Reader reader = new FileReader("orders.json")) {
	   		JSONParser parser = new JSONParser();
	   		JSONObject jsonObject = (JSONObject) parser.parse(reader);
	   		JSONArray orders = (JSONArray) jsonObject.get("lista");
	   		Iterator<JSONObject> iterator = orders.iterator();
	   		 while (iterator.hasNext()) {
           		 list.add(iterator.next());
      			  }
	   	} catch (ParseException e) {
	   		e.printStackTrace();
	   	}
	   }
	   
           JSONObject obj = new JSONObject();
           JSONObject objfinal = new JSONObject();
	   obj.put("CO2_rating", co2);
	   obj.put("Engine", engine);
	   obj.put("Number_of_days", rentDays);
	   obj.put("Number_of_units", numUnits);
	   obj.put("Discount", discount);
	   list.add(obj);
	 objfinal.put("lista",list);
	   try (FileWriter file = new FileWriter("orders.json")) {
		    file.write(objfinal.toJSONString());
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
