package repository;

import model.Customer;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository {
    private static List<Type> typeList = new ArrayList<>();
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ALL_TYPE = "select * from type ;";
    public List<Type> findByAll() {
        Connection connection = baseRepository.connectDataBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id_type = resultSet.getInt("id_type");
                String name_type = resultSet.getString("name_type");
               Type customerType= new Type(id_type,name_type);
               typeList.add(customerType);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeList;
    }
}
