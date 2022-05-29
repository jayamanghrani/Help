/*Java Mapping for Decryption:*/


package com.sap.pi.pgb;

 

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.Map;

 

import com.sap.aii.mapping.api.AbstractTrace;

import com.sap.aii.mapping.api.StreamTransformation;

import com.sap.aii.mapping.api.StreamTransformationConstants;

import com.sap.aii.mapping.api.StreamTransformationException;

 

public class PGPDecryption implements StreamTransformation {

      private Map param;

      private AbstractTrace trace;

 

      public void setParameter(Map param) {

            this.param = param;

      }

 

      public void execute(InputStream in, OutputStream out) throws StreamTransformationException {

            if (param instanceof java.util.Map) {

                  trace = (AbstractTrace) ((Map) param).get(StreamTransformationConstants.MAPPING_TRACE);

            }

            try {

                  new PGPCrypto().decrypt(in, out, “/com/sap/pi/pgp/Secring_dev.skr”, “Passphrase”, trace);

            } catch (Exception e) {

                  e.printStackTrace();

            }

      }

 

      // for unit testing.

      public static void main(String[] args) throws Exception {

            try {

                  new PGPDecryption().execute(new FileInputStream(“C:\\PGP\\CypherText.asc”), new FileOutputStream(“C:\\PGP\\plainText_N.txt”));

            } catch (Exception e) {

            }

      }

}

 

 

 