package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-31.
 * 读取excel表格中的数据，返回一个迭代器
 * 使用map.get(表头)获取单元格数据
 */
public class ExcelIterator implements Iterator<Object[]> {
    private int colNum;  //有效列
    private int rowNum;  //有效行
    private String columnName[]; //表头数组
    private int curRowNum = 0;       //当前行
    private Workbook book;
    private Sheet sheet;

    /**
     * 构造方法
     * @param filePath  读取文件路径+文件名，不带文件后缀名
     * @param sheetName 工作表的名称
     * @throws IOException
     */
    public ExcelIterator(String filePath, String sheetName) throws IOException {
         readExcel(filePath,sheetName);
    }

    /**
     * 读取表格数据，将表头数据存入数组中
     * @param fileName 文件路径+文件名
     * @param sheetName 工作表
     * @throws IOException
     */
    public void readExcel(String fileName,String sheetName) throws IOException {
        //创建一个文件流
        FileInputStream inputStream = new FileInputStream(fileName+".xls");
        //创建工作区间
        book = new HSSFWorkbook(inputStream);
        //获取工作表
        sheet = book.getSheet(sheetName);
        //获取表头数据
        Row row = sheet.getRow(0);
        //获取有效列
        colNum = row.getPhysicalNumberOfCells();
        //获取有效行
        rowNum = sheet.getPhysicalNumberOfRows();
        //声明表头数组
        columnName = new String[colNum];
        //将表头数据转换成单元格迭代器
        Iterator<Cell> cellItr = row.cellIterator();
        int count = 0;
        //迭代器遍历
        while (cellItr.hasNext()){
            Cell cell = cellItr.next();
            //判断获取的单元格是否为空
            if (cell!=null){
            cell.setCellType(CellType.STRING);
            //获取表头单元格中的数据，并存入表头数组中
            columnName[count] = cell.getStringCellValue().toString();
            count++;
            }
        }
        //关闭文件流
        inputStream.close();
        this.curRowNum++;
    }

    public boolean hasNext() {
        if (rowNum == 0 || curRowNum>=rowNum){
            return false;
        }else {
            return true;
        }
    }

    public Object[] next() {
        Map<String,String> map = new HashMap();
        String temp;
        //获取当前行数据
        Row row = sheet.getRow(curRowNum);
        for (int i =0;i<colNum;i++){
            //使用for循环获取当前行的单元格
            Cell cell = row.getCell(i);
            if (cell !=null){
            cell.setCellType(CellType.STRING);
            temp = cell.getStringCellValue();
            //将当前行的单元格中的数据与其对应的表头存入map集合中
            map.put(columnName[i],temp);
            }
        }
        this.curRowNum++;
        Object[] objects = new Object[1];
        //将map集合中的数据存入一个数组中
        objects[0] = map;
        return objects;
    }

    public void remove() {

    }
}
