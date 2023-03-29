package uk.ac.ucl.servlets;

import notes.ItemList;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/additem.html")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String content = request.getParameter("userinput");
        ItemList list = (ItemList) request.getSession().getAttribute("curr");
        String type = request.getParameter("add"); // Gets the type of item to add.
        model.addItem(content, list, type);
        request.setAttribute("list", list); // Sets list with added item as an attribute
        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/noteContents.jsp");
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }
}
