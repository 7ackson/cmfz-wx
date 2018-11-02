package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/poi")
public class PoiController {
    @Autowired
    private UserService userService;

    @RequestMapping("/deriveAll")
    public void deriveAll(HttpServletResponse response) {
        List<User> listUser = userService.selectAll();
        List<String> listTitle = Arrays.asList("昵称", "性别", "生日");

        Workbook workbook = new HSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();

        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年mm月dd天");
        cellStyle.setDataFormat(format);

        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");
        cellStyle1.setFont(font);

        Sheet sheet = workbook.createSheet("user");

        sheet.setColumnWidth(2, 22 * 256);
        Row row = sheet.createRow(0);

        for (int i = 0; i < listTitle.size(); i++) {
            row.createCell(i).setCellValue(listTitle.get(i));
        }

        for (int i = 0; i < listUser.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Class<? extends User> aClass = listUser.get(i).getClass();
            row1.createCell(0).setCellValue(listUser.get(i).getName());
            if (listUser.get(i).getSex() == 0) {
                row1.createCell(1).setCellValue("男");
            } else {
                row1.createCell(1).setCellValue("女");
            }
            Cell cell = row1.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(listUser.get(i).getRegDate());
        }
        long time = new Date().getTime();
        String s = time + "文件.xls";
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importAll")
    public void importAll(MultipartFile excel) {
        List<User> list = new ArrayList();
        Workbook workbook = null;
        try {
            InputStream inputStream = excel.getInputStream();
            workbook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet("user");
        int j = sheet.getLastRowNum();
        for (int i = 1; i<sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            User u = new User();
            String name = row.getCell(0).getStringCellValue();
            int sex = 0;
            if (row.getCell(1).getStringCellValue().equals("男")) {
                sex = 0;
            } else {
                sex = 1;
            }

            Date date = row.getCell(2).getDateCellValue();
            u.setDharmaName(name);
            u.setSex(sex);
            u.setRegDate(date);
            list.add(u);
        }
        userService.inserts(list);
    }


    @RequestMapping("/selectImport")
    public void selectImport(HttpServletResponse response, String titles, String fileds) {
        List<User> userList = userService.selectAll();
        String[] filedArray = fileds.split(",");
        String[] titleArray = titles.split(",");
        Workbook workbook = new HSSFWorkbook();

        CellStyle cellStyle1 = workbook.createCellStyle();
        //设置日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年mm月dd天");
        cellStyle1.setDataFormat(format);

        Sheet sheet = workbook.createSheet("user");
        Row row = sheet.createRow(0);
        for (int i = 0; i < titleArray.length; i++) {
            row.createCell(i).setCellValue(titleArray[i]);
        }

        for (int i = 0; i < userList.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Class<? extends User> aClass = userList.get(i).getClass();
            for (int j = 0; j < filedArray.length; j++) {
                //getId
                String methodName = "get" + filedArray[j].substring(0, 1).toUpperCase() + filedArray[j].substring(1);
                try {
                    Object invoke = aClass.getDeclaredMethod(methodName, null).invoke(userList.get(i), null);
                    if (invoke instanceof Date) {
                        Cell cell = row1.createCell(j);
                        cell.setCellStyle(cellStyle1);
                        cell.setCellValue((Date) invoke);
                        sheet.setColumnWidth(j, 22 * 256);
                    } else if (invoke instanceof Integer) {
                        Cell cell = row1.createCell(j);
                        cell.setCellValue((Integer) invoke);
                    } else {
                        Cell cell = row1.createCell(j);
                        cell.setCellValue(String.valueOf(invoke));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long time = new Date().getTime();
        String s = time + "文件.xls";
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
