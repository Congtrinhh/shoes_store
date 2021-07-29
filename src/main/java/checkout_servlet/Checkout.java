package checkout_servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/checkout/checkout.jsp");
		dispatcher.forward(req, resp);
	}
}
