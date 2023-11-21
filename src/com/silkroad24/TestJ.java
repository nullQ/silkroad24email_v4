package com.silkroad24;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJ {
    public static void main(String args[]) throws FileNotFoundException {
        //  读取jasper模板文件
        JasperReport jasperReport = null;
        try {
            String jrxml = "C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\Wood.jrxml";
            jasperReport = JasperCompileManager.compileReport(jrxml);
            //  此行代码可解决部分打印空页问题
            jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
        } catch (JRException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置模板数据
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> rowMap1 = new HashMap<String, Object>();
            rowMap1.put("C1", "第:" + i + "行1列");
            rowMap1.put("C2", "第:" + i + "行1列");
            rowMap1.put("C3", "第:" + i + "行1列");
            rowMap1.put("C4", "第:" + i + "行1列");
            rowMap1.put("C5", "第:" + i + "行1列");
            dataList.add(rowMap1);
        }
        //设置paramters
        map.put("dataList", dataList);
        JasperPrint jasperPrint;
        Connection connection;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(dataList));
            // 生成PDF客户端
            JRPdfExporter exporter = new JRPdfExporter();
            try {


                HashMap parameters = new HashMap();

                FileInputStream inputStream = new FileInputStream("C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\Wood.jrxml");
                JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                connection = (Connection) DriverManager.getConnection("jdbc:sqlserver://192.168.0.89:1433;databaseName=XERERP", "sa", "_Memo");// 建立数据库连接
                // Pass compiled report to print
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                // jasperViewer.setVisible(true);
                JasperExportManager.exportReportToPdfFile(jasperPrint, "app_created_report.pdf");


                // exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                // exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("D:\\test1.pdf"));
                // exporter.exportReport();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JRException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}