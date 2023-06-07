package hiber.service;


import hiber.model.Car;
import hiber.model.User;


import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getUsers();

    void add(Car car);

    List<Car> getCars();

    User findOwner(String car_name, int car_series);
}