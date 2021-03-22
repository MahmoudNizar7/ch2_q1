/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assinment2_q1;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author pc
 */
public class Assinment2_q1 extends Application {
    
    private ListView<String> listViewSource, listViewDest;
    private TextField textFieldName;
    private CheckBox checkBoxAll;
    private RadioButton radioButtonGold, radioButtonCyan;
    private Button buttonAdd, buttonDel, buttonUpdate, buttonCopy, buttonClear;
    
    @Override
    public void start(Stage primaryStage) {
       String names[] = {"Mahmoud", "Nizar"};
        listViewSource = new ListView<>(FXCollections.observableArrayList(names));

//        listViewSource.getSelectionModel().selectedIndexProperty().addListener(e -> {
//            textFieldName.setText(listViewSource.getSelectionModel().getSelectedItem());
//        });

        listViewSource.setPrefSize(100, 150);

        listViewDest = new ListView<>();
        listViewDest.setPrefSize(100, 150);

        HBox hBox1 = new HBox(10, listViewSource, listViewDest);
        hBox1.setAlignment(Pos.CENTER);

        textFieldName = new TextField();

        Assinment2_q1.MyEventHandler myEventHandler = new Assinment2_q1.MyEventHandler();

        checkBoxAll = new CheckBox("selected All");
        checkBoxAll.setOnAction(event -> {

            listViewSource.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String name : listViewSource.getItems()) {
                listViewSource.getSelectionModel().select(name);
            }

        });

        radioButtonGold = new RadioButton("Gold");
        radioButtonCyan = new RadioButton("Cyan");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonGold.setToggleGroup(toggleGroup);
        radioButtonCyan.setToggleGroup(toggleGroup);

        HBox hBox2 = new HBox(20, radioButtonGold, radioButtonCyan);
        hBox2.setAlignment(Pos.CENTER);

        buttonAdd = new Button("Add");
        buttonDel = new Button("Delete");
        buttonUpdate = new Button("Update");
        buttonCopy = new Button("Copy");
        buttonClear = new Button("Clear");

        buttonAdd.setOnAction(myEventHandler);
        buttonDel.setOnAction(myEventHandler);
        buttonUpdate.setOnAction(myEventHandler);
        buttonCopy.setOnAction(myEventHandler);
        buttonClear.setOnAction(myEventHandler);

        HBox hBox3 = new HBox(10, buttonAdd, buttonDel, buttonUpdate, buttonCopy, buttonClear);
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10, hBox1, textFieldName, checkBoxAll, hBox2, hBox3);
        vBox1.setPadding(new Insets(20));
        vBox1.setAlignment(Pos.CENTER);

        radioButtonGold.setOnAction(event -> {
            vBox1.setStyle("-fx-background-color: gold");
        });
        radioButtonCyan.setOnAction(event -> {
            vBox1.setStyle("-fx-background-color: cyan");
        });

        FlowPane flowPane = new FlowPane(vBox1);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("List View");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private class MyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String buttonName = ((Button) event.getSource()).getText();

            if (buttonName == "Add") {

                if (!textFieldName.getText().equalsIgnoreCase("")) {

                    listViewSource.getItems().add(textFieldName.getText());
                    textFieldName.setText("");

                }

            } else if (buttonName == "Delete") {

                listViewSource.getItems().remove(listViewSource.getSelectionModel().getSelectedItem());

            } else if (buttonName == "Update") {

                if (!textFieldName.getText().equalsIgnoreCase("")) {

                    listViewSource.getItems().remove(listViewSource.getSelectionModel().getSelectedItem());
                    listViewSource.getItems().add(textFieldName.getText());
                    textFieldName.setText("");

                }

            } else if (buttonName == "Copy") {

                if (listViewSource.getSelectionModel().getSelectedItem() != null) {

                    for (String name : FXCollections.observableArrayList(listViewSource.getSelectionModel().getSelectedItems())) {
                        listViewDest.getItems().add(name);
                    }

                }

            } else if (buttonName == "Clear") {

                listViewSource.setItems(FXCollections.observableArrayList());
                listViewDest.setItems(FXCollections.observableArrayList());

            }
        }
    }
    
}
