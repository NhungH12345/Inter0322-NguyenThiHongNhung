package service.Impl;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final String SELECT_ALL = "select * from product";
    private final String SELECT_BY_ID = "select * from product where id= ?";
    private final String CREATE = "insert into product (name, price, quantity, color, category) value (?, ?, ?, ?, ?)";
    private final String UPDATE = "update product set name= ?, price= ?, quantity= ?, color= ?, category= ? where id= ?";
    private final String DELETE = "delete from product where id= ?";


    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/baitap", "root", "12345");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void save(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(product.getId() > 0 ? UPDATE : CREATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPrice());
            preparedStatement.setString(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getCategory());

            if (product.getId() > 0) preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//       if(product.getId()> 0){
//         // update
//            try ( Connection connection= getConnection();
//                 PreparedStatement preparedStatement= connection.prepareStatement(UPDATE)){
//                preparedStatement.setString(1, product.getName());
//                preparedStatement.setString(2, product.getPrice());
//                preparedStatement.setString(3, product.getQuantity());
//                preparedStatement.setString(4, product.getColor());
//                preparedStatement.setString(5, product.getDescription());
//                preparedStatement.setString(6, product.getCategory());
//                preparedStatement.setInt(7, product.getId());
//
//                preparedStatement.executeUpdate();
//
//            }catch (SQLException e){
//                e.printStackTrace();
//           }
//       }
//        else {
//           // create
//            try ( Connection connection= getConnection();
//                  PreparedStatement preparedStatement= connection.prepareStatement(CREATE)){
//                  preparedStatement.setString(1, product.getName());
//                preparedStatement.setString(2, product.getPrice());
//                preparedStatement.setString(3, product.getQuantity());
//                preparedStatement.setString(4, product.getColor());
//                preparedStatement.setString(5, product.getDescription());
//                preparedStatement.setString(6, product.getCategory());
//                preparedStatement.executeUpdate();
//
//            }catch (SQLException e){
//               e.printStackTrace();
//            }
//        }
//
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product get(int id) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return getProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String price = resultSet.getString(3);
        String quantity = resultSet.getString(4);
        String color = resultSet.getString(5);
        String category = resultSet.getString(6);

        return new Product(id, name, price, quantity, color, "", category);
    }
}