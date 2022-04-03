package controller;

import model.Customer;
import model.Type;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;
import service.Impl.TypeServiceImpl;
import service.TypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerTypeServlet",urlPatterns = "/customer")
public class CustomerTypeServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    private TypeService typeService = new TypeServiceImpl();
    private Type type;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                create(request, response);
                break;
            }
            case "edit": {
                update(request, response);
                break;
            }
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int id_type = Integer.parseInt(request.getParameter("id_type"));
        String address = request.getParameter("address");
        Customer customer = customerService.findById(id_type);

        Customer customer1 = new Customer(name, birthday, gender, phone, email, type, address);
        customerService.update(customer);
        //  request.setAttribute("message","Edit successfully!!");
        customerList(request,response);

    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int id_type = Integer.parseInt(request.getParameter("id_type"));
        String address = request.getParameter("address");
        Customer customer = new Customer(name, birthday, gender, phone, email, new Type(id_type), address);
        customerService.save(customer);
        // RequestDispatcher dispatcher=request.getRequestDispatcher("list.jsp");
        customerList(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                showFormCreate(request, response);
                break;
            }
            case "edit": {
                edit(request, response);
                break;
            }
            case "delete": {
                delete(request, response);
            }
            case "search": {
                search(request, response);
            }
            default:
                customerList(request, response);
                break;
        }
    }

    private void customerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> listCustomer = customerService.findAll();
        request.setAttribute("listCustomer", listCustomer);
        List<Type> typeList = typeService.findByAll();
        request.setAttribute("listType", typeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request, response);
    }
    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> types = typeService.findByAll();
        request.setAttribute("listType", types);
        //// List<Product> products = iProductService.selectAllProduct();
        // request.setAttribute("listProduct", products);
        request.getRequestDispatcher("customer/create.jsp").forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Customer> listCustomer =new ArrayList<>();
        listCustomer =customerService.search(name);
        List<Type> typeList =typeService.findByAll();
        request.setAttribute("listType",typeList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/list.jsp");
        if (listCustomer.size()!=0){
            request.setAttribute("listCustomer",listCustomer);
        }else {
            request.setAttribute("mess","không có dữ liệu nào tìm hấy");
        }
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.delete(id);
        customerList(request, response);
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findById(id);
        List<Type> typeList = typeService.findByAll();
        request.setAttribute("customer", customer);
        request.setAttribute("listType",typeList);
        request.getRequestDispatcher("customer/edit.jsp").forward(request, response);
    }
////    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
////        int id = Integer.parseInt(request.getParameter("id"));
////        String name = request.getParameter("name");
////        String birthday = request.getParameter("birthday");
////        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
////        ;
////        String phone = request.getParameter("phone");
////        String email = request.getParameter("email");
////        int typeId = Integer.parseInt(request.getParameter("type_id"));
////        String address = request.getParameter("address");
////        Customer customer = new Customer(name, birthday, gender, phone, email, typeId, address);
////        customer.setId(id);
////        boolean check = customerService.save(customer);
////        if (check) {
////            request.setAttribute("mess", "Edit thanh cong");
////        } else {
////            request.setAttribute("mess", "Edit  không thanh cong");
////        }
////        List<Customer> customers = customerService.findAll();
////        List<Type> typeList = typeService.findByAll();
////        request.setAttribute("typeList", typeList);
////        request.setAttribute("customers", customers);
////        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/list.jsp");
////        try {
////            requestDispatcher.forward(request, response);
////        } catch (ServletException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//   // private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
//////        String id = Integer.parseInt(request.getParameter("id"));
////            String id = (request.getParameter("id"));
////            boolean check =customerService.delete(id);
////            if (check){
////                request.setAttribute("mess","Xóa thanh cong");
////            }else {
////                request.setAttribute("mess","Xóa không thanh cong");
////            }
////            List<Customer> customerList = customerService.findAll();
////            request.setAttribute("customerList",customerList);
////            List<Type> typeList =typeService.findByAll();
////            request.setAttribute("typeList",typeList);
////            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/list.jsp");
////            try {
////                requestDispatcher.forward(request,response);
////            } catch (ServletException e) {
////                e.printStackTrace();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        int id = Integer.parseInt(request.getParameter("id"));
////        customerService.delete(id);
////        showCustomerList(request, response);
    }
