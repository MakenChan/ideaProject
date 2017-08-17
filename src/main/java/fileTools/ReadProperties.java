/**
 * 
 */
package fileTools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author CHEN XINGXING
 * @Description:用于读取配置文件
 */
public class ReadProperties {
	/**
	 * 
	 * 获得Properties对象
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String path) throws IOException {
		Properties prop = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(path));
		prop.load(in);
		return prop;
	}

	/**
	 * 
	 * 获得全部的properties属性(枚举类型)
	 * @param path
	 *            文件路径如：src/main/resources/db.properties
	 * @throws IOException
	 */
	public static Map<String, String> getAllProperties(String path)
			throws IOException {
		Properties prop = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(path));
		prop.load(in);
		// 得到配置文件的名字
		Enumeration<?> enu = prop.propertyNames();
		Map<String, String> propmap = new HashMap<String, String>();
		while (enu.hasMoreElements()) {
			String propKey = (String) enu.nextElement();
			String propValue = prop.getProperty(propKey);
			propmap.put(propKey, propValue);
			propmap.put(propValue, propValue);
		}
		return propmap;

	}
}
