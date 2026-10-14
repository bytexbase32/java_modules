
package fr._42.spring.service.application;

import fr._42.spring.service.config.ApplicationConfig;
import fr._42.spring.service.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        ApplicationConfig.class
                );

        try {
            UsersService usersService =
                    context.getBean(UsersService.class);

            String password =
                    usersService.signUp("junior101@example.com");

            System.out.println("Temporary password: " + password);
        } finally {
            context.close();
        }
    }
}
