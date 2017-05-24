package server.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.Main;
import server.controller.connectionDB.DatabaseConnection;

/**
 * Created by andreea on 5/24/2017.
 */
public class ControllerUsersManagement {
    public Button buttonRemove;
    public TableView table;
    public TableColumn username;
    public TableColumn firstName;
    public TableColumn lastName;
    public TableColumn credit;
    public Button buttonAddCredit;
    public Button buttonHistory;

    public void getAllData() {
        // https://stackoverflow.com/questions/18941093/how-to-fill-up-a-tableview-with-database-data
        DatabaseConnection c;
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        if (Main.serverConnection != null) {
            try {
                c = DatabaseConnection.getDatabaseConnection();
                for (int i = 0; i < 4; i++) {
                    final int j = i;
                    TableColumn col = new TableColumn();
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    table.getColumns().addAll(col);
                    System.out.println("Column [" + i + "] ");
                }


                for(int i=0;i<c.getUsers().size();i++) {
                    //Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int j = 1; j <= c.getUsers().size(); j++) {
                        //Iterate Column
                        row.add(c.getUsers().get(j).toString());
                    }
                    System.out.println("Row [1] added " + row);
                    data.add(row);

                }

                //FINALLY ADDED TO TableView
                table.setItems(data);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
        }
    }

    public void start(Stage stage) throws Exception {
        //TableView
        table = new TableView();
        getAllData();

        //Main Scene
        Scene scene = new Scene(table);

        stage.setScene(scene);
        stage.show();

    }

    public void removeUser(ActionEvent actionEvent) {
        DatabaseConnection c = DatabaseConnection.getDatabaseConnection();
    }

    public void addCredit(ActionEvent actionEvent) {
    }

    public void showHistory(ActionEvent actionEvent) {
    }
}
