import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityClass {
	public List<String> returnData(String path, String sheetName) throws IOException{
		List<String> allrowsdata = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(path);

		//Create xssfworkbook object
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		//Get the sheet you want
		XSSFSheet sheet = workbook.getSheet(sheetName);

		
		Iterator<Row> itrow = sheet.iterator();
		while(itrow.hasNext()) {
			
			//Get a row number
			Row row = itrow.next();

			Iterator<Cell> it = row.iterator();
			while(it.hasNext())
			{
				//Get the Cell
				Cell cell = it.next();
				DataFormatter df = new DataFormatter();
				String cellvalue = df.formatCellValue(cell);
				allrowsdata.add(cellvalue);
			}
		}
		
		workbook.close();
		return allrowsdata;
	}
	
	public void writeData(String path, String sheetName, String value) {
		FileInputStream fis = null;

		try {
			//Get access to the workbook
			fis = new FileInputStream(path);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

		//Create workbook object
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Get the sheet you want
		XSSFSheet sheet = workbook.getSheet(sheetName);

		//Get the column names row
		int lastRowNo = sheet.getLastRowNum();
		System.out.println("last row number is "+lastRowNo);
		
		FileOutputStream fos = null;

		try {
			//Get access to the workbook
			fos = new FileOutputStream(path);
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

		Row rowtoput = sheet.createRow(lastRowNo+1);
		int actualcolindex = 0;
		Cell celltoput = rowtoput.createCell(actualcolindex);
		
		celltoput.setCellValue(value);
		try {
			workbook.write(fos);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}