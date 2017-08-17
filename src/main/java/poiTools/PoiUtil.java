/**
 *
 */
package poiTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author CHEN XINGXING
 * poi工具类
 */
public class PoiUtil {
    /**
     * 处理2007以上版本的excel文件后缀为.xlsx 注：第一个list为标题行
     *
     * @param file 带.xlsx的文件
     * @return 返回这种格式易于转换
     * @throws IOException 抛出文件路径未找到等异常
     */
    public static List<List<String>> excel2007ToList(File file)
            throws IOException {
        InputStream inp = new FileInputStream(file);
        // 通过流得到workbook对象
        XSSFWorkbook workbook = new XSSFWorkbook(inp);

        List<List<String>> result = ergodicWorkbook(workbook);

        workbook.close();
        return result;

    }

    /**
     * 遍历每一页,保存结果
     *
     * @param workbook
     */
    private static List<List<String>> ergodicWorkbook(Workbook workbook) {
        // 保存结果
        List<List<String>> result = new ArrayList<List<String>>();
        // 遍历每一页
        for (Sheet sheet : workbook) {
            // 遍历每一个sheet
            for (Row row : sheet) {
                // 遍历每一行
                // 每一行是一个list<String>
                List<String> rows = new ArrayList<String>();
                // 遍历每一个单元格
                for (Cell cell : row) {
                    rows.add(cell.toString());
                }
                result.add(rows);
            }
        }
        return result;
    }

    /**
     * @param file 带.xls的文件
     * @return 返回这种格式易于转换
     * @throws IOException 抛出文件路径未找到等异常
     * 处理2003版本的excel文件后缀为.xls 注：第一个list为标题行
     */
    public static List<List<String>> excel2003ToList(File file)
            throws IOException {
        InputStream inp = new FileInputStream(file);
        // 通过流得到workbook对象
        HSSFWorkbook workbook = new HSSFWorkbook(inp);
        List<List<String>> result = ergodicWorkbook(workbook);
        workbook.close();
        return result;

    }

    /**
     * @param file 传入文件
     * @return 如果传入的不是excel文档则抛出异常或者返回空
     * @throws IOException
     * 自动判断是2003或者是2007文档
     */
    public static List<List<String>> excelToList(File file) throws IOException {
        String fileSuffix = file.getName();
        List<List<String>> result = new ArrayList<List<String>>();
        if (fileSuffix.endsWith(".xlsx")) {
            result = PoiUtil.excel2007ToList(file);
        }
        if (fileSuffix.endsWith(".xls")) {
            result = PoiUtil.excel2003ToList(file);
        }
        return result;

    }

    /**
     * @param file
     * @param list 要写入文件的字符串
     * @throws IOException
     * 将字符写入excel2007文件
     */
    public static void stringToExcel2007(File file, List<List<String>> list)
            throws IOException {
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录(推荐以后使用这个方法)
            file.getParentFile().mkdirs();
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < list.size(); i++) {
            XSSFRow rows = sheet.createRow(i);
            for (int j = 0; j < list.get(i).size(); j++) {
                rows.createCell(j).setCellValue(list.get(i).get(j));
            }
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    /**
     * @param file
     * @param list 要写入文件的字符串
     * @throws IOException
     * 将字符写入excel2003文件
     */
    public static void stringToExcel2003(File file, List<List<String>> list)
            throws IOException {
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录(推荐以后使用这个方法)
            file.getParentFile().mkdirs();
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow rows = sheet.createRow(i);
            for (int j = 0; j < list.get(i).size(); j++) {
                rows.createCell(j).setCellValue(list.get(i).get(j));
            }
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    /**
     * @param file
     * @param list
     * @throws IOException
     * 自动判断是excel2003或者excel2007
     */
    public static void stringToExcel(File file, List<List<String>> list)
            throws IOException {
        String fileSuffix = file.getName();
        if (fileSuffix.endsWith(".xlsx")) {
            PoiUtil.stringToExcel2007(file, list);
        }
        if (fileSuffix.endsWith(".xls")) {
            PoiUtil.stringToExcel2003(file, list);
        }
    }
}
