package com.example.managestaff.model.services;

import com.example.managestaff.model.entity.Staff;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportFile {



    public static void exportToExcel(TableView<Staff> tableView, File outputFile) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Staff Data");
            ObservableList<TableColumn<Staff, ?>> columns = tableView.getColumns();

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.size(); i++) {
                headerRow.createCell(i).setCellValue(columns.get(i).getText());
            }

            ObservableList<Staff> data = tableView.getItems();
            for (int rowIdx = 0; rowIdx < data.size(); rowIdx++) {
                Row row = sheet.createRow(rowIdx + 1);
                Staff staff = data.get(rowIdx);
                row.createCell(0).setCellValue(staff.getId());
                row.createCell(1).setCellValue(staff.getPhoneNumber());
                row.createCell(2).setCellValue(staff.getName());
                row.createCell(3).setCellValue(staff.getGender());
                row.createCell(4).setCellValue(staff.getDepartmentId());
            }

            try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
