package repository.Impl;

import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepositoryImpl implements TypeRepository {
    BaseRepository baseRepository = new BaseRepository();
    @Override
    public List<Type> findByAll() {
        List<Type> typeList =new ArrayList<>();
        Connection connection = baseRepository.connectDataBase();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customertype;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int typeId = resultSet.getInt("type_id");
                String typeName = resultSet.getString("type_name");
               Type customerType= new Type(typeId,typeName);
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
