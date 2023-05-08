package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List listCars() {
      Query query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User findOwner(String car_name, int car_series) {
      Query findCarQuery = sessionFactory.getCurrentSession().createQuery
                      ("from Car where model = :car_name and series = :car_series")
              .setParameter("car_name", car_name)
              .setParameter("car_series", car_series);
      List findCarList = findCarQuery.getResultList();
      if (!findCarList.isEmpty()) {
         Car findCar = (Car) findCarList.get(0);
         List<User> ListUser = listUsers();
         return listUsers().stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
      }
      return null;
   }
}