package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainController {

    @FXML
    protected void onShopClick() {
        showAlert("Магазин", "Открывается раздел магазина спортивного питания и снаряжения");
    }

    @FXML
    protected void onExercisesClick() {
        showAlert("Упражнения", "База упражнений с видео и подробными инструкциями");
    }

    @FXML
    protected void onTrainersClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trainers-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Тренеры");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить страницу тренеров: " + e.getMessage());
        }
    }

    @FXML
    protected void onAboutClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("about-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - О нас");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить страницу \"О нас\": " + e.getMessage());
        }
    }

    @FXML
    protected void onPersonalCabClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("personal-cab-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\" - Личный кабинет");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить личный кабинет: " + e.getMessage());
        }
    }

    @FXML
    protected void onBookClick() {
        showAlert("Запись на тренировку", "Форма записи будет открываться здесь");
    }

    @FXML
    protected void onExitClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход из приложения");
        alert.setHeaderText(null);
        alert.setContentText("Вы уверены, что хотите выйти из фитнес клуба \"Импульс\"?");

        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                UserSession.clear();
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    private void showAlert(String заголовок, String содержание) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(заголовок);
        alert.setHeaderText(null);
        alert.setContentText(содержание);
        alert.showAndWait();
    }
}