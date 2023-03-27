package uk.ac.ucl.servlets;
import Notes.Item;
import Notes.ItemList;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import uk.ac.ucl.model.*;

@WebServlet("/deleteitem.html")
public class DeleteItemServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        ItemList parent = (ItemList) request.getSession().getAttribute("curr"); // Gets list containing list to delete
        Item itemToDelete = (Item) request.getSession().getAttribute(request.getParameter("item"));
        model.delItem(itemToDelete, parent); // Deletes Item and updates file
        request.setAttribute("list", parent); // Sets parent with deleted item as an attribute
        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/noteContents.jsp");
        dispatch.forward(request, response);
        response.setContentType("text/html");

    }
}
