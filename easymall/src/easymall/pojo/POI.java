package easymall.pojo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
public class POI {
	public POI() {}
	
	public void showExcel(List<Products> products) {
		//System.out.println("start to create excel");
		HSSFWorkbook workbook = new HSSFWorkbook();  // 创建一个excel
		// excel生成过程: excel-->sheet-->row-->cell
		HSSFSheet sheet = workbook.createSheet(); // 为excel创建一个名为test的sheet页
		workbook.setSheetName(0, "销售数据",HSSFCell.ENCODING_UTF_16);
		
		//3.创建标题行
		HSSFRow titlerRow = sheet.createRow(0);
		HSSFCell cell00=titlerRow.createCell((short) 0);
        cell00.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell00.setCellValue("商品名称");
        HSSFCell cell11=titlerRow.createCell((short) 1);
        cell11.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell11.setCellValue("商品价格");
        HSSFCell cell22=titlerRow.createCell((short) 2);
        cell22.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell22.setCellValue("商品类别");
        HSSFCell cell33=titlerRow.createCell((short) 3);
        cell33.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell33.setCellValue("销售数量");
//		titlerRow.createCell((short) 0).setCellValue("商品名称");
//		titlerRow.createCell((short) 1).setCellValue("商品价格");
//		titlerRow.createCell((short) 2).setCellValue("商品类别");
//		titlerRow.createCell((short) 3).setCellValue("销售数量");
		
		HSSFRow row = sheet.createRow((int)0);
		for (Products area : products) {
             //获取最后一行的行号
             int lastRowNum = sheet.getLastRowNum();
             HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
             HSSFCell cell0=dataRow.createCell((short) 0);
             //System.out.println(area.getName());
             cell0.setEncoding(HSSFCell.ENCODING_UTF_16);
             cell0.setCellValue(area.getName());
             HSSFCell cell1=dataRow.createCell((short) 1);
             cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
             cell1.setCellValue(area.getPrice());
             HSSFCell cell2=dataRow.createCell((short) 2);
             cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
             cell2.setCellValue(area.getCategory());
             HSSFCell cell3=dataRow.createCell((short) 3);
             cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
             cell3.setCellValue(area.getSoldnum());
             
//             dataRow.createCell((short) 0).setCellValue(area.getName());
//             dataRow.createCell((short) 1).setCellValue(area.getPrice());
//             dataRow.createCell((short) 2).setCellValue(area.getCategory());
//             dataRow.createCell((short) 3).setCellValue(area.getPnum());    
			         }
//		HSSFRow row = sheet.createRow(1); // 创建一行,参数2表示第一行
//		HSSFCell cellB2 = row.createCell(1); // 在B2位置创建一个单元格
//		HSSFCell cellB3 = row.createCell(2); // 在B3位置创建一个单元格
//		cellB2.setCellValue("单元格B2"); // B2单元格填充内容
//		cellB3.setCellValue("单元格B3"); // B3单元格填充内容
//		
//		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 单元格样式
//		Font fontStyle = workbook.createFont(); // 字体样式
//		fontStyle.setBold(true); // 加粗
//		fontStyle.setFontName("黑体"); // 字体
//		fontStyle.setFontHeightInPoints((short) 11); // 大小
//		// 将字体样式添加到单元格样式中 
//		cellStyle.setFont(fontStyle);
//		// 边框，居中
//		cellStyle.setAlignment(HorizontalAlignment.CENTER);
//		cellStyle.setBorderBottom(BorderStyle.THIN);
//		cellStyle.setBorderLeft(BorderStyle.THIN);
//		cellStyle.setBorderRight(BorderStyle.THIN);
//		cellStyle.setBorderTop(BorderStyle.THIN);
//		cellB2.setCellStyle(cellStyle); // 为B2单元格添加样式
		
		// 合并单元格
//		CellRangeAddress cra =new CellRangeAddress(1, 3, 1, 3); // 起始行, 终止行, 起始列, 终止列
//		sheet.addMergedRegion(cra);
		
		// 使用RegionUtil类为合并后的单元格添加边框
//		RegionUtil.setBorderBottom(1, cra, sheet); // 下边框
//		RegionUtil.setBorderLeft(1, cra, sheet); // 左边框
//		RegionUtil.setBorderRight(1, cra, sheet); // 有边框
//		RegionUtil.setBorderTop(1, cra, sheet); // 上边框
		
		// 输出到本地
		String excelName = "d:/销售数据.xls";
		FileOutputStream out = null;
		OutputStreamWriter out2 = null;
		try {
			out = new FileOutputStream(excelName);
//			out2=new OutputStreamWriter(new FileOutputStream(excelName),"utf-8");
			workbook.write(out);
			out.flush();
			out.close();
			//System.out.println("输出成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}
	}
 
}