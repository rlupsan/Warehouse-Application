package presentation;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;

public class TableGenerator<T> {

    /**
     * This method retrieves the properties from the models' fields
     * and it generates the table in JavaFX
     *
     * @param o Object
     * @return table
     */
    public TableView<T> generateTable(Object o) {
        TableView<T> table = new TableView<>();
        Class oClass = o.getClass();
        for (Field f : oClass.getDeclaredFields()) {
            TableColumn<T, ?> column = new TableColumn<>(f.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
            table.getColumns().add(column);
        }
        return table;
    }
}
