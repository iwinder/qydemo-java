package Utills;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
//    public static void main(String[] args) {
//        XWPFDocument doc = null;
//        OPCPackage pack = null;
//        String fileSrc="";
//        try {
//            pack = POIXMLDocument.openPackage(fileSrc);
//            doc = new XWPFDocument(pack);
//            if (param != null && param.size() > 0) {
//                // 处理段落
//                List<XWPFParagraph> paragraphList = Arrays.asList(doc.getParagraphs());
////                processParagraphs(paragraphList, param, doc);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FileOutputStream fopts = null;
//        try {
//            fopts = new FileOutputStream(fileDest);
//            doc.write(fopts);
//            pack.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeStream(fopts);
//        }

    public static String readXls(String name) {
        FileInputStream in;
        StringBuilder content = new StringBuilder();
        try {
            in = new FileInputStream(name);
            HSSFWorkbook excel = new HSSFWorkbook(in);
            //获取第一个sheet
            HSSFSheet sheet0 = excel.getSheetAt(0);
            for (Iterator rowIterator = sheet0.iterator(); rowIterator.hasNext(); ) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                for (Iterator iterator = row.cellIterator(); iterator.hasNext(); ) {
                    HSSFCell cell = (HSSFCell) iterator.next();
                    //根据单元的的类型 读取相应的结果
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                        content.append(cell.getStringCellValue() + "\t");
                    else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA)
                        content.append(cell.getNumericCellValue() + "\t");
                    else
                        content.append("" + "\t");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String readXlsx(String name) {
        FileInputStream in;
        StringBuilder content = new StringBuilder();
        try {
            in = new FileInputStream(name);
            XSSFWorkbook excel = new XSSFWorkbook(in);
            //获取第一个sheet
            XSSFSheet sheet0 = excel.getSheetAt(0);
            for (Iterator rowIterator = sheet0.iterator(); rowIterator.hasNext(); ) {
                XSSFRow row = (XSSFRow) rowIterator.next();
                for (Iterator iterator = row.cellIterator(); iterator.hasNext(); ) {
                    XSSFCell cell = (XSSFCell) iterator.next();
                    //根据单元格的类型 读取相应的结果
                    if (cell.getCellType() ==   HSSFCell.CELL_TYPE_STRING)
                        content.append(cell.getStringCellValue() + "\t");
                    else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA)
                        content.append(cell.getNumericCellValue() + "\t");
                    else
                        content.append("" + "\t");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return content.toString();
    }
}
