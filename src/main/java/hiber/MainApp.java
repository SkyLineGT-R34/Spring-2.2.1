package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1= new User("User1", "Lastname1", "user1@mail.ru",new Car("CarModel1",1));
      userService.add(user1);
      User user2= new User("User2", "Lastname2", "user2@mail.ru",new Car("CarModel2",2));
      userService.add(user2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User findedUser = userService.getUserByCar(new Car("CarModel2",2));
      System.out.println("Id = "+findedUser.getId());
      System.out.println("First Name = "+findedUser.getFirstName());
      System.out.println("Last Name = "+findedUser.getLastName());
      System.out.println("Email = "+findedUser.getEmail());

      context.close();
   }
}
