package uk.ac.ucl.servlets;
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


@WebServlet("/getmainlists.html")
public class GetMainListsServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    request.setAttribute("list", model.getMain()); // Stores the main list as an attribute to be accessed by the jsp
    // Invoke the JSP
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/mainLists.jsp");
    dispatch.forward(request, response);
    response.setContentType("text/html");
  }
}
