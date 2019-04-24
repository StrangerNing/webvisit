package com.webvisit.utils;

import com.webvisit.common.component.Column;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public class ExcelUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    public static HSSFWorkbook generateExcel(List<Column> columns, List<?> list, String sheetName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row;

        int rows = list.size(), cols = columns.size();
        Object val;
        for (int c = 0; c < cols; c++) {
            if (c == 0) {
                sheet.setDefaultColumnWidth(25);
            } else {
                sheet.setDefaultColumnWidth(15);
            }
            row = getRow(sheet,0);
            //设置表头
            getCell(row,c).setCellValue(columns.get(c).getTitle());
            //设置数据
            for (int r = 0; r< rows;r++) {
                val = getBeanVal(list.get(r),columns.get(c).getProperty());
                if (null != columns.get(c).getCellEditor()) {
                    val = columns.get(c).getCellEditor().getCellEditorValue();
                }
                if (null != val) {
                    row = getRow(sheet,r+1);
                    if (val instanceof Number) {
                        double number = Double.parseDouble(val.toString());
                        getCell(row,c).setCellValue(number);
                    } else {
                        getCell(row,c).setCellValue(new HSSFRichTextString(val.toString()));
                    }
                }
            }
        }
        return workbook;
    }

    /**
     * 获取工作表中指定的行
     *
     * @param sheet 工作表
     * @param index 工作行索引
     * @return 指定行
     */
    private static HSSFRow getRow(HSSFSheet sheet, int index) {
        HSSFRow row = sheet.getRow(index);
        if (null == row) {
            row = sheet.createRow(index);
        }
        return row;
    }

    /**
     * 获取工作表中指定的列
     *
     * @param row   工作行
     * @param index 工作列索引
     * @return 指定的列
     */
    private static HSSFCell getCell(HSSFRow row, int index) {
        HSSFCell cell = row.getCell(index);
        if (null == cell) {
            cell = row.createCell(index, CellType.STRING);
        }
        return cell;
    }

    /**
     * 根据属性字段获取数据
     *
     * @param bean 数据实体
     * @param prop 属性
     * @return 数据
     */
    private static Object getBeanVal(Object bean, String prop) {
        Object itemValue = null;
        try {
            if (bean instanceof Map) {
                itemValue = ((Map) bean).get(prop);
            } else {
                itemValue = PropertyUtils.getProperty(bean, prop);
            }
        } catch (Exception e) {
            LOGGER.debug("获取数据出现异常，beanName=" + bean.getClass().getName() + "\tprop=" + prop, e);
        }
        return itemValue;
    }
}
