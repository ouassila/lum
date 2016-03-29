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
		
		
		Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(M_Data.MAC_MULTIPRISE);
		Environnement environnement = M_Data.getInstance().getLastEnvironnement(M_Data.MAC_MULTIPRISE);
		
		request.setAttribute("multiprise", multiprise);
		request.setAttribute("environnement", environnement);
		request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise(M_Data.MAC_MULTIPRISE));

		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
		Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(M_Data.MAC_MULTIPRISE);
		Environnement environnement = M_Data.getInstance().getLastEnvironnement(M_Data.MAC_MULTIPRISE);
		
		request.setAttribute("multiprise", multiprise);
		request.setAttribute("environnement", environnement);
		request.setAttribute("suivi",  M_Data.getInstance().getConsoPrise(M_Data.MAC_MULTIPRISE));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 

	}
	
}
