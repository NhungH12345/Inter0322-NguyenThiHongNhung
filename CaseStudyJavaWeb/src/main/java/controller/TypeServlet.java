package controller;

import model.Type;
import service.Impl.TypeServiceImpl;
import service.TypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TypeServlet")
public class TypeServlet extends HttpServlet {
    private TypeService typeService=new TypeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                typeList(request, response);
                break;
        }
    }
    private void typeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type>typeList=typeService.findByAll();
        request.setAttribute("listType", typeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request, response);
    }
}
