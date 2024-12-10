package com.openx.zoo.api.utils;

import com.openx.zoo.api.entity.Treatment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelMedicHistory extends Excel {

    public byte[] downloadMedicalHistories(List<Treatment> treatments) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            List<ExcelHeader> headers = List.of(
                    HistoryHeader.ANIMAL_CODE, HistoryHeader.ANIMAL_NAME, HistoryHeader.SPECIES,
                    HistoryHeader.REGION, HistoryHeader.ZONE, HistoryHeader.APPLY_DATE,
                    HistoryHeader.VETERINARIAN, HistoryHeader.DIAGNOSIS, HistoryHeader.COMMENTS);

            Sheet sheet = createSheet(workbook, "Data", headers);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            CellStyle bodyStyle = createBodyStyle(workbook, IndexedColors.BLACK);
            for (int index = 1; index < treatments.size() + 1; index++) {
                Row row = sheet.createRow(index);

                Treatment item = treatments.get(index - 1);

                Cell cellCode = row.createCell(0);
                cellCode.setCellStyle(bodyStyle);
                cellCode.setCellValue(item.getAnimal().getId());

                Cell cellAnimalName = row.createCell(1);
                cellAnimalName.setCellStyle(bodyStyle);
                cellAnimalName.setCellValue(item.getAnimal().getName());

                Cell cellAnimalSpecies = row.createCell(2);
                cellAnimalSpecies.setCellStyle(bodyStyle);
                cellAnimalSpecies.setCellValue(item.getAnimal().getSpecies());

                Cell cellZone = row.createCell(3);
                cellZone.setCellStyle(bodyStyle);
                cellZone.setCellValue(item.getAnimal().getZone().getName());

                Cell cellRegion = row.createCell(4);
                cellRegion.setCellStyle(bodyStyle);
                cellRegion.setCellValue(item.getAnimal().getZone().getRegion().getName());

                Cell cellApplyDate = row.createCell(5);
                cellApplyDate.setCellStyle(bodyStyle);
                cellApplyDate.setCellValue(dateFormatter.format(item.getApplyDate()));

                Cell cellVeterinarian = row.createCell(6);
                cellVeterinarian.setCellStyle(bodyStyle);
                cellVeterinarian.setCellValue(item.getVeterinarian().getFullName());

                Cell cellDiagnosis = row.createCell(7);
                cellDiagnosis.setCellStyle(bodyStyle);
                cellDiagnosis.setCellValue(item.getDiagnosis());

                Cell cellComment = row.createCell(8);
                cellComment.setCellStyle(bodyStyle);
                cellComment.setCellValue(item.getComments());
            }

            return toBytes(workbook);
        }
    }

    public enum HistoryHeader implements ExcelHeader {
        ANIMAL_CODE("Código animal", 4400),
        ANIMAL_NAME("Nombre animal", 8000),
        SPECIES("Especie", 4000),
        REGION("Región", 5000),
        ZONE("Zona", 5000),
        APPLY_DATE("Fecha", 3000),
        VETERINARIAN("Veterinario", 7000),
        DIAGNOSIS("Diagnóstico", 8000),
        COMMENTS("Comentarios", 7000);

        private final String text;
        private final int width;

        HistoryHeader(String text, int width) {
            this.text = text;
            this.width = width;
        }

        @Override
        public String text() {
            return text;
        }

        @Override
        public int width() {
            return width;
        }
    }
}
