package uk.ac.ucl.servlets;
import uk.ac.ucl.items.ItemList;
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

@WebServlet("/deletelist.html")
public class DeleteListServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        ItemList parent = (ItemList) request.getSession().getAttribute("curr"); // Gets list containing list to delete
        ItemList listToDelete = (ItemList) request.getSession().getAttribute(request.getParameter("list"));
        model.delList(listToDelete, parent);
        request.setAttribute("list", parent); // Sets list after deletion of nested list as an attribute
        //Invoke the right JSP page depending on whether parent list is the main list or a nested list
        ServletContext context = getServletContext();
        RequestDispatcher dispatch;
        if(parent == model.getMain()){
            dispatch = context.getRequestDispatcher("/mainLists.jsp");
        }else{
            dispatch = context.getRequestDispatcher("/listContents.jsp");
        }
        dispatch.forward(request, response);
        response.setContentType("text/html");

    }
}
