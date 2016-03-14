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

public class C_SaveDatas extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("oki");
		String source ="";
		URL oracle = new URL("http://172.16.15.94/req.php?lum1=off");
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			source +=inputLine;
		in.close();
		System.out.println(source);
		System.out.println("connexion");
		System.out.println(M_Data.getInstance().test());

		//RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/Accueil.jsp");
		//dispatcher.forward(request,response); 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String operation = request.getParameter("operation");			
		
		if (operation.equals("save")){
			String etat_1 = request.getParameter("etat_1");
			String etat_2 = request.getParameter("etat_2");
			String etat_3 = request.getParameter("etat_3");
			String min_temp = request.getParameter("min_temp");
			String max_temp = request.getParameter("max_temp");
			String min_humd = request.getParameter("min_humd");
			String max_humd = request.getParameter("max_humd");
			
			request.setAttribute("retour", "Envoi OK : " +etat_1+" "+etat_2+" "+etat_3+" "+min_temp+" "+max_temp+" "+min_humd+" "+max_humd);
			
			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");
			dispatch.forward (request, response);	
		}

	}
}
