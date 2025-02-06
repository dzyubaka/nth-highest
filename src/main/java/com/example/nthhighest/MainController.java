package com.example.nthhighest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
class MainController {

    @GetMapping
    private int main(String path, int n) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(path);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int[] maxElements = new int[n];
        for (int i = 0; i < n; i++) {
            maxElements[i] = Integer.MIN_VALUE;
        }

        for (Row row : sheet) {
            int num = (int) row.getCell(0).getNumericCellValue();
            if (num > maxElements[0]) {
                maxElements[0] = num;

                for (int j = 0; j < n - 1; j++) {
                    if (maxElements[j] > maxElements[j + 1]) {
                        ArrayUtils.swap(maxElements, j, j + 1);
                    } else {
                        break;
                    }
                }
            }
        }

        return maxElements[0];
    }

}