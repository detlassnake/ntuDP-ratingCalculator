package ua.detlas.ntuDpRating;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
    private double studentRating;
    private int bonusMarkCounter;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Rating Calculator");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene scene = new Scene(rootNode, 450, 150);
        stage.setScene(scene);

        Button btnBudgetRating = new Button("BUDGET RATING");
        Button btnAverageRating = new Button("AVERAGE RATING");
        Button btnBonusPlus = new Button("+ BONUS");
        Button btnClear = new Button("CLEAR");
        Label labelMark = new Label("0.0");
        TextField textField = new TextField();
        textField.setPrefWidth(420);
        textField.setTextFormatter(new TextFormatter<String>((Change c) -> {
            String text = c.getText();
            text = text.replaceAll("\t", "    ");
            text = text.replaceAll("\n", "");
            c.setText(text);
            return c;
        }));

        btnBudgetRating.setOnAction(event -> {
            String inputRating = textField.getText();
            if (inputRating.trim().length() > 0) {
                Rating rating = new Rating();
                studentRating = rating.ratingCalculate(inputRating);
                labelMark.setText("" + studentRating);
            }
        });

        btnAverageRating.setOnAction(event -> {
            String inputRating = textField.getText();
            if (inputRating.trim().length() > 0) {
                Rating rating = new Rating();
                studentRating = rating.ratingCalculate(inputRating) / 0.94;
                labelMark.setText("" + studentRating);
            }
        });

        btnBonusPlus.setOnAction(event -> {
            if (bonusMarkCounter != 6) {
                bonusMarkCounter++;
                studentRating++;
                labelMark.setText("" + studentRating);
            }
        });

        btnClear.setOnAction(event -> {
            studentRating = 0.0;
            bonusMarkCounter = 0;
            labelMark.setText("" + studentRating);
            textField.setText("");
        });

        rootNode.getChildren().addAll(textField, btnBudgetRating, btnAverageRating, btnBonusPlus, btnClear, labelMark);
        stage.show();
    }
}