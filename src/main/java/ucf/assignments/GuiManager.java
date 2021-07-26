/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GuiManager {
    InventoryList iManager = new InventoryList();

    @FXML private TextField itemSerialText;
    @FXML private TextField itemValueText;
    @FXML private TextField itemNameText;

    @FXML private TextField itemSerialField;
    @FXML private TextField itemValueField;
    @FXML private TextField itemNameField;

    @FXML private TextField directoryField;
    @FXML private TextField fileNameField;

    @FXML private ListView inventoryView;
    @FXML private MenuButton fileTypeDrop;
    @FXML private Text errorText;

    @FXML private AnchorPane createItemWindow;
    @FXML private AnchorPane importExportWindow;

    @FXML private CheckMenuItem JsonField;
    @FXML private CheckMenuItem HTMLField;
    @FXML private CheckMenuItem TCVField;

    private int itemSelected = 0;

    @FXML
    public void createWindowOpen() {
        createItemWindow.setDisable(false);
        createItemWindow.setVisible(true);
    }

    @FXML
    public void createWindowClose() {
        createItemWindow.setDisable(true);
        createItemWindow.setVisible(false);
        if (errorText.isVisible()){
            errorText.setVisible(false);
        }
    }

    @FXML
    public void importExportWindowOpen() {
        importExportWindow.setDisable(false);
        importExportWindow.setVisible(true);
    }

    @FXML
    public void importExportWindowClose() {
        importExportWindow.setDisable(true);
        importExportWindow.setVisible(false);
    }

    @FXML
    public void itemSelection() {
        itemSelected = inventoryView.getSelectionModel().getSelectedIndex();

        itemSerialText.setText(iManager.itemList.get(itemSelected).serialNumber);
        itemValueText.setText(String.valueOf(iManager.itemList.get(itemSelected).value));
        itemNameText.setText(iManager.itemList.get(itemSelected).name);
    }

    @FXML
    public void refreshList() {
        inventoryView.getItems().clear();
        for (int i = 0; i < iManager.itemList.size(); i++){
            inventoryView.getItems().add("$" + iManager.itemList.get(i).value + "\t" + iManager.itemList.get(i).serialNumber + "\t" + iManager.itemList.get(i).name);
        }
    }

    @FXML
    public void deleteButton() {
        iManager.deleteItem(itemSelected);
        refreshList();
    }

    @FXML
    public void createNewItem(){
        String output = iManager.createItem(itemNameField.getText(), itemSerialField.getText(), itemValueField.getText());
        errorText.setVisible(true);
        errorText.setText(output);
        refreshList();
    }

    @FXML
    public void saveButton(){
        iManager.editSerialNumber(itemSelected, itemSerialText.getText());
        iManager.editItemValue(itemSelected, itemValueText.getText());
        iManager.editItemName(itemSelected, itemNameText.getText());
        refreshList();
    }

    @FXML
    public void exportButtonAction() throws IOException {
        if (JsonField.isSelected()){
            iManager.exportListJSON(directoryField.getText(), fileNameField.getText());
        }
        if (HTMLField.isSelected()){
            iManager.exportListHTML(directoryField.getText(), fileNameField.getText());
        }
        if (TCVField.isSelected()){
            iManager.exportListTCV(directoryField.getText(), fileNameField.getText());
        }
    }

    @FXML
    public void importButtonAction() throws IOException {
        iManager.clearList();

        if (JsonField.isSelected()){
            iManager.importList(directoryField.getText(), fileNameField.getText(), 0);
        }
        if (HTMLField.isSelected()){
            iManager.importList(directoryField.getText(), fileNameField.getText(), 1);
        }
        if (TCVField.isSelected()){
            iManager.importList(directoryField.getText(), fileNameField.getText(), 2);
        }

        refreshList();
    }

    @FXML
    public void checkboxJson() {
        HTMLField.setSelected(false);
        TCVField.setSelected(false);
    }

    @FXML
    public void checkboxHTML() {
        JsonField.setSelected(false);
        TCVField.setSelected(false);
    }

    @FXML
    public void checkboxTCV() {
        HTMLField.setSelected(false);
        JsonField.setSelected(false);
    }

    @FXML
    public void sortName() {
        iManager.sortList(0);
        refreshList();
    }

    @FXML
    public void sortSerialNumber() {
        iManager.sortList(1);
        refreshList();
    }

    @FXML
    public void sortValue() {
        iManager.sortList(2);
        refreshList();
    }
}
