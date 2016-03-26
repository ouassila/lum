package Controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modele.Environnement;
import Modele.M_Data;
import Modele.Multiprise;

public class C_InitLoad extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String mac = "08:00:27:d1:76:e4";
		
		Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(mac);
		Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);
		Map<Integer, HashMap<String, Integer>> conso = M_Data.getInstance().getConsoPrise(mac);
		
		request.setAttribute("multiprise", multiprise);
		request.setAttribute("environnement", environnement);
		request.setAttribute("suivi", conso);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String mac = "08:00:27:d1:76:e4";
		
		Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(mac);
		Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);
		
		request.setAttribute("multiprise", multiprise);
		request.setAttribute("environnement", environnement);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 

	}
	
}
