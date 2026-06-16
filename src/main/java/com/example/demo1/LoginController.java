package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField телефонField;

    @FXML
    private PasswordField парольField;

    @FXML
    public void initialize() {
        телефонField.setText("+7");

        телефонField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.startsWith("+7")) {
                if (newValue.isEmpty()) {
                    телефонField.setText("+7");
                } else {
                    String толькоЦифры = newValue.replaceAll("\\D", "");
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

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\"");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить главное меню: " + e.getMessage());
        }
    }

    @FXML
    protected void onLoginClick() {
        String email = emailField.getText().trim();
        String телефон = телефонField.getText().trim();
        String пароль = парольField.getText();

        if (email.isEmpty() || телефон.isEmpty() || пароль.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля");
            return;
        }

        if (UserSession.getUserEmail().equals(email) &&
                UserSession.getUserPhone().equals(телефон) &&
                UserSession.getUserPassword().equals(пароль)) {

            UserSession.setLoggedIn(true);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("personal-cab-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Фитнес клуб \"Импульс\" - Личный кабинет");
            } catch (Exception e) {
                showAlert("Ошибка", "Не удалось загрузить личный кабинет: " + e.getMessage());
            }
        } else {
            showAlert("Ошибка", "Неверный Email, телефон или пароль");
        }
    }

    @FXML
    protected void onRegisterClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Регистрация");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить окно регистрации: " + e.getMessage());
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