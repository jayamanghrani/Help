/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.webreports.util;

import com.tcs.webreports.config.utils.UtilProperties;

/**
 * The Class OAMOIDUtil.
 *
 * @author 738566
 */
public class OAMOIDUtil {

	/** The Constant LDAP_HOSTNAME. */
	public static final String LDAP_HOSTNAME = UtilProperties.getLdapHostName();

	/** The Constant LDAP_PORT. */
	public static final String LDAP_PORT = UtilProperties.getLdapPort();

	/** The Constant LDAP_USERNAME. */
	public static final String LDAP_USERNAME = UtilProperties.getLdapUserName();

	/** The Constant LDAP_PASSWORD. */
	public static final String LDAP_PASSWORD = UtilProperties.getLdapPassword();
	
	/** The Constant OAMOID_URL. */
	public static final String OAMOID_URL = UtilProperties.getOamoidUrl();
	
	/** The Constant OAMOID_URL. */
	public static final String JPS_LOCATION = UtilProperties.getJpsLocation();
	
}
