package com.nia.sign;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import com.nia.jpa.exception.Sftp_fname_dao_Exception;
import com.nia.jpa.exception.SignPayment_Exception;



public class DigitalSignature {

	private static DigitalSignature instance;	
	private File[] Plain_paymentFile_Array;
	private String Plainpayment_filename;

	protected static final Logger logger = Logger.getLogger( DigitalSignature.class );

	public static synchronized DigitalSignature getInstance()
	{
		if (instance == null)
		{
			instance = new DigitalSignature();
		}
		return instance;
	}


	public DigitalSignature()
	{

	}


	public boolean getPaymnetFileDigitalSignature(DigitalSignBean digitalSignbean) throws SignPayment_Exception {
		boolean signedPaymentStatus=false;

		String InboundFolderSign=digitalSignbean.getInboundFolderSign();
		String OutboundFolderSign=digitalSignbean.getOutboundFolderSign();
		String KEYSTORE_FILE_details=digitalSignbean.getKEYSTORE_FILE();
		String KEYSTORE_PWD=digitalSignbean.getKEYSTORE_PWD();
		String newExtension=digitalSignbean.getNewExtension();

		File INfolder = new File(InboundFolderSign);

		Plain_paymentFile_Array =INfolder.listFiles();

		for(File fpath :Plain_paymentFile_Array)
		{
			

			Plainpayment_filename =fpath.getName();
			
			
			String Plainpayment_filenamePrefix = Plainpayment_filename.substring(0, Plainpayment_filename.lastIndexOf("."));
			
			File infile = new File(InboundFolderSign + Plainpayment_filename);
			FileInputStream fileInputStream = null;
			byte[] bFile = new byte[(int)infile.length()];
			
			try
			{
				fileInputStream = new FileInputStream(infile);
				fileInputStream.read(bFile);
				fileInputStream.close();
				for (int i = 0; i < bFile.length; i++);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			Security.addProvider(new BouncyCastleProvider());

			KeyStore ks;
			try {
				
				
				ks = KeyStore.getInstance("PKCS12");
				FileInputStream	 fis = new FileInputStream(KEYSTORE_FILE_details);
				ks.load(fis, KEYSTORE_PWD.toCharArray());
		
			String KEYSTORE_ALIAS = (String)ks.aliases().nextElement();
			Key key = ks.getKey(KEYSTORE_ALIAS, KEYSTORE_PWD.toCharArray());

			PrivateKey privKey = (PrivateKey)key;
			Signature signature = Signature.getInstance("SHA256WithRSA", "BC");
			signature.initSign(privKey);
			signature.update(bFile);

			X509Certificate cert = (X509Certificate)ks.getCertificate(KEYSTORE_ALIAS);
			List certList = new ArrayList();
			CMSTypedData msg = new CMSProcessableByteArray(bFile);

			certList.add(cert);
			Store certs = new JcaCertStore(certList);
			CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
			ContentSigner sha1Signer;
			
				sha1Signer = new JcaContentSignerBuilder("SHA256withRSA").setProvider("BC").build(privKey);
				gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, cert));
				gen.addCertificates(certs);
				CMSSignedData sigData = gen.generate(msg, true);

				File outputfile = new File(OutboundFolderSign + Plainpayment_filenamePrefix +"."+newExtension);
				FileOutputStream bos = new FileOutputStream(outputfile);
				bos.write(sigData.getEncoded());
				bos.close();


				signedPaymentStatus=true;
			
			
			
			}
				
			catch (KeyStoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					logger.error("DigitalSignature  : Exception: "+ e1.getMessage());
					throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
					+ System.getProperty("line.separator")+
							"Error details - " +e1.getMessage() +System.getProperty("line.separator")+
							"Hence file transfer failed."+ System.getProperty("line.separator") +
							"Please check log file for more details on error. "
							+ System.getProperty("line.separator") +System.getProperty("line.separator"));
				}
			 catch (NoSuchAlgorithmException | CertificateException
						| IOException e) {
					
					e.printStackTrace();
					logger.error("DigitalSignature  : Exception: "+ e.getMessage());
					throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
					+ System.getProperty("line.separator")+
							"Error details - " +e.getMessage() +System.getProperty("line.separator")+
							"Hence file transfer failed."+ System.getProperty("line.separator") +
							"Please check log file for more details on error. "
							+ System.getProperty("line.separator") +System.getProperty("line.separator"));
				}
			catch (OperatorCreationException e) {
				
				e.printStackTrace();
				logger.error("DigitalSignature  : Exception: "+ e.getMessage());
				throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
				+ System.getProperty("line.separator")+
						"Error details - " +e.getMessage() +System.getProperty("line.separator")+
						"Hence file transfer failed."+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "
						+ System.getProperty("line.separator") +System.getProperty("line.separator"));
			} catch (CMSException e) {
				
				e.printStackTrace();
				logger.error("DigitalSignature  : Exception: "+ e.getMessage());
				throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
				+ System.getProperty("line.separator")+
						"Error details - " +e.getMessage() +System.getProperty("line.separator")+
						"Hence file transfer failed."+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "
						+ System.getProperty("line.separator") +System.getProperty("line.separator"));
			} catch (UnrecoverableKeyException e) {
				
				e.printStackTrace();
				logger.error("DigitalSignature  : Exception: "+ e.getMessage());
				throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
				+ System.getProperty("line.separator")+
						"Error details - " +e.getMessage() +System.getProperty("line.separator")+
						"Hence file transfer failed."+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "
						+ System.getProperty("line.separator") +System.getProperty("line.separator"));
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
				logger.error("DigitalSignature  : Exception: "+ e.getMessage());
				throw new SignPayment_Exception("SQLException occured while fetching the payment file name and location details. " 
				+ System.getProperty("line.separator")+
						"Error details - " +e.getMessage() +System.getProperty("line.separator")+
						"Hence file transfer failed."+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "
						+ System.getProperty("line.separator") +System.getProperty("line.separator"));
				
			} catch (InvalidKeyException e) {				
				e.printStackTrace();
				throw new SignPayment_Exception("DigitalSignature exception occured while fetching the payment file name and location details. " 
						+ System.getProperty("line.separator")+
								"Error details - " +e.getMessage() +System.getProperty("line.separator")+
								"Hence file transfer failed."+ System.getProperty("line.separator") +
								"Please check log file for more details on error. "
								+ System.getProperty("line.separator") +System.getProperty("line.separator"));
				
			} catch (SignatureException e) {
			
				e.printStackTrace();
				throw new SignPayment_Exception("DigitalSignature occured while fetching the payment file name and location details. " 
						+ System.getProperty("line.separator")+
								"Error details - " +e.getMessage() +System.getProperty("line.separator")+
								"Hence file transfer failed."+ System.getProperty("line.separator") +
								"Please check log file for more details on error. "
								+ System.getProperty("line.separator") +System.getProperty("line.separator"));
			}
			


		}



		return signedPaymentStatus;
	}
}