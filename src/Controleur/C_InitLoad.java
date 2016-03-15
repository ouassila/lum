package Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.M_Data;
import Modele.Multiprise;

public class C_InitLoad extends HttpServlet {

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("oki");
		Multiprise multiprise = M_Data.getInstance().getMultipriseDetail("08:00:27:d1:76:e4");
		System.out.println(multiprise.getMac());
		System.out.println(multiprise.getPrises().size());
		System.out.println(multiprise.getPrises().get(1).getId());
		System.out.println(multiprise.getPrises().get(1).getEtat());
		request.setAttribute("multiprise", multiprise);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 

}
}
