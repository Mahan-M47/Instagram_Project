package Client.View;

import Client.Controller.Data;
import Client.Controller.NetworkManager;
import Client.Controller.Request;
import Client.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable
{
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF, confirmPasswordTF;
    @FXML
    private Hyperlink loginInsteadHL;
    @FXML
    private Button signupButton;
    @FXML
    private Label errorLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.setText(Utils.SIGNUP_ERROR_TEXT);
    }


    @FXML
    void loginInsteadHLHandler(ActionEvent event) {
        Starter.changeScene(Utils.GUI.LOGIN);
        Utils.resetErrorTexts();
    }

    @FXML
    void signupButtonClickHandler(ActionEvent event)
    {
        String username = usernameTF.getText().toLowerCase();
        String password = passwordTF.getText();

        if ( checkTextFields(username, password) ) {
            Request req = new Request(Utils.REQ.SIGNUP, new Data(username, password));
            NetworkManager.putRequest(req);
        }
    }


    public boolean checkTextFields(String username, String password)
    {
        boolean flag = false;
        String confirmPassword = confirmPasswordTF.getText();

        if (username.equals("") && password.equals("")) {
            errorLabel.setText("Please Enter Your Username and Password.");
        }
        else if (username.equals("")) {
            errorLabel.setText("Please Enter Your Username.");
        }
        else if (username.contains(" ")) {
            errorLabel.setText("Username Cannot Contain White Space");
        }
        else if (username.length() < 4) {
            errorLabel.setText("Username Must Have At Least 4 Characters.");
        }
        else if (password.equals("")) {
            errorLabel.setText("Please Enter Your Password.");
        }
        else if (password.contains(" ")) {
            errorLabel.setText("Password Cannot Contain White Space.");
        }
        else if (password.length() < 6) {
            errorLabel.setText("Password Must Have At Least 6 Characters.");
        }
        else if (confirmPassword.equals("")) {
            errorLabel.setText("Please Confirm Your Password.");
        }
        else if (!password.equals(confirmPassword)) {
            errorLabel.setText("The Passwords Didn't Match. Please Try Again.");
        }

        else {
            errorLabel.setText("");
            flag = true;
        }

        return flag;
    }

}
