package com.example.mall.util;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
    public static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case NUMERIC -> cell.getNumericCellValue();
        }
        return null;
    }
}
