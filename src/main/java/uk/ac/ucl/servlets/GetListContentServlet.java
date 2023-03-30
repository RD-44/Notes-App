package uk.ac.ucl.servlets;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.items.ItemList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/getlistcontent.html")
public class GetListContentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        ItemList list = (ItemList) request.getSession().getAttribute(request.getParameter("content"));
        ItemList prev = (ItemList) request.getSession().getAttribute("curr");
        if(prev == null){
            prev = model.getMain(); // prev might be null if given list has been visited from a search result, so we just make the back button go to main list.
        }
        model.pushList(prev);
        request.setAttribute("list", list);

        // Invoke the JSP
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/listContents.jsp");
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }
}

