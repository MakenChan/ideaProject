/**
 * 
 */
package fileTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CHEN XINGXING
 * :字符串工具类
 */
public class FileUtil {
	/**
	 * 
	 * 将字符串写入文件中
	 * @param file
	 *            文件如：content.txt等
	 * @param content
	 *            要写入的内容
	 * @param line
	 *            是否要分行,true为每次写入文件都分行
	 * @param append
	 *            是否追加文件末尾,true为追加在文件末尾,false为每次都删除原来的文件重新写入文件
	 * @throws IOException
	 */
	public static void writeStrToFile(File file, String content, boolean line,
			boolean append) throws IOException {
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录(推荐使用这个方法)
			file.getParentFile().mkdirs();
		}
		FileOutputStream fop = new FileOutputStream(file, append);
		if (line == true) {
			content = content + "\t\n";
		}
		fop.write(content.getBytes());
		fop.close();
	}

	/**
	 * 
	 * 读取文件
	 * @param file
	 *            输入文件如：content.txt
	 * @return 返回一个字符串
	 * @throws IOException
	 */
	public static String fileToString(File file) throws IOException {
		InputStream inp = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
		String line; // 用来保存每行读取的内容
		StringBuffer buffer = new StringBuffer();
		while ((line = reader.readLine()) != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
		}
		reader.close();
		inp.close();
		return buffer.toString();

	}

	/**
	 * 
	 * list.get(0) 文件路径 list.get(1) 对应的文件名
	 * @param directory
	 * @return 获取某个目录下(不包含子目录)所有的文件的全路径和文件名的集合
	 */
	public static List<List<String>> getAllFile(String directory) {
		File file = new File(directory);
		File[] files = file.listFiles();
		List<List<String>> dir = new ArrayList<List<String>>();
		List<String> allFilePath = new ArrayList<String>();
		List<String> allFileName = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			// 只输出目录
			if (files[i].isDirectory()) {
				// allFilePath.add(files[i].toString());
				// allFileName.add(files[i].getName());
			}
			allFilePath.add(files[i].toString());
			allFileName.add(files[i].getName());
		}
		dir.add(allFilePath);
		dir.add(allFileName);
		return dir;

	}

}
