package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> getUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public User findOwner(String carName, int carSeries) {
      String queryString = "select user from User user join user.car car where car.model = :carName and car.series = :carSeries";

      return sessionFactory.getCurrentSession()
              .createQuery(queryString, User.class)
              .setParameter("carName", carName)
              .setParameter("carSeries", carSeries)
              .uniqueResultOptional()
              .orElseThrow(() -> new EntityNotFoundException("Owner not found for car: " + carName + " series: " + carSeries));
   }
}
