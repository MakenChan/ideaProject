/**
 * 
 */
package jsonTools;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author CHEN XINGXING
 * json转换类
 */
public class JsonUtil {

	/**
	 * 用于处理json字符串的静态对象
	 */
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 默认构造器
	 */
	private JsonUtil() {
	}

	/**
	 * 
	 * 对Object的对象进行编码
	 * @param object
	 *            编码对象
	 * @return 一个编码后的字符串
	 */
	public static String convertToXml(Object object) {
		return convertToXml(object, "UTF-8");
	}

	/**
	 * 
	 * 对object的对象转化成XML字符串
	 * @param object
	 *            转化对象
	 * @param encoding
	 *            编码方式
	 * @return 一个对象格式的XML字符串
	 */
	public static String convertToXml(Object object, String encoding) {
		String result = "";
		try {
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter writer = new StringWriter();
			marshaller.marshal(object, writer);
			result = writer.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * xml字符串转换成JavaBean实体
	 * @param xml
	 *            XML字符串
	 * @param clazz
	 *            转换的泛型
	 * @return 一个转化的实体对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> clazz) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 
	 * 对象转换为json字符串
	 * @param object
	 *            转化的实体对象
	 * @return 装换后的json字符串
	 */
	public static String objectToJson(Object object) {
		Writer writer = null;
		String json = "";
		try {
			writer = new StringWriter();
			objectMapper.writeValue(writer, object);
			json = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	/**
	 * 
	 * json字符转换成对象
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            实体对象的class
	 * @return 返回转换后的实体对象
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz) {
		T object = null;
		try {
			object = objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

}
