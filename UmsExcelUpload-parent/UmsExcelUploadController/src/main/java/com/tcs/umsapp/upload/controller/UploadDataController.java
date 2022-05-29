package com.tcs.umsapp.upload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.tcs.umsapp.upload.commons.ErrorList;
import com.tcs.umsapp.upload.commons.ExcelError;
import com.tcs.umsapp.upload.commons.FileContent;
import com.tcs.umsapp.upload.controller.util.ExcelDataValidation;
import com.tcs.umsapp.upload.persist.dao.UserDetailDao;
import com.tcs.umsapp.upload.persist.dao.UserDetailDaoImpl;
import com.tcs.umsapp.upload.response.UploadFileDetailResponseSO;
import com.tcs.umsapp.upload.vo.cmo.UmsappResponseObject;



public class UploadDataController {
    private static final Logger LOGGER = Logger
            .getLogger(UploadDataController.class);

    private String loginUserId;
    
    public UmsappResponseObject addRoleDetail(MultipartFile file,String loginUserId) {
        
        this.loginUserId = loginUserId;
        LOGGER.info("Log in user userid: " + loginUserId);
        String extension = FilenameUtils.getExtension(file
                .getOriginalFilename());
        LOGGER.info("File Extension: " + extension);
        UmsappResponseObject umsappResponseObject = null;

        // Read xls file
        if (extension.equalsIgnoreCase("xls")) {
            umsappResponseObject = readXlsFile(file);
        }

        // Read xlsx file
        else if (extension.equalsIgnoreCase("xlsx")) {
            umsappResponseObject = readXlsxFile(file);
        }

        else {
            umsappResponseObject = new UmsappResponseObject();
        }

        return umsappResponseObject;

    }

    private UmsappResponseObject readXlsFile(MultipartFile file) {

        HSSFWorkbook wb = null;

        UploadFileDetailResponseSO uploadFileDetailResponseSO = new UploadFileDetailResponseSO();
        uploadFileDetailResponseSO.setStatus("2");
        List<ExcelError> excelErrorList = new ArrayList<ExcelError>();
        try {
            InputStream inputStream = new BufferedInputStream(
                    file.getInputStream());
            wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            uploadFileDetailResponseSO = validateExcelRecords(rowIterator);

        } catch (IOException e) {
            uploadFileDetailResponseSO.setStatus("1");
            excelErrorList.add(new ExcelError(0, 0, ErrorList.ERR_GEN_0001));
            uploadFileDetailResponseSO.setErrorList(excelErrorList);
            e.printStackTrace();
        } finally {
            try {
                if (wb != null)
                    wb.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return uploadFileDetailResponseSO;

    }

    private UmsappResponseObject readXlsxFile(MultipartFile file) {

        XSSFWorkbook myWorkBook = null;
        UploadFileDetailResponseSO uploadFileDetailResponseSO = new UploadFileDetailResponseSO();
        List<ExcelError> excelErrorList = new ArrayList<ExcelError>();

        try {
            InputStream inputStream = new BufferedInputStream(
                    file.getInputStream());
            myWorkBook = new XSSFWorkbook(inputStream);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator<Row> rowIterator = mySheet.iterator();
            uploadFileDetailResponseSO = validateExcelRecords(rowIterator);

        } catch (IOException e) {
            uploadFileDetailResponseSO.setStatus("1");
            excelErrorList.add(new ExcelError(0, 0, ErrorList.ERR_GEN_0001));
            uploadFileDetailResponseSO.setErrorList(excelErrorList);
            e.printStackTrace();
        } finally {
            if (myWorkBook != null) {
                try {
                    myWorkBook.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        return uploadFileDetailResponseSO;
    }

    public UploadFileDetailResponseSO validateExcelRecords(
            Iterator<Row> rowIterator) {
        FileContent fileContent;
        ExcelError excelError;
        boolean validHeader = true;

        UploadFileDetailResponseSO uploadFileDetailResponseSO = new UploadFileDetailResponseSO();
        uploadFileDetailResponseSO.setStatus("2");
        ExcelDataValidation excelDataValidation = new ExcelDataValidation();
        List<FileContent> userRoles = new ArrayList<FileContent>();
        List<String> errorList = new ArrayList<String>();
        List<ExcelError> excelErrorList = new ArrayList<ExcelError>();        
        UserDetailDao userDetailDao = new UserDetailDaoImpl();
        
        String logInUserBranch = null;

        try {
            if (rowIterator.hasNext()) {
                System.out.println("Header validation Started");
                Row row = rowIterator.next();
                excelError = excelDataValidation.validateHeader(row);
                System.out.println("Error: " + excelError.getError());
                if (excelError.getError() != null) {
                    validHeader = false;
                    excelErrorList.add(excelError);

                } else {
                    
                    System.out.println("Data validation Start");
                    DecimalFormat formatter = new DecimalFormat("0");
                    while (rowIterator.hasNext() && validHeader) {
                        List roles=new ArrayList();
                        List rolesRemove=new ArrayList();
                        String SRNumber = null, officeCode = null, Application = null, Role = null, Action = null, AppId= null,AppId1= null, RoleId=null, RoleId1=null ;
                        row = rowIterator.next();
                        int rowNum = row.getRowNum();
                        String userBranch = null;
                        // Validate SR NUMBER

                        excelError = excelDataValidation.validateSRNumber(row
                                .getCell(0).toString().trim(), rowNum);

                        if (excelError.getError() == null) {
                            SRNumber = formatter.format(Double.parseDouble(row
                                    .getCell(0).toString().trim()));
                            userBranch = userDetailDao.getLoginUserBranch(SRNumber);
                            
                            logInUserBranch = userDetailDao.getLoginUserBranch(this.loginUserId);
                            LOGGER.info("Log in user userid: " + this.loginUserId);
                            LOGGER.info("Log in user branch: " + logInUserBranch);
                            if(logInUserBranch.substring(0, 2).equals("10")){
                            	SRNumber = formatter.format(Double.parseDouble(row
                                    .getCell(0).toString().trim()));
                            
                             }
                            else{
                                
                                LOGGER.info("SR: " + SRNumber + " Branch: " + userBranch);
                                if(logInUserBranch.equals(userBranch)){
                                    SRNumber = formatter.format(Double.parseDouble(row
                                            .getCell(0).toString().trim()));
                                    LOGGER.info("If else");
                                }
                                else{
                                    excelError = new ExcelError(rowNum, 0, ErrorList.ERR_SRNUM_0003);
                                    LOGGER.info("else");
                                }
                            }
                            System.out.println("SR Number Added");
                        } else {
                            excelErrorList.add(excelError);
                        }

                        // Validate Office Code
                        excelError = excelDataValidation.validateOfficeCode(row
                                .getCell(1).toString().trim(), rowNum);

                        if (excelError.getError() == null) {
                            //checking office code
                            LOGGER.info("userBranch"+userBranch);
                            
                            if(userBranch == null){
                                
                                excelError = new ExcelError(rowNum, 0, ErrorList.ERR_SRNUM_0001);
                                LOGGER.info("if userBranch null");
                                excelErrorList.add(excelError);
                                
                            }
                            LOGGER.info("equalsIgnoreCase(row.getCell(1).toString().trim())"+userBranch.equalsIgnoreCase(row.getCell(1).toString().trim()));
                            LOGGER.info("user branch "+row.getCell(1).toString());
                            if(userBranch.equalsIgnoreCase(formatter.format(Double.parseDouble(row.getCell(1).toString().trim()))))
                            {
                            
                            officeCode = formatter.format(Double
                                    .parseDouble(row.getCell(1).toString()
                                            .trim()));
                            System.out.println("Office Code Added");
                        } else{
                            //error added for office code
                            excelError = new ExcelError(rowNum, 0, ErrorList.ERR_SRNUM_0004);
                            excelErrorList.add(excelError);
                            LOGGER.info("else");
                            
                        }
                        }
                        
                        else {
                            excelErrorList.add(excelError);
                        }
                        boolean isValidRole=true;
                        // Validate Application
                        List<String> userAppList = userDetailDao.getAppAccessList(this.loginUserId);
                       
                        excelError = excelDataValidation.validateApplication(
                                row.getCell(2).toString().trim(), rowNum,userAppList,logInUserBranch);
                        
                        LOGGER.info("row  : "+row.getCell(2).toString().trim());
                        LOGGER.info("userAppList  : "+userAppList);
                        
                        if (excelError.getError() == null) {
                            
                            Application = row.getCell(2).toString().trim();
                            
                            //application Id// 
                            List<String> userAppId = userDetailDao.getAppId(Application);
                            
                            AppId = userAppId.toString();
                            LOGGER.info("AppId"+AppId);
                            
                            
                            
                            AppId1 = AppId;
                            
                            AppId1 = AppId1.replace("[", "");
                            AppId1 = AppId1.replace("]", "");
                            
                            LOGGER.info("AppId1 " + AppId1);
                        
                        
                            System.out.println("Application Added");
                        } else {
                            excelErrorList.add(excelError);
                        }

                        // Validation for Role

                        excelError = excelDataValidation.validateRole(row
                                .getCell(3).toString().trim(), rowNum,logInUserBranch);
                        
                        if (excelError.getError() == null) {
                            
                            Role = row.getCell(3).toString().trim();
                            LOGGER.info("AppId1 "+AppId1);
                            LOGGER.info("Role "+Role);
                            //role Id// 
                            
                            List<String> userRoleId = userDetailDao.getRoleId(AppId1,Role);
                            RoleId = userRoleId.toString();
                            LOGGER.info("RoleId"+RoleId);
                            
                            //checking roleif is null
                            if(RoleId == "[]"){

                                excelError = new ExcelError(rowNum, 0, ErrorList.ERR_ROLEID_0004);
                                excelErrorList.add(excelError);
                                LOGGER.info("role id is null");
                            }
                            //checking if already have access to role
                            LOGGER.info("checking if already have access to role");
                            URL url = null;
                            HttpsURLConnection connection = null;
                            
                            url = new URL(com.tcs.umsapp.upload.vo.util.UtilProperties.getUserRoleSearchUrl());
                            StringBuilder response = new StringBuilder();

                            connection = (HttpsURLConnection) url.openConnection();

                            connection.setDoOutput(true);
                            connection.setRequestMethod("POST");
                            connection.setRequestProperty("Content-Type", "application/json");

                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                                    connection.getOutputStream());
                            outputStreamWriter.write("{\"userId\":"
                                    + formatter.format(Double.parseDouble(row
                                            .getCell(0).toString().trim())) + "}");
                            outputStreamWriter.flush();
                            outputStreamWriter.close();
                            System.out.println(connection.getResponseCode());
                            if (connection.getResponseCode() == 200) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(
                                        (connection.getInputStream())));
                                String s = null;
                                while ((s = br.readLine()) != null) {
                                    response.append(s);
                                }
                                LOGGER.info("-ServiceResponse -" + response);
                            }
                           
                            Gson gson = new Gson();
                            com.tcs.umsapp.upload.commons.UserAppAndRoleAccessDetails dt = (com.tcs.umsapp.upload.commons.UserAppAndRoleAccessDetails) gson.fromJson(response.toString(),
                                    com.tcs.umsapp.upload.commons.UserAppAndRoleAccessDetails.class);
                            List<Integer> exstingRoles = new ArrayList<Integer>();
                      
                            for (Entry<String, List<com.tcs.umsapp.upload.commons.UserRoleDetails>> entry : dt
                                    .getAppAndRoleAccessDetails().entrySet()) {
                               
                                for (com.tcs.umsapp.upload.commons.UserRoleDetails dtl : entry.getValue()) {
                                   
                                   
                                    System.out.println("Role Added");
                                    LOGGER.info("role excel "+row.getCell(3).toString().trim());
                                    LOGGER.info("getRoleName"+dtl.getRoleName());
                                    
                                    if (dtl.getRoleName().equals(row.getCell(3).toString().trim())&& row
                                            .getCell(4).toString().trim().equalsIgnoreCase("add")) {
                                    
                                        excelError = new ExcelError(rowNum, 3, ErrorList.ERR_ROLEEXIST_0001);
                                        excelErrorList.add(excelError);
                                        LOGGER.info("role already exsist");
                                        
                                    }
                                    else{
                                        
                                        if(RoleId != null){
                                            LOGGER.info("role id is not null");
                                        
                                        RoleId1 = RoleId;
                                        
                                        RoleId1 = RoleId1.replace("[", "");
                                        RoleId1 = RoleId1.replace("]", "");
                                        
                                       
                                        
                                        //checking if role is present for application
                                        }
                                        else{
                                            
                                            excelError = new ExcelError(rowNum, 0, ErrorList.ERR_ROLEID_0002);
                                            excelErrorList.add(excelError);
                                            LOGGER.info("Invalid Role Name");
                                            isValidRole=false;
                                            
                                        }
                                        
                                        
                                    }
                                    
                                    
                                    if (dtl.getRoleName().equals(row.getCell(3).toString().trim())&& row
                                            .getCell(4).toString().trim().equalsIgnoreCase("remove")&&(dtl.getStatus().equalsIgnoreCase("R")||dtl.getStatus().equalsIgnoreCase("I"))) {
                                        roles.add(dtl.getRoleName());
                                        LOGGER.info("role is pending exsist");
                                        
                                    }
                                    
                                    
                                    rolesRemove.add(dtl.getRoleName());

                                }
                            }

                        } else {
                            
                            
                            excelErrorList.add(excelError);
                        }

                        LOGGER.info("Exsting roles for User "+roles);
                        LOGGER.info("XL roles for User "+ row.getCell(3).toString().trim());
                        
                        if(roles.contains(row.getCell(3).toString().trim())&& row
                                .getCell(4).toString().trim().equalsIgnoreCase("remove"))
                        {
                           
                            excelError = new ExcelError(rowNum, 3, ErrorList.ERR_ROLEPENDING_0001);
                            excelErrorList.add(excelError);
                          
                        }
                        
                        LOGGER.info("rolesRemove "+rolesRemove);
                        
                        if(!rolesRemove.contains(row.getCell(3).toString().trim())&& row
                                .getCell(4).toString().trim().equalsIgnoreCase("remove"))
                        {
                           
                            excelError = new ExcelError(rowNum, 3, ErrorList.ERR_ROLENOTEXIST_0001);
                            excelErrorList.add(excelError);
                          
                        }
                        
                        
                        
                        // Validation for Action

                        excelError = excelDataValidation.validateAction(row
                                .getCell(4).toString().trim(), rowNum);
                        if (excelError.getError() == null) {
                            Action = row.getCell(4).toString().trim();

                            Action = Action.toLowerCase().equalsIgnoreCase(
                                    "add") ? "A" : "D";

                            System.out.println("Action Added");
                        } else {
                            excelErrorList.add(excelError);
                        }
                        


                        fileContent = new FileContent(SRNumber, officeCode,
                                Application, Role, Action, AppId1, RoleId1);
                        userRoles.add(fileContent);
                        
                        System.out.println("Detail: " + fileContent.toString());
                        
                        

                        
                    }   

                }

            } else {
                uploadFileDetailResponseSO.setStatus("1");
                errorList.add(ErrorList.ERR_GEN_0002);

            }

        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
        uploadFileDetailResponseSO.setErrorList(excelErrorList);
        uploadFileDetailResponseSO.setStatus("0");
        
        uploadFileDetailResponseSO.setUserRoles(userRoles);
        uploadFileDetailResponseSO.setRequestBy(this.loginUserId);
        return uploadFileDetailResponseSO;
         
    }

}
