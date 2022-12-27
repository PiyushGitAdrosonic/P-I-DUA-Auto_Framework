package com.as.autot.core.common;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.as.autot.business.BusinessException;


/**
 * This class will instantiate test data object for respective test script. It
 * has option to define different users and browsers before test script
 * execution
 * 
 * @author Anuj Tripathi
 *
 */
public class TestDataInstance {

	private static final Logger LOGGER =  Logger.getLogger(TestDataInstance.class);
	
	public static Properties properties;
	public static String pniVersion;

	private String url;
	private String loginUser;
	private String loginPassword;
	private User selectedUser = User.DEFAULT;

	public enum User {
		DEFAULT, DUA_USER_1, DUA_USER_2, DUA_USER_3,
		PI_USER_READ, PI_USER_BIND , PI_USER_TC , PI_USER_ROOMING, CUSTOM;
	}

	public enum Browser {
		CHROME, EDGE, FIREFOX, CHR_GRID
	}

	enum Environment {
		DEFAULT, DEV,DWTEST, PREPROD, PITEST, PERFTEST, PRODDEV, PRODTEST, UAT;
	}
	
	public enum EnvironmentKey{
		URL, USER, PASSWORD, API_BASE_URL, API_KEY, API_KEY_VALUE, API_VERSION
	}

	public TestDataInstance() {
		super();
		this.selectedUser = User.DEFAULT;
		getCredential();
	}

	public TestDataInstance(User selectedUser) throws BusinessException {
		super();
		this.selectedUser = selectedUser;
		getCredential();
	}

	public TestDataInstance(String loginUser, String loginPassword) {
		super();
		this.loginUser = loginUser;
		this.loginPassword = loginPassword;
		this.selectedUser = User.CUSTOM;
	}

	/**
	 * This method will extract user credential on the basis of user selection
	 * [selectedUser]
	 * 
	 */
	private void getCredential() {
		this.url = getDefaultUrl();
		if (selectedUser.name().equals(User.DEFAULT.name())) {
			this.loginUser = getCredential("USER", selectedUser.name());
			this.loginPassword = getCredential("PASSWORD", selectedUser.name());
		} else if (!selectedUser.name().equals(User.CUSTOM.name())) {
			this.loginUser = getCredential("USER", selectedUser.name());
			this.loginPassword = getCredential("PASSWORD", selectedUser.name());
		}
	}

	/**
	 * This method will return application URL for default environment in property
	 * file
	 * 
	 * @return
	 */
	public static String getDefaultUrl() {
		String defaultEnv = properties.getProperty("DEFAULT.ENV");
		return properties.getProperty("ENV." + defaultEnv + ".URL");
	}
	
	
	/**
	 * This method will return application parameters for default environment in property
	 * file
	 * @param key
	 * @return
	 */
	public static String getDefaultEnvValue(String key) {
		String defaultEnv = properties.getProperty("DEFAULT.ENV");
		return properties.getProperty("ENV." + defaultEnv + "."+key);
	}

	/**
	 * This method will override user credential which are stored in property file
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	private String getCredential(String name, String id) {
		String defaultEnv = properties.getProperty("DEFAULT.ENV");
		if (id.equals(User.DEFAULT.name())) {
			id = properties.getProperty("DEFAULT.USER");
		}
		return properties.getProperty("ENV." + defaultEnv + "." + name + "." + id);
	}

	/**
	 * This method will load all properties value in environment file
	 */
	static void loadProperties() {
		if (null == properties) {
			try {
				String absolutePath = Constants.RUNCONFIGPROP;
				FileReader reader = new FileReader(absolutePath);
				properties = new Properties();
				properties.load(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static {
		loadProperties();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
