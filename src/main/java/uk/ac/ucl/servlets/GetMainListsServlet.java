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


@WebServlet("/getmainlists.html") // Servlet runs when "View notes" is clicked on main page.
public class GetMainListsServlet extends HttpServlet //
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();  // Get the data from the model
    ItemList list = model.getMain();
    request.setAttribute("list", list); // Stores the main list as an attribute to be accessed by MainNotes.jsp
    // Invoke the JSP for the mainNotes page
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/mainNotes.jsp");
    dispatch.forward(request, response);
    response.setContentType("text/html");
  }
}
