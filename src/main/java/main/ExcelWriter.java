package main;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ExcelWriter {
    public void writeToExcel(ArrayList<Registration> registrations, String filePath) {
    	
        // Add the "ExcelFile" directory to the file path
        String dataDirectory = "ExcelFile" + File.separator; // Using File.separator ensures compatibility with different operating systems

        // Build the full file path
        String fullFilePath = dataDirectory + filePath;

        File dataDir = new File(dataDirectory);
        if (!dataDir.exists()) {
            if (!dataDir.mkdirs()) {
                System.out.println("Failed to create 'data' directory");
                return;
            }
        }

        Workbook workbook;
        Sheet sheet;
        FileOutputStream fileOut;

        File file = new File(fullFilePath);

        try {
            if (!file.exists()) {
                // If the file doesn't exist, create a new one
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Registrations");

                // Add the header
                String[] header = {"First Name", "Last Name", "Email", "Password", "Department"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < header.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(header[i]);
                }
            } else {
                // If the file already exists, open it to append data
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                sheet = workbook.getSheetAt(0); // Assuming there's only one sheet in the file
                fis.close();
            }

            // Get the number of the last filled row
            int lastRowNum = sheet.getLastRowNum();

            // Add data to existing rows or create new rows as needed
            for (Registration registration : registrations) {
                Row row = sheet.createRow(++lastRowNum);
                row.createCell(0).setCellValue(registration.getFirstName().substring(0, 1).toUpperCase() + registration.getFirstName().substring(1));
                row.createCell(1).setCellValue(registration.getLastName().substring(0, 1).toUpperCase() + registration.getLastName().substring(1));
                row.createCell(2).setCellValue(registration.getEmail());
                row.createCell(3).setCellValue(registration.getPassword());
                row.createCell(4).setCellValue(registration.getDepartment());
            }

            // Write to the Excel file
            fileOut = new FileOutputStream(fullFilePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }
}
