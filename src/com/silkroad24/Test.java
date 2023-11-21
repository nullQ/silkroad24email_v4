package com.silkroad24;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class Test {
    public static void main(String[] args) {
        JasperReport jasperReport;
        JasperPrint jasperPrint;

        Map map = new HashMap();
        map.put("code_id", 1201);// 报表主参数，直接传值！


        Connection connection;

        try {

            FileInputStream is = new FileInputStream("C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\mysql11.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(is);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);


            // 连结JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.0.88:3306/crm9?useUnicode=true&characterEncoding=utf-8", "root", "Qianmi123");// 建立数据库连接

            // Pass compiled report to print
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(false);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "app_created_report.pdf");
            System.out.println("输出完成");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void bj(String bjid) {


        JasperReport jasperReport;
        JasperPrint jasperPrint;

        Map map = new HashMap();
        map.put("code_id", bjid);// 报表主参数，直接传值！


        Connection connection;

        try {

            FileInputStream is = new FileInputStream("C:\\Users\\admin\\JaspersoftWorkspace\\MyReports\\mysql11.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(is);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);


            // 连结JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.0.88:3306/crm9?useUnicode=true&characterEncoding=utf-8", "root", "Qianmi123");// 建立数据库连接

            // Pass compiled report to print
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
            jasperViewer.setVisible(false);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "app_created_report.pdf");
            System.out.println("输出完成");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}