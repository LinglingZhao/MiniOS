import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	public static byte[] readFile(String pathName) {
		File file = new File(pathName);
		InputStream is = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		byte[] data = null;
		try {
			// 获取输入流
			is = new FileInputStream(file);
			// 将输入流包装成，带缓冲的输入流
			bis = new BufferedInputStream(is);
			baos = new ByteArrayOutputStream();
			// 缓冲数组
			byte[] buffer = new byte[1024 * 1024];// 大小为 1M
			// 读入数据
			while (bis.available() > 0) {
				int len = bis.read(buffer);
				baos.write(buffer, 0, len);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关闭资源
			try {
				if (baos != null)
					baos.close();
				if (bis != null)
					bis.close();
				if (is != null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	
	public static void writeFile(String pathName, byte[] data) {
		File file = new File(pathName);
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			// 获取输出字节流
			os = new FileOutputStream(file);
			// 将字节流包装成，带缓冲区的字节流
			bos = new BufferedOutputStream(os);
			// 写入数据
			bos.write(data);
			// 刷新缓冲
			bos.flush();

			// 关闭资源
			if (bos != null)
				bos.close();
			if (os != null)
				os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
