/**
 * 
 */
package dateTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CHEN XINGXING
 * 2017/8/17  时间转换类
 */
public class DataUtil {

	/**
	 * 
	 * 2017/8/17 将当前时间格式化,格式化的格式yyyy-MM-dd
	 * @return 一个短格式化的时间字符串
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String time = formatter.format(date);
		return time;
	}

	/**
	 * 
	 * 将当前时间格式化,格式化的格式yyyy-MM-dd HH:mm:ss
	 * @return 一个长格式化的时间字符串
	 */
	public static String getTimeDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formatter.format(date);
		return time;
	}

	/**
	 *  将输入时间格式化,格式化的格式yyyy-MM-dd HH:mm:ss
	 *
	 * @return 一个长格式的时间字符串
	 */
	public static String timeToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formatter.format(date);
		return time;
	}

	/**
	 * 
	 *  将输入时间格式化,格式化的格式yyyy-MM-dd
	 * @param date
	 * @return 一个短格式的时间字符串
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String time = formatter.format(date);
		return time;
	}

	/**
	 * 
	 * TODO:  把标准时区转化成当前时区
	 * @param date
	 * @return 当前时区的时间
	 */
	public static Date dateOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, +8);
		Date date1 = calendar.getTime();
		return date1;
	}
}
