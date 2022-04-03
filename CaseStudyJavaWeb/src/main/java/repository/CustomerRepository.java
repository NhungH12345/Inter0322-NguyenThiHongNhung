package repository;

import model.Customer;
import model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static List<Customer> customerList = new ArrayList<>();
    BaseRepository baseRepository = new BaseRepository();
    final String SELECT_ALL_CUSTOMER = "SELECT * FROM customer join type ;";
    final String INSERT_INTO_CUSTOMER = "INSERT INTO customer(`name`,birthday,gender,phone,email,id_type,address)" +
            " values(?,?,?,?,?,?,?);";
    final String SELECT_BY_ID = "SELECT * FROM customer WHERE id =?;";
    final String DELETE_BY_CUSTOMER = "DELETE FROM customer WHERE id =";
    final String EDIT_BY_ID = "call edit_customer_by_id(?,?,?,?,?,?,?);";
    final String SEARCH = "SELECT * FROM customer WHERE name like ?;";


    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = String.valueOf(resultSet.getDate("birthday"));
                int gender = resultSet.getInt("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int id_type = resultSet.getInt("id_type");
                String name_type = resultSet.getString("name_type");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id, name, date, gender, phone, email, new Type(id_type, name_type), address);
                customerList.add(customer);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public Customer findById(int id) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        Customer customer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = String.valueOf(resultSet.getDate("birthday"));
                int gender = resultSet.getInt("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int id_type = resultSet.getInt("id_type");
                String name_type = resultSet.getString("name");
                String address = resultSet.getString("address");
                Customer customers = new Customer(id, name, date, gender, phone, email, new Type(id_type, name_type), address);
                customerList.add(customers);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public boolean save(Customer customer) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        boolean check = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CUSTOMER);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, Date.valueOf(customer.getBirthday()));
            preparedStatement.setInt(3, customer.isGender());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getType().getId_type());
            preparedStatement.setString(7, customer.getAddress());
            check = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    public boolean delete(int id) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        boolean check = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_CUSTOMER);
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;

    }

//    public boolean delete(String id) {
////        Connection connection = null;
////        connection = baseRepository.connectDataBase();
////        boolean check = false;
////        try {
////            Statement statement = connection.createStatement();
//////            preparedStatement.setInt(1, id);
////            check = statement.execute(DELETE_BY_ID2 + id + ";");
////            statement.close();
////            connection.close();
////        } catch (SQLException throwables) {
////            throwables.printStackTrace();
////        }
////        return check;
////    }

    public boolean update(int id, Customer customer) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        boolean check = false;
        try {
            CallableStatement callableStatement = connection.prepareCall(EDIT_BY_ID);
            check = callableStatement.executeUpdate() > 0;
            callableStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

//    public boolean update(Customer customer) {
//    }
    public List<Customer> search(String searchName) {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH);
            String string1 = "%" + searchName+ "%";
            preparedStatement.setString(1, string1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               // int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = String.valueOf(resultSet.getDate("birthday"));
                int gender = resultSet.getInt("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int id_type = resultSet.getInt("id_type");
                String name_type = resultSet.getString("name_type");
                String address = resultSet.getString("address");
                Customer customer = new Customer( name, date, gender, phone, email, new Type(id_type, name_type), address);
                customerList.add(customer);
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public List<Type> findByAll() {
        return null;
    }
    }