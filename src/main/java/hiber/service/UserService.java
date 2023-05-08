package hiber.service;


import hiber.model.Car;
import hiber.model.User;


import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();


    //    @Transactional
    void add(Car car);

    List<Car> listCars();

    User findOwner(String car_name, int car_series);
}