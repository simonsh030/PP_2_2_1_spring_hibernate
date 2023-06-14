package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final CarDao carDao;

    public UserServiceImp(UserDao userDao, CarDao carDao) {
        this.userDao = userDao;
        this.carDao = carDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> getCars() {
        return carDao.getCars();
    }

    @Override
    public User findOwner(String carName, int carSeries) {
        return carDao.findOwner(carName, carSeries);
    }
}
