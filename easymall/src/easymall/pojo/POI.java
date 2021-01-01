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
		HSSFWorkbook workbook = new HSSFWorkbook();  // ����һ��excel
		// excel���ɹ���: excel-->sheet-->row-->cell
		HSSFSheet sheet = workbook.createSheet(); // Ϊexcel����һ����Ϊtest��sheetҳ
		workbook.setSheetName(0, "��������",HSSFCell.ENCODING_UTF_16);
		
		//3.����������
		HSSFRow titlerRow = sheet.createRow(0);
		HSSFCell cell00=titlerRow.createCell((short) 0);
        cell00.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell00.setCellValue("��Ʒ����");
        HSSFCell cell11=titlerRow.createCell((short) 1);
        cell11.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell11.setCellValue("��Ʒ�۸�");
        HSSFCell cell22=titlerRow.createCell((short) 2);
        cell22.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell22.setCellValue("��Ʒ���");
        HSSFCell cell33=titlerRow.createCell((short) 3);
        cell33.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell33.setCellValue("��������");
//		titlerRow.createCell((short) 0).setCellValue("��Ʒ����");
//		titlerRow.createCell((short) 1).setCellValue("��Ʒ�۸�");
//		titlerRow.createCell((short) 2).setCellValue("��Ʒ���");
//		titlerRow.createCell((short) 3).setCellValue("��������");
		
		HSSFRow row = sheet.createRow((int)0);
		for (Products area : products) {
             //��ȡ���һ�е��к�
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
//		HSSFRow row = sheet.createRow(1); // ����һ��,����2��ʾ��һ��
//		HSSFCell cellB2 = row.createCell(1); // ��B2λ�ô���һ����Ԫ��
//		HSSFCell cellB3 = row.createCell(2); // ��B3λ�ô���һ����Ԫ��
//		cellB2.setCellValue("��Ԫ��B2"); // B2��Ԫ���������
//		cellB3.setCellValue("��Ԫ��B3"); // B3��Ԫ���������
//		
//		HSSFCellStyle cellStyle = workbook.createCellStyle(); // ��Ԫ����ʽ
//		Font fontStyle = workbook.createFont(); // ������ʽ
//		fontStyle.setBold(true); // �Ӵ�
//		fontStyle.setFontName("����"); // ����
//		fontStyle.setFontHeightInPoints((short) 11); // ��С
//		// ��������ʽ��ӵ���Ԫ����ʽ�� 
//		cellStyle.setFont(fontStyle);
//		// �߿򣬾���
//		cellStyle.setAlignment(HorizontalAlignment.CENTER);
//		cellStyle.setBorderBottom(BorderStyle.THIN);
//		cellStyle.setBorderLeft(BorderStyle.THIN);
//		cellStyle.setBorderRight(BorderStyle.THIN);
//		cellStyle.setBorderTop(BorderStyle.THIN);
//		cellB2.setCellStyle(cellStyle); // ΪB2��Ԫ�������ʽ
		
		// �ϲ���Ԫ��
//		CellRangeAddress cra =new CellRangeAddress(1, 3, 1, 3); // ��ʼ��, ��ֹ��, ��ʼ��, ��ֹ��
//		sheet.addMergedRegion(cra);
		
		// ʹ��RegionUtil��Ϊ�ϲ���ĵ�Ԫ����ӱ߿�
//		RegionUtil.setBorderBottom(1, cra, sheet); // �±߿�
//		RegionUtil.setBorderLeft(1, cra, sheet); // ��߿�
//		RegionUtil.setBorderRight(1, cra, sheet); // �б߿�
//		RegionUtil.setBorderTop(1, cra, sheet); // �ϱ߿�
		
		// ���������
		String excelName = "d:/��������.xls";
		FileOutputStream out = null;
		OutputStreamWriter out2 = null;
		try {
			out = new FileOutputStream(excelName);
//			out2=new OutputStreamWriter(new FileOutputStream(excelName),"utf-8");
			workbook.write(out);
			out.flush();
			out.close();
			//System.out.println("����ɹ�");
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