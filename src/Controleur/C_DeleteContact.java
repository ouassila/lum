package Controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.Contact;
import Modele.M_Data;

public class C_DeleteContact extends HttpServlet{

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String mac = request.getParameter("mac");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		Contact contactToDelete = new Contact(mac,mail,telephone);
		M_Data.getInstance().deleteContact(contactToDelete);
	}
}
