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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Vue/accueil.jsp");
		dispatcher.forward(request,response); 

}
}
