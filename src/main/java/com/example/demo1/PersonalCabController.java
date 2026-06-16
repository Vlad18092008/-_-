package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PersonalCabController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button exitButton;

    @FXML
    private VBox userDataContainer;

    @FXML
    private VBox userInfoBlock;

    @FXML
    private VBox guestBlock;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label membershipLabel;

    @FXML
    private Label daysLeftLabel;

    @FXML
    public void initialize() {
        checkUserProfile();
    }

    private void checkUserProfile() {
        if (!UserSession.isLoggedIn()) {
            showGuestView();
        } else {
            showUserView();
        }
    }

    private void showGuestView() {
        userInfoBlock.setVisible(false);
        userInfoBlock.setManaged(false);
        guestBlock.setVisible(true);
        guestBlock.setManaged(true);

        welcomeLabel.setText("Личный кабинет");

        exitButton.setVisible(false);
        exitButton.setManaged(false);
    }

    private void showUserView() {
        userInfoBlock.setVisible(true);
        userInfoBlock.setManaged(true);
        guestBlock.setVisible(false);
        guestBlock.setManaged(false);

        nameLabel.setText(UserSession.getUserName());
        emailLabel.setText(UserSession.getUserEmail());
        phoneLabel.setText(UserSession.getUserPhone());
        welcomeLabel.setText("Привет, " + UserSession.getUserName() + "!");

        exitButton.setVisible(true);
        exitButton.setManaged(true);
    }

    @FXML
    protected void onMainMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\"");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить главное меню: " + e.getMessage());
        }
    }

    @FXML
    protected void onLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Вход");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить окно входа: " + e.getMessage());
        }
    }

    @FXML
    protected void onRegisterClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Регистрация");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить окно регистрации: " + e.getMessage());
        }
    }

    @FXML
    protected void onEditClick() {
        showAlert("Редактирование", "Функция редактирования профиля будет добавлена позже");
    }

    @FXML
    protected void onExitClick() {
        UserSession.setLoggedIn(false);
        checkUserProfile();
        showAlert("Выход", "Вы вышли из личного кабинета");
    }

    private void showAlert(String заголовок, String содержание) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(заголовок);
        alert.setHeaderText(null);
        alert.setContentText(содержание);
        alert.showAndWait();
    }
}