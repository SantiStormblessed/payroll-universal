package com.universal.payroll.controllersFx;

import com.universal.payroll.config.FxmlView;
import com.universal.payroll.config.StageManager;
import com.universal.payroll.user.User;
import com.universal.payroll.user.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private Button logInButton;
    @FXML
    private Button signUpButton;

    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Label alert;

    private final StageManager stageManager;

    private final UserService userService;

    @Lazy
    public LoginController(StageManager stageManager,
                           UserService userService){
        this.stageManager = stageManager;
        this.userService = userService;
    }

    public void loadUserAndOpenHomePage(){
        Optional<User> user = userService.findByUserName(userName.getText());

        if(user.isEmpty()){
            alert.setText("The username does not exist");
            throw new RuntimeException();
        } else if(!user.get().getPassword().equals(password.getText())){
            alert.setText("Incorrect password");
            throw new RuntimeException();
        }
        stageManager.switchToNextScene(FxmlView.HOME);
    }

    public void saveNewUser(){
        String username = userName.getText();
        String pass = password.getText();

        Optional<User> user = userService.findByUserName(username);
        if(user.isPresent()){
            alert.setText("User name already exists");
        }
        if(username.isBlank() || pass.isBlank()){
            alert.setText("Must input username and password");
        }
        userService.saveUser(username, pass);
        alert.setText("Succesfully saved new user name, please log in");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }
}
