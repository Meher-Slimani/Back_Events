package com.onegateafrica.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.onegateafrica.entity.Guest;

public class GuestExcelImporter {

  private GuestExcelImporter(){}

  public static List<Guest> excelImport(MultipartFile excel)  {

    List<Guest> guestList = new ArrayList<>();


    try(Workbook workbook = new XSSFWorkbook(excel.getInputStream());) {
      Sheet firstSheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = firstSheet.iterator();
      rowIterator.next();
      while (rowIterator.hasNext()) {
        Row nextRow = rowIterator.next();
        Guest newGuest = new Guest();
        newGuest.setGuestName(nextRow.getCell(0).getStringCellValue());
        newGuest.setGuestEmail(nextRow.getCell(1).getStringCellValue());
        newGuest.setInvitationStatus("Pending");
        guestList.add(newGuest);
      }

    }catch (Exception e) {
      e.printStackTrace();
    }


    return guestList;
  }


}

