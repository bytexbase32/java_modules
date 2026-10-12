package fr._42.forms;

import fr._42.annotations.HtmlForm;
import fr._42.annotations.HtmlInput;

@HtmlForm(
    fileName = "user.html",
    action = "/users",
    method = "post"
)
public class UserForm {

    @HtmlInput(
        type = "text",
        name = "username",
        placeholder = "Enter username"
    )
    private String username;

    @HtmlInput(
        type = "email",
        name = "email",
        placeholder = "Enter email"
    )
    private String email;

    @HtmlInput(
        type = "password",
        name = "password",
        placeholder = "enter password"
    )
    private String password;
}
