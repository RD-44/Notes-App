package uk.ac.ucl.servlets;

import notes.Item;
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

@WebServlet("/edititem.html")
public class EditItemServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        ItemList parent = (ItemList) request.getSession().getAttribute("curr"); // Gets list containing list to delete
        Item itemToRename = (Item) request.getSession().getAttribute(request.getParameter("item"));
        model.renameItem(itemToRename, request.getParameter("userinput"));
        request.setAttribute("list", parent); // Sets parent with deleted item as an attribute
        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/noteContents.jsp");
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }

}
