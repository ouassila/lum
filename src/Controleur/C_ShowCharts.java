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

import Modele.Environnement;
import Modele.M_Data;
import Modele.Multiprise;

public class C_ShowCharts extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String source ="";
		URL oracle = new URL("http://172.16.15.94/req.php?lum1=off");
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null){
			source +=inputLine;
		}

		in.close();
		System.out.println(source);
		System.out.println("connexion");

		//RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/Accueil.jsp");
		//dispatcher.forward(request,response); 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String operation = request.getParameter("operation");			

		if (operation.equals("show")){
			
			String datas = request.getParameter("datas");
			String periode = request.getParameter("periode");
			String mac = request.getParameter("mac");			
			Multiprise multiprise = M_Data.getInstance().getMultipriseDetail(mac);
			Environnement environnement = M_Data.getInstance().getLastEnvironnement(mac);
			
			if(datas == "temp" || datas == "humd"){
			}
			if( datas.contains("etat_")){
				
			}
			
			request.setAttribute("multiprise", multiprise);
			request.setAttribute("environnement", environnement);
			
			RequestDispatcher dispatch = request.getRequestDispatcher ("/Vue/accueil.jsp");			
			dispatch.forward (request, response);	
		}
	}
}
