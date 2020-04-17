package presentation;

import bll.CustomerBLL;
import bll.OrderItemBLL;
import bll.ProductBLL;
import dao.CustomerDAO;
import dao.ProductDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Customer;
import model.OrderItem;
import model.Product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static dao.CustomerDAO.getTheCustomerList;
import static dao.ProductDAO.getTheProductsList;

public class View extends Application {

    private TextField idCustomerInput, nameCustomerInput, phoneInput, emailInput, addressInput,
            idProductInput, nameProductInput, categoryIdInput, priceInput, quantityOnStockInput,
            productInput, customerInput, quantityInput;
    private TableView<Customer> tableViewCustomer;
    private TableView<Product> tableViewProduct;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to the Warehouse");

        VBox vBox = new VBox(10);
        Label helloLabel = new Label("What do you want to do ?");
        helloLabel.setFont(Font.font("Georgia", FontWeight.NORMAL, 16));

        Button customerButton = new Button("Manage Customers");
        customerButton.setMaxWidth(150);
        Button productButton = new Button("Manage Products");
        productButton.setMaxWidth(150);
        Button orderButton = new Button("Place an Order");
        orderButton.setMaxWidth(150);

        vBox.getChildren().addAll(helloLabel, customerButton, productButton, orderButton);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 1100, 550);
        // THE END OF SCENE 0

        //BEGIN SCENE 1
        primaryStage.setTitle("Manage Customers");

        HBox hBox1 = new HBox(10);
        VBox vBox1 = new VBox(8);
        HBox hBox11 = new HBox(10);

        tableViewCustomer = new TableView<>();
        Customer customer = new Customer(-1, "", "", "", "");
        TableGenerator<Customer> t = new TableGenerator<Customer>();
        tableViewCustomer = t.generateTable(customer);
        tableViewCustomer.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewCustomer.setMaxWidth(900);
        refreshTableCustomer();

        idCustomerInput = new TextField();
        idCustomerInput.setPromptText("Id");
        idCustomerInput.setMaxWidth(70);
        nameCustomerInput = new TextField();
        nameCustomerInput.setPromptText("Name");
        phoneInput = new TextField();
        phoneInput.setPromptText("Phone");
        emailInput = new TextField();
        emailInput.setPromptText("Email");
        addressInput = new TextField();
        addressInput.setPromptText("Address");
        Button clearCustomerButton = new Button("Clear");
        clearCustomerButton.setOnAction(e -> clearAllCustomerFields());
        Label chooseLabel = new Label("Choose an operation: ");
        Button addCustomerButton = new Button("Add Client");
        addCustomerButton.setPrefWidth(100);
        Button editCustomerButton = new Button("Edit Client");
        editCustomerButton.setPrefWidth(100);
        Button deleteCustomerButton = new Button("Delete Client");
        deleteCustomerButton.setPrefWidth(100);
        Button backButtonC = new Button("Back to Menu");

        hBox11.getChildren().addAll(chooseLabel, addCustomerButton, editCustomerButton, deleteCustomerButton);
        hBox1.getChildren().addAll(idCustomerInput, nameCustomerInput, phoneInput, emailInput, addressInput, clearCustomerButton);
        vBox1.getChildren().addAll(tableViewCustomer, hBox1, hBox11, backButtonC);
        Scene scene1 = new Scene(vBox1, 1100, 550);
        //END OF SCENE 1 (Customer)

        //BEGIN OF SCENE 2 (Product)
        HBox hBox2 = new HBox(10);
        VBox vBox2 = new VBox(8);
        HBox hBox12 = new HBox(10);

        tableViewProduct = new TableView<>();
        Product product = new Product(-1, "", 0, 0.00, 0);
        TableGenerator<Product> t1 = new TableGenerator<Product>();
        tableViewProduct = t1.generateTable(product);
        tableViewProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewProduct.setMaxWidth(900);
        refreshTableProduct();

        idProductInput = new TextField();
        idProductInput.setPromptText("Id");
        idProductInput.setMaxWidth(100);
        nameProductInput = new TextField();
        nameProductInput.setPromptText("Name");
        nameProductInput.setMaxWidth(150);
        categoryIdInput = new TextField();
        categoryIdInput.setPromptText("Category Id");
        categoryIdInput.setMaxWidth(100);
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMaxWidth(100);
        quantityOnStockInput = new TextField();
        quantityOnStockInput.setPromptText("Quantity on stock");
        quantityOnStockInput.setMaxWidth(110);
        Button clearProductButton = new Button("Clear");
        clearProductButton.setOnAction(e -> clearAllProductFields());
        Label choose2Label = new Label("Choose an operation: ");
        Button addProductButton = new Button("Add Product");
        addProductButton.setPrefWidth(100);
        Button editProductButton = new Button("Edit Product");
        editProductButton.setPrefWidth(100);
        Button deleteProductButton = new Button("Delete Product");
        deleteProductButton.setPrefWidth(100);

        Button backButtonP = new Button("Back to Menu");

        hBox12.getChildren().addAll(choose2Label, addProductButton, editProductButton, deleteProductButton);
        hBox2.getChildren().addAll(idProductInput, nameProductInput, categoryIdInput, priceInput, quantityOnStockInput,
                clearProductButton);
        vBox2.getChildren().addAll(tableViewProduct, hBox2, hBox12, backButtonP);
        Scene scene2 = new Scene(vBox2, 1100, 550);
        //END OF SCENE 2

        //BEGIN OF SCENE 3
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label infoLabel = new Label("Place an order:");
        GridPane.setConstraints(infoLabel, 0, 0);
        Label customerLabel = new Label("Customer Id: ");
        GridPane.setConstraints(customerLabel, 0, 1);
        Label productLabel = new Label("Product Id: ");
        GridPane.setConstraints(productLabel, 0, 2);
        Label quantityLabel = new Label("Quantity");
        GridPane.setConstraints(quantityLabel, 0, 3);

        customerInput = new TextField();
        GridPane.setConstraints(customerInput, 1, 1);
        productInput = new TextField();
        GridPane.setConstraints(productInput, 1, 2);
        quantityInput = new TextField();
        GridPane.setConstraints(quantityInput, 1, 3);
        Button okBillButton = new Button("OK! I want the bill");
        GridPane.setConstraints(okBillButton, 1, 5);
        Button backButtonO = new Button("Back to Menu");
        GridPane.setConstraints(backButtonO, 1, 7);

        gridPane.getChildren().addAll(infoLabel, customerLabel, customerInput, productLabel, productInput, quantityLabel,
                quantityInput, okBillButton, backButtonO);
        Scene scene3 = new Scene(gridPane, 1100, 550);
        //END OF SCENE 3

        customerButton.setOnAction(e -> {
            primaryStage.setTitle("Manage Customer");
            primaryStage.setScene(scene1);
        });
        backButtonC.setOnAction(e -> {
            primaryStage.setTitle("Welcome to the Warehouse");
            primaryStage.setScene(scene);
        });
        productButton.setOnAction(e -> {
            primaryStage.setTitle("Manage Product");
            primaryStage.setScene(scene2);
        });
        backButtonP.setOnAction(e -> {
            primaryStage.setTitle("Welcome to the Warehouse");
            primaryStage.setScene(scene);
        });
        orderButton.setOnAction(e -> {
            primaryStage.setTitle("Place an Order");
            primaryStage.setScene(scene3);
        });

        backButtonO.setOnAction(e -> {
            primaryStage.setTitle("Welcome to the Warehouse");
            primaryStage.setScene(scene);
        });

        addCustomerButton.setOnAction(this::addCustomer);
        editCustomerButton.setOnAction(this::editCustomer);
        deleteCustomerButton.setOnAction(this::deleteCustomer);
        addProductButton.setOnAction(this::addProduct);
        editProductButton.setOnAction(this::editProduct);
        deleteProductButton.setOnAction(this::deleteProduct);
        okBillButton.setOnAction(this::createTransaction);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This method refreshes the customer table
     * it does not have any parameters
     */
    private void refreshTableCustomer() {
        tableViewCustomer.getItems().removeAll();
        ArrayList<Customer> customers1 = getTheCustomerList();
        ObservableList<Customer> data1 = FXCollections.observableArrayList(customers1);
        tableViewCustomer.setItems(data1);
        tableViewCustomer.refresh();
    }

    /**
     * This method refreshes the product table
     * it does not have any parameters
     */
    private void refreshTableProduct() {
        tableViewProduct.getItems().removeAll();
        ArrayList<Product> products1 = getTheProductsList();
        ObservableList<Product> dataProducts1 = FXCollections.observableArrayList(products1);
        tableViewProduct.setItems(dataProducts1);
        tableViewProduct.refresh();

    }

    /**This method clears the fields of the customer part
     * it does not have any parameters*/
    private void clearAllCustomerFields() {
        idCustomerInput.clear();
        nameCustomerInput.clear();
        phoneInput.clear();
        emailInput.clear();
        addressInput.clear();
    }

    /**This method clears the fields of the products part
     * it does not have any parameters*/
    private void clearAllProductFields() {
        idProductInput.clear();
        nameProductInput.clear();
        categoryIdInput.clear();
        priceInput.clear();
        quantityOnStockInput.clear();
    }

    /**This method creates the transaction and the bill for the order
     */
    private void createTransaction(ActionEvent e) {
        try {
            int customerId = Integer.parseInt(customerInput.getText());
            int productId = Integer.parseInt(productInput.getText());
            int requestedQuantity = Integer.parseInt(quantityInput.getText());
            Product actualProduct = ProductDAO.findProductById(productId);
            assert actualProduct != null;
            int newQuantityProduct = actualProduct.getQuantityOnStock() - requestedQuantity;
            double price = actualProduct.getPrice();
            if (newQuantityProduct >= 0) {
                OrderItemBLL orderItemBLL = new OrderItemBLL();
                OrderItem orderItem1 = new OrderItem(customerId, productId, requestedQuantity);
                orderItemBLL.insert(orderItem1);
                String content = "Your order is:\nCustomer id: " + customerId + "\nProduct id: " + productId + "\nQuantity: " + requestedQuantity +
                        "\nTotal price: " + (price * requestedQuantity);
                createFile("final_bill.txt", content);
                Product newProduct = new Product(actualProduct.getIdProduct(), actualProduct.getNameProduct(),
                        actualProduct.getCategoryId(), actualProduct.getPrice(), newQuantityProduct);
                ProductDAO.update(newProduct);
                refreshTableProduct();
            } else {
                showError(Alert.AlertType.WARNING, "Under stock msg","We don't have the quantity you have asked for" );
            }
            clearOrderFields();
        } catch (Exception e1) {
           showError(Alert.AlertType.ERROR, "Impossible Transaction","The transaction could not have benn realised." );
        }
    }

    /**This method clears the order fields*/
    private void clearOrderFields(){
        customerInput.clear();
        productInput.clear();
        quantityInput.clear();
    }

    /**This method show the transaction error
     * @param alertType - the type of the alert
     * @param title - the title of the alert
     * @param content - the content of the alert*/
    private void showError(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**This method creates and writes into the file as the bill
     * @param pathName - the path name of the file
     * @param content - the content of the file
     */
    private void createFile(String pathName, String content){
        try {
            File file = new File(pathName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(content);
            pw.close();
        } catch (IOException ei) {
            showError(Alert.AlertType.ERROR, "Bill unrealised", "The bill you have asked for was unrealised.\nSomething went wrong\n");
        }
    }

    /**edits the product*/
    private void editProduct(ActionEvent e) {
        try {
            ProductBLL productBLL = new ProductBLL();
            Product product1 = new Product(
                    Integer.parseInt(idProductInput.getText()),
                    nameProductInput.getText(),
                    Integer.parseInt(categoryIdInput.getText()),
                    Double.parseDouble(priceInput.getText()),
                    Integer.parseInt(quantityOnStockInput.getText()));
            productBLL.update(product1);
            clearAllProductFields();
            refreshTableProduct();
        } catch (Exception e1) {
            showError(Alert.AlertType.ERROR,"Couldn't edit product","The product could not be edited.\nThe input might be missing or incompatible.\n" +
                    "Please try again."  );
        }
    }

    /**deletes the product*/
    private void deleteProduct(ActionEvent e) {
        try {
            Product selectedItem = tableViewProduct.getSelectionModel().getSelectedItem();
            int idSelected = selectedItem.getIdProduct();
            ProductDAO.delete(idSelected);
            refreshTableProduct();
            clearAllProductFields();
        } catch (Exception ex) {
            showError(Alert.AlertType.ERROR,"Couldn't delete product","The product could not be deleted.");
        }
    }

    /**adds the product*/
    private void addProduct(ActionEvent e) {
        try {
            ProductBLL productBLL = new ProductBLL();
            Product product1 = new Product(
                    Integer.parseInt(idProductInput.getText()),
                    nameProductInput.getText(),
                    Integer.parseInt(categoryIdInput.getText()),
                    Double.parseDouble(priceInput.getText()),
                    Integer.parseInt(quantityOnStockInput.getText()));
            productBLL.insert(product1);
            refreshTableProduct();
            clearAllProductFields();
        } catch (Exception e1) {
            showError(Alert.AlertType.ERROR,"Couldn't add product","The product could not be added.\nThe input might be missing or incompatible.\n" +
                    "Please try again."  );
        }
    }

    /**adds the customer*/
    private void addCustomer(ActionEvent e) {
        try {
            CustomerBLL customerBLL = new CustomerBLL();
            Customer c = new Customer(
                    Integer.parseInt(idCustomerInput.getText()),
                    nameCustomerInput.getText(),
                    phoneInput.getText(),
                    emailInput.getText(),
                    addressInput.getText());
            customerBLL.insert(c);
            refreshTableCustomer();
            clearAllCustomerFields();
        } catch (Exception e1) {
            showError(Alert.AlertType.ERROR,"Couldn't add customer","The customer could not be added.\nThe input might be missing or incompatible.\n" +
                    "Please try again." );
        }
    }

    /**deletes the customer*/
    private void deleteCustomer(ActionEvent e) {
        try {
            Customer selectedItem = tableViewCustomer.getSelectionModel().getSelectedItem();
            int idSelected = selectedItem.getIdCustomer();
            tableViewCustomer.getItems().remove(selectedItem);
            CustomerDAO.delete(idSelected);
            refreshTableCustomer();
            clearAllCustomerFields();
        } catch (Exception ex) {
            showError(Alert.AlertType.INFORMATION, "Couldn't delete customer","The customer could not be deleted.\n" +
                    "It may have an order already, so it is not possible to delete it\n");
        }
    }

    /**edits the customer*/
    private void editCustomer(ActionEvent e) {
        try {
            CustomerBLL customerBLL = new CustomerBLL();
            Customer customer1 = new Customer(
                    Integer.parseInt(idCustomerInput.getText()),
                    nameCustomerInput.getText(),
                    phoneInput.getText(),
                    emailInput.getText(),
                    addressInput.getText());
            customerBLL.update(customer1);
            clearAllCustomerFields();
            refreshTableCustomer();
        } catch (Exception e1) {
            showError(Alert.AlertType.ERROR,"Couldn't edit customer", "The customer could not be edited.\nThe input might be missing or incompatible.\n" +
                    "Please try again." );
        }
    }
}
