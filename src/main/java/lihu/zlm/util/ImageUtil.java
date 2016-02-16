package lihu.zlm.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2015年8月6日 下午1:40:27
 * 
 */
@SuppressWarnings("restriction")
public class ImageUtil {

	public static Map<String, Object> getImageSize(InputStream fis) throws IOException {
		BufferedImage image = ImageIO.read(fis);
		Map<String, Object> size = new HashMap<String, Object>();
		size.put("width", image.getWidth());
		size.put("height", image.getHeight());
		return size;
	}

	public static String getSuffix(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (index < 0) {
			return null;
		}

		return fileName.substring(index);
	}

	public static void crop(String src, String dest, int scaledWith, int scaledHeight, int x, int y, int w, int h)
			throws IOException {
		BufferedImage image = ImageIO.read(new File(src));
		int widthOrigin = image.getWidth();
		int heightOrigin = image.getHeight();

		if (x < 0 || y < 0 || w <= 0 || h <= 0) {
			throw new IllegalArgumentException("裁剪参数不正确");
		}

		// 需要缩放
		if (scaledWith > 0 && scaledHeight > 0 && scaledWith != heightOrigin && scaledHeight != widthOrigin) {
			BufferedImage imageScale = new BufferedImage(scaledWith, scaledHeight, BufferedImage.TYPE_INT_RGB);
			imageScale.getGraphics().drawImage(
					image.getScaledInstance(scaledWith, scaledHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			image = imageScale;
		} else {
			scaledWith = widthOrigin;
			scaledHeight = heightOrigin;
		}

		if (x + w > scaledWith || y + h > scaledHeight) {
			throw new IllegalArgumentException("裁剪参数不正确");
		}

		// 开始裁剪
		BufferedImage imageCrop = image.getSubimage(x, y, w, h);

		FileOutputStream newimage = null;
		try {
			newimage = new FileOutputStream(dest);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(imageCrop);
			jep.setQuality(1.0f, true);
			encoder.encode(imageCrop, jep);
		} finally {
			if (newimage != null) {
				newimage.close();
			}
		}
	}

	/**
	 * 截取一个图像的中央区域
	 * 
	 * @param image
	 *            图像File
	 * @param cuted
	 *            截取后的File
	 * 
	 * @throws Exception
	 */
	public static void cutImage(File image, File cuted) throws Exception {
		// 判断参数是否合法
		if (null == image) {
			new Exception("哎呀，截图出错！！！");
		}
		InputStream inputStream = new FileInputStream(image);
		// 用ImageIO读取字节流
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		BufferedImage distin = null;

		// 返回源图片的宽度。
		int srcWidth = bufferedImage.getWidth();

		// 返回源图片的高度。
		int srcHeight = bufferedImage.getHeight();
		int x = 0, y = 0;
		int w = 0, h = 0;
		if (srcWidth > srcHeight) {
			w = srcHeight;
			h = srcHeight;
		} else if (srcWidth < srcHeight) {
			w = srcWidth;
			h = srcWidth;
		} else {
			w = srcWidth;
			h = srcWidth;
		}

		// 使截图区域居中
		x = srcWidth / 2 - w / 2;
		y = srcHeight / 2 - h / 2;
		srcWidth = srcWidth / 2 + w / 2;
		srcHeight = srcHeight / 2 + h / 2;
		// 生成图片
		distin = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = distin.getGraphics();
		g.drawImage(bufferedImage, 0, 0, w, h, x, y, srcWidth, srcHeight, null);
		ImageIO.write(distin, "jpg", cuted);
	}

	/**
	 * 验证图片类型
	 * 
	 * @param imageFile
	 * @throws IOException
	 */
	public static String checkImageType(File imageFile) throws IOException {
		// get image format in a file
		File file = imageFile;

		// create an image input stream from the specified file
		ImageInputStream iis = ImageIO.createImageInputStream(file);

		// get all currently registered readers that recognize the image format
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

		if (!iter.hasNext()) {
			throw new RuntimeException("No readers found!");
		}

		// get the first reader
		ImageReader reader = iter.next();

		String formatName = reader.getFormatName();

		// close stream
		iis.close();

		return formatName;
	}

	/**
	 * 保存图片
	 * 
	 * @param imgFileName
	 *            上传照片文件名
	 * @param filedata
	 *            文件数据
	 */
	public static void saveFile(String saveFilePath, String imgFileName, MultipartFile filedata) {
		// 构建文件目录
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(saveFilePath + imgFileName);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
