package service.Impl;

import model.Type;
import repository.TypeRepository;
import service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    TypeRepository typeRepository = new TypeRepository();
    @Override
    public List<Type> findByAll() {
        return typeRepository.findByAll();
    }
}
