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

public class C_InitLoad extends HttpServlet {

	
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
		String temperature = request.getParameter("temperature");
		String humidite = request.getParameter("humidite");
		System.out.println(temperature +" ok "+ humidite);
	String operation = request.getParameter("operation");
	if (operation.equals("recuptemperature")){
		
	}

}
}
