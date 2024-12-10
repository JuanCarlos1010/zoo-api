package com.openx.zoo.api.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    private final String fontSize;

    public Excel() {
        this.fontSize = "12";
    }

    protected byte[] toBytes(Workbook workbook) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return outputStream.toByteArray();
    }

    protected ByteArrayOutputStream createTemplate(String sheetName, String[] headers) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);

            // Create headers
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            return outputStream;
        }
    }

    protected Sheet createSheet(Workbook workbook, String sheetName, List<ExcelHeader> headers) {
        Sheet sheet = workbook.createSheet(sheetName);

        // Create headers
        CellStyle headerStyle = createHeaderStyle(workbook, IndexedColors.DARK_GREEN);
        Row headerRow = sheet.createRow(0);
        for (int index = 0; index < headers.size(); index++) {
            ExcelHeader header = headers.get(index);
            sheet.setColumnWidth(index, header.width());

            Cell cell = headerRow.createCell(index);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header.text());
        }

        return sheet;
    }

    protected List<String> getSheetHeaders(Sheet sheet) {
        List<String> headers = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            String headerName = cell.getStringCellValue();
            headers.add(headerName);
        }
        return headers;
    }

    protected List<String> getSheetHeaders(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        return getSheetHeaders(sheet);
    }

    protected CellStyle createHeaderStyle(Workbook workbook, IndexedColors colors) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints(Short.parseShort(this.fontSize));
        font.setColor(IndexedColors.WHITE.index);
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(colors.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    protected CellStyle createBodyStyle(Workbook workbook, IndexedColors colors) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints(Short.parseShort(this.fontSize));
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(colors.getIndex());
        return cellStyle;
    }

    protected String nullableString(Cell cell) {
        if(cell == null) return null;
        if(cell.getCellType() == CellType.BLANK) return null;
        return cell.getStringCellValue();
    }

    protected boolean isNotNumber(Cell cell) {
        if(cell == null) return true;
        return cell.getCellType() != CellType.NUMERIC;
    }

    protected boolean isEmpty(Cell cell) {
        if(cell == null) return true;
        return cell.getCellType() == CellType.BLANK;
    }

    protected String validateDateCells(Row row, String headerName) {
        return String.format("Error: Fila [%s] Columna '%s' debe ser formato fecha.",
                (row.getRowNum() + 1), headerName);
    }

    protected String validateDatetimeCells(Row row, String headerName) {
        return String.format("Error: Fila [%s] Columna '%s' debe ser formato fecha y hora.",
                (row.getRowNum() + 1), headerName);
    }

    protected String validateNumericCells(Row row, String headerName) {
        return String.format("Error: Fila [%s] Columna '%s' debe ser un número.",
                (row.getRowNum() + 1), headerName);
    }

    protected String validateEmptyCells(Row row, String headerName) {
        return String.format("Error: Fila [%s] Columna '%s' no debe ser vacío.",
                (row.getRowNum() + 1), headerName);
    }

    protected String validateEmailCell(Row row, String headerName) {
        return String.format("Error: Fila [%s] Columna '%s' debe ser un email.",
                (row.getRowNum() + 1), headerName);
    }
}
