package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private TextField имяField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField телефонField;

    @FXML
    private PasswordField парольField;

    @FXML
    private PasswordField подтвердитьПарольField;

    @FXML
    public void initialize() {
        телефонField.setText("+7");

        телефонField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.startsWith("+7")) {
                if (newValue.isEmpty()) {
                    телефонField.setText("+7");
                } else {
                    String فقطЦифры = newValue.replaceAll("\\D", "");
                    String толькоЦифры = "";
                    телефонField.setText("+7" + толькоЦифры);
                }
            }

            String толькоЦифры = newValue.replaceAll("\\D", "");
            if (толькоЦифры.length() > 10) {
                телефонField.setText("+7" + толькоЦифры.substring(0, 10));
            }
        });
    }

    @FXML
    protected void onMainMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) имяField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\"");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить главное меню: " + e.getMessage());
        }
    }

    @FXML
    protected void onRegisterClick() {
        String имя = имяField.getText().trim();
        String email = emailField.getText().trim();
        String телефон = телефонField.getText().trim();
        String пароль = парольField.getText();
        String подтвердитьПароль = подтвердитьПарольField.getText();

        if (имя.isEmpty() || email.isEmpty() || телефон.isEmpty() || пароль.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все обязательные поля");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert("Ошибка", "Неверный формат Email");
            return;
        }

        if (!телефон.matches("\\+7\\d{10}")) {
            showAlert("Ошибка", "Телефон должен содержать 10 цифр после +7");
            return;
        }

        if (!пароль.equals(подтвердитьПароль)) {
            showAlert("Ошибка", "Пароли не совпадают");
            return;
        }

        if (пароль.length() < 6) {
            showAlert("Ошибка", "Пароль должен быть не менее 6 символов");
            return;
        }

        UserSession.setUserName(имя);
        UserSession.setUserEmail(email);
        UserSession.setUserPhone(телефон);
        UserSession.setUserPassword(пароль);
        UserSession.setLoggedIn(true);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("personal-cab-view.fxml"));
            Parent root = loader.load();

            showAlert("Успех", "Регистрация успешна!\nИмя: " + имя + "\nEmail: " + email + "\nТелефон: " + телефон);

            Stage stage = (Stage) имяField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Личный кабинет");

            имяField.clear();
            emailField.clear();
            телефонField.clear();
            парольField.clear();
            подтвердитьПарольField.clear();
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить личный кабинет: " + e.getMessage());
        }
    }

    @FXML
    protected void onLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) имяField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Вход");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить окно входа: " + e.getMessage());
        }
    }

    private void showAlert(String заголовок, String содержание) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(заголовок);
        alert.setHeaderText(null);
        alert.setContentText(содержание);
        alert.showAndWait();
    }
}