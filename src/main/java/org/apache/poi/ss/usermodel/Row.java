package org.apache.poi.ss.usermodel;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.record.formula.functions.Cell;

import java.util.Iterator;

public interface Row extends Iterable<Cell> {
    Row.MissingCellPolicy RETURN_NULL_AND_BLANK = new Row.MissingCellPolicy();
    Row.MissingCellPolicy RETURN_BLANK_AS_NULL = new Row.MissingCellPolicy();
    Row.MissingCellPolicy CREATE_NULL_AS_BLANK = new Row.MissingCellPolicy();

    Cell createCell(int var1);

    Cell createCell(int var1, int var2);

    void removeCell(Cell var1);

    void setRowNum(int var1);

    int getRowNum();

    Cell getCell(int var1);

    Cell getCell(int var1, Row.MissingCellPolicy var2);

    short getFirstCellNum();

    short getLastCellNum();

    int getPhysicalNumberOfCells();

    void setHeight(short var1);

    void setZeroHeight(boolean var1);

    boolean getZeroHeight();

    void setHeightInPoints(float var1);

    short getHeight();

    float getHeightInPoints();

    Iterator<Cell> cellIterator();

    Sheet getSheet();

    public static final class MissingCellPolicy {
        private static int NEXT_ID = 1;
        public final int id;

        private MissingCellPolicy() {
            this.id = NEXT_ID++;
        }
    }
}
