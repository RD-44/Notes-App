package uk.ac.ucl.servlets;
import uk.ac.ucl.items.Item;
import uk.ac.ucl.items.ItemList;
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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/runsearch.html")
public class SearchServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    // Use the model to do the search and sets the result as an attribute to be accessed by the searchResult jsp
    Model model = ModelFactory.getModel();
    HashMap<ItemList, ArrayList<Item>> searchResult = model.searchFor(request.getParameter("userinput")); // Gets hashmap of list with their items matching the query
    request.setAttribute("result", searchResult);
    // Invoke the JSP
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
    dispatch.forward(request, response);
    response.setContentType("text/html");
  }
}
