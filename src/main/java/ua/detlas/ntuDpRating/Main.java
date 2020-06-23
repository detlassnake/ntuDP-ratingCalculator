package ua.detlas.ntuDpRating;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private double studentRating;

    @Override
    public void start(Stage primaryStage) {
        FlowPane rootNode = new FlowPane(10, 10);
        primaryStage.setScene(new Scene(rootNode, 450, 150));
        primaryStage.setTitle("Rating Calculator");
        primaryStage.getIcons().add(new Image("icons/ntu-dp-logo.png"));
        primaryStage.setResizable(false);
        rootNode.setAlignment(Pos.CENTER);

        Button btnBudgetRating = new Button("BUDGET RATING");
        Button btnAverageRating = new Button("AVERAGE RATING");
        Button btnBonusPlus = new Button("+ BONUS");
        Button btnBonusMinus = new Button("- BONUS");
        Button btnClear = new Button("CLEAR");
        Label labelMark = new Label("0.0");
        TextField textField = new TextField();

        labelMark.setTextFill(Color.web("#000000"));
        textField.setPrefWidth(430);
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
                studentRating = rating.ratingCalculate(inputRating).get(0).doubleValue();
                labelMark.setText("" + studentRating);
            }
        });

        btnAverageRating.setOnAction(event -> {
            String inputRating = textField.getText();
            if (inputRating.trim().length() > 0) {
                Rating rating = new Rating();
                studentRating = rating.ratingCalculate(inputRating).get(1).doubleValue();
                labelMark.setText("" + studentRating);
            }
        });

        btnBonusPlus.setOnAction(event -> {
            studentRating++;
            labelMark.setText("" + studentRating);
        });

        btnBonusMinus.setOnAction(event -> {
            --studentRating;
            labelMark.setText("" + studentRating);
        });

        btnClear.setOnAction(event -> {
            studentRating = 0.0;
            labelMark.setText("" + studentRating);
            textField.setText("");
        });

        rootNode.getChildren().addAll(textField, btnBudgetRating, btnAverageRating, btnBonusPlus, btnBonusMinus, btnClear, labelMark);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
