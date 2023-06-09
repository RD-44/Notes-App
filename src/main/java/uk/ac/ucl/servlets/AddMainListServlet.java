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

@WebServlet("/addlist.html")
public class AddMainListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        model.addMainList(request.getParameter("userinput"));
        request.setAttribute("list", model.getMain());
        // Invoke the JSP
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/mainLists.jsp");
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }
}
