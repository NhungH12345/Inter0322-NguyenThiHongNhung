package repository.Impl;

import model.Customer;
import model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static List<Customer> customerList = new ArrayList<>();
    BaseRepository baseRepository = new BaseRepository();
    final String SELECT_ALL_CUSTOMER = "SELECT * FROM customer;";
    final String INSERT_INTO_CUSTOMER = "INSERT INTO customer(`name`,birthday,gender,phone,email,type_id,address)" +
            " values(?,?,?,?,?,?,?,);";
    final String SELECT_BY_ID = "SELECT * FROM customer WHERE id =?;";
    final String DELETE_BY_CUSTOMER = "DELETE FROM customer WHERE id =";
    final String EDIT_BY_ID = "call edit_customer_by_id(?,?,?,?,?,?,?);";
    final String SEARCH = "SELECT * FROM customer WHERE name like ?;";


    @Override
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
                boolean gender = resultSet.getBoolean("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int typeId = resultSet.getInt("type_id");
                String address = resultSet.getString("address");

                Customer customer = new Customer(id, name, date, gender, phone, email, typeId, address);
                customerList.add(customer);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        Customer student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = String.valueOf(resultSet.getDate("birthday"));
                boolean gender = resultSet.getBoolean("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int typeId = resultSet.getInt("type_id");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id1, name, date, gender, phone, email, typeId, address);
                customerList.add(customer);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    @Override
    public boolean save(Customer customer) {
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        boolean check = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CUSTOMER);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDate(2, Date.valueOf(customer.getBirthday()));
            preparedStatement.setBoolean(3, customer.getGender());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getTypeId());
            preparedStatement.setString(7, customer.getAddress());
            check = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
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

    @Override
    public boolean delete(String id) {
        return false;
    }

//    @Override
//    public boolean delete(String id) {
//        Connection connection = null;
//        connection = baseRepository.connectDataBase();
//        boolean check = false;
//        try {
//            Statement statement = connection.createStatement();
////            preparedStatement.setInt(1, id);
//            check = statement.execute(DELETE_BY_ID2 + id + ";");
//            statement.close();
//            connection.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return check;
//    }

    @Override
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

    @Override
    public List<Customer> search(String searchName) {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        connection = baseRepository.connectDataBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH);
            String string1 = "%" + searchName + "%";
            preparedStatement.setString(1, string1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = String.valueOf(resultSet.getDate("birthday"));
                boolean gender = resultSet.getBoolean("gender");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int typeId = resultSet.getInt("type_id");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id1, name, date, gender, phone, email, typeId, address);
                customerList.add(customer);
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Type> findByAll() {
        return null;
    }
}