package uk.ac.ucl.servlets;


import Notes.ItemList;
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

@WebServlet("/goback.html")
public class GoBackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Get previous list from stack and set it as an attribute.
        Model model = ModelFactory.getModel();
        ItemList list = model.prev();
        request.setAttribute("list", list);

        //Invoke the right JSP page depending on whether popped list is the main list or a nested list
        ServletContext context = getServletContext();
        RequestDispatcher dispatch;
        if(list == model.getMain()){
            dispatch = context.getRequestDispatcher("/mainNotes.jsp");
        }else{
            dispatch = context.getRequestDispatcher("/noteContents.jsp");
        }
        dispatch.forward(request, response);
        response.setContentType("text/html");
    }
}

