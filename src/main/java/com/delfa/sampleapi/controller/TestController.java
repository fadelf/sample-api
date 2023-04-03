package com.delfa.sampleapi.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(path = "/api",
            method = RequestMethod.GET)
    public ResponseEntity<?> getTestMessage() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(path = "/upload-xls",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadXlsInventory(
            @RequestPart("file") MultipartFile file) {

        try {
            int maxByteArraySize = 1024 * 1024 * 200; // 200 MB
            IOUtils.setByteArrayMaxOverride(maxByteArraySize);

            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum() + 1;
            inputStream.close();
            return ResponseEntity.ok().body("Number of rows in the Excel file: " + rowCount);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
