
package fr._42.spring.service.application;

import fr._42.spring.service.repositories.UsersRepository;
import fr._42.spring.service.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        UsersRepository jdbc = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class
        );
        System.out.println("Standard JDBC:");
        for (User user : jdbc.findAll()) {
            System.out.println(user);
        }

        UsersRepository template = context.getBean(
                "usersRepositoryJdbcTemplate", UsersRepository.class
        );
        System.out.println("JdbcTemplate:");
        for (User user : template.findAll()) {
            System.out.println(user);
        }

        context.close();
    }
}
