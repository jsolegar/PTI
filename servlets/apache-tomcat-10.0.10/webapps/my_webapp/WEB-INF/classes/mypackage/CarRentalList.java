package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CarRentalList extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    String password = req.getParameter("password");
    if (nombre != null && nombre.equals("jordi") && password != null && password.equals("1234")) { //Imprimir JSON
		    out.println("<html>" + 
		    	"<h1>Hello World!</h1>" +
		    "</html>");
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
