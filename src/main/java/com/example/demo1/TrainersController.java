package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class TrainersController {

    @FXML
    public void initialize() {
    }

    @FXML
    protected void onMainMenuClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Фитнес клуб \"Импульс\"");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось загрузить главное меню: " + e.getMessage());
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