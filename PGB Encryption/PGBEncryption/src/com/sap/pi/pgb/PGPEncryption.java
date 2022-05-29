/*Java Mapping for Encryption*/

 

package com.sap.pi.pgb;

 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.StreamTransformation;
import com.sap.aii.mapping.api.StreamTransformationConstants;
import com.sap.aii.mapping.api.StreamTransformationException;

 

public class PGPEncryption implements StreamTransformation {

      private Map param;
      private AbstractTrace trace;
      private String CHECK_ID;
      private String INVOICE_NUMBER;
      private String PAYMENT_VOUCHER_NO;
      private Date PAYMENT_DATE;
 

      public void setParameter(Map param) {

            this.param = param;

      }

 

      public void execute(InputStream in, OutputStream out) throws StreamTransformationException {

            if (param instanceof java.util.Map) {

                  trace = (AbstractTrace) ((Map) param).get(StreamTransformationConstants.MAPPING_TRACE);

            }

            try {

                  String publicKeyPath = "/com/sap/pi/pgp/publickey.pkr";

                  // Encrypt the message

                  new PGPCrypto().encrypt(publicKeyPath, in, out, trace);

            } catch (Exception e) {

                  e.printStackTrace();

            }

      }
      
      


      // for unit testing.
      
      public static void main(String[] args) throws Exception {

      {
    	  PGPEncryption obj= new PGPEncryption();
    	  
            try {            

                  obj.execute(new FileInputStream("D:\\PGP\\plainText.txt"), new FileOutputStream("C:\\PGP\\CypherText.asc"));

            } catch (Exception e) {

            }

      }

}
}




/*
package com.didisoft.pgp;



import com.didisoft.pgp.PGPLib;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EncryptString {
	

	public static void encryptString(String CHECK_ID, String invoiceno, String PAYMENT_VOUCHER_NO, String PAYMENT_DATE) throws Exception
	{
        // create an instance of the library
        PGPLib pgp = new PGPLib();
 
        String publicEncryptionKeyFile = "D:\\PGP\\public.key";        
 
        InputStream publicEncryptionKeyStream = null;
        try
        {
		       	publicEncryptionKeyStream = new FileInputStream(publicEncryptionKeyFile);
		        // encrypt     	
		       	String [] names = {CHECK_ID, invoiceno, PAYMENT_VOUCHER_NO, PAYMENT_DATE};
		         	
		       	for(String str:names)
		       	{	
		       		String encryptedString = pgp.encryptString(str, publicEncryptionKeyStream);
		       		System.out.println("encryptedString"+ encryptedString);
		       		System.out.println("------------------------------");
		       	}
		       	
		     
        }
        
        finally 
        {
             if (publicEncryptionKeyStream != null)
             	publicEncryptionKeyStream.close();
       }      
		
		
	}
	
    public static void main(String[] args) throws Exception {
    	
    	encryptString("a","b","c","d");
    	
    	
    }
    
}*/




















