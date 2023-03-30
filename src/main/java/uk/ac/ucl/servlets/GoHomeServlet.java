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

@WebServlet("/gohome.html")
public class GoHomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Empty the history stack in the model
        Model model = ModelFactory.getModel();
        model.emptyStack();
        //Go back to homepage
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/index.html");
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }
}