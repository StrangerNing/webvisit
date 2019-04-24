package com.webvisit.common.component;

import javax.swing.*;

/**
 * @author zening.zhu
 * @version 1.0
 * @date 2019/4/24
 */

public class Column {

    private String property;

    private String title;

    private CellEditor cellEditor;

    public Column(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public String getTitle() {
        return title;
    }

    public Column title(String title) {
        this.title = title;
        return this;
    }

    public CellEditor getCellEditor() {
        return cellEditor;
    }

    public void setCellEditor(CellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    public Column cellEditor(CellEditor editor) {
        setCellEditor(editor);
        return this;
    }
}
