package lihu.zlm.util;

import java.io.File;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {

	private static String[] allowedExts = new String[] { ".jpeg", ".jpg", ".png", ".JPEG", ".JPG", ".PNG" };

	private String basePath = "/opt/tomcat/webapps/ROOT/imgbase/coverbg/";
	private String baseUrl = "http://www.zhuanlemei.com/imgbase/coverbg/";

	/*
	private String basePath = "D:/wuxc/next/Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/zhuanlemei/imgbase/coverbg/";
	private String baseUrl = "http://127.0.0.1/imgbase/coverbg/";
	 */

	public String uploadImage(MultipartFile file) throws Exception {
		if (file == null)
			return null;

		if (file.isEmpty())
			return null;

		String fileNameOrigin = file.getOriginalFilename();
		String fileSuffix = fileNameOrigin.substring(fileNameOrigin.lastIndexOf("."));
		if (StringUtils.isEmpty(fileSuffix)) {
			throw new Exception("文件后缀不合法");
		}

		if (ArrayUtils.indexOf(allowedExts, fileSuffix) < 0) {
			throw new Exception("文件不支持");
		}

		// 复制文件
		UploadInfo uploadInfo = getUploadInfo(fileNameOrigin);
		File targetFile = new File(uploadInfo.getFilePath());
		File targetParent = targetFile.getParentFile();
		if (!targetParent.exists()) {
			targetParent.mkdirs();
		}
		file.transferTo(targetFile);

		return uploadInfo.getFileUrl();
	}

	public UploadInfo getUploadInfo(String fileName) {
		return getUploadInfo(fileName, true);
	}

	public UploadInfo getUploadInfo(String fileName, boolean rename) {
		String fileNameNew = fileName;
		if (rename) {
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
			fileNameNew = UniqID.getInstance().getUniqIDHash() + fileSuffix;
		}

		String thumbnailFileName = "thumb_" + fileNameNew;
		String filePathNew = basePath + fileNameNew;
		String fileUrlNew = baseUrl + fileNameNew;
		String thumbnailFilePath = basePath + thumbnailFileName;
		String thumbnailFileUrl = baseUrl + thumbnailFileName;

		UploadInfo uploadInfo = new UploadInfo();
		uploadInfo.setFilePath(filePathNew);
		uploadInfo.setFileUrl(fileUrlNew);
		uploadInfo.setFileName(fileNameNew);
		uploadInfo.setThumbnailFileName(thumbnailFileName);
		uploadInfo.setThumbnailFilePath(thumbnailFilePath);
		uploadInfo.setThumbnailFileUrl(thumbnailFileUrl);
		return uploadInfo;
	}

	public String getFilePath(String fileUrl) {
		int i = fileUrl.indexOf(baseUrl);
		if (i < 0)
			return null;

		String path = fileUrl.substring(i + baseUrl.length());
		return basePath + path;
	}

	public void delete(String imgUrl) {
		if (!StringUtils.isNotBlank(imgUrl)) {
			return;
		}
		String fileName = imgUrl.substring(baseUrl.length(), imgUrl.length());
		File imgFile = new File(basePath + fileName);
		if (imgFile.exists()) {
			imgFile.delete();
		}
	}

	public class UploadInfo {

		private String filePath;

		private String fileUrl;

		private String fileName;

		private String thumbnailFilePath;

		private String thumbnailFileUrl;

		private String thumbnailFileName;

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getThumbnailFilePath() {
			return thumbnailFilePath;
		}

		public void setThumbnailFilePath(String thumbnailFilePath) {
			this.thumbnailFilePath = thumbnailFilePath;
		}

		public String getThumbnailFileUrl() {
			return thumbnailFileUrl;
		}

		public void setThumbnailFileUrl(String thumbnailFileUrl) {
			this.thumbnailFileUrl = thumbnailFileUrl;
		}

		public String getThumbnailFileName() {
			return thumbnailFileName;
		}

		public void setThumbnailFileName(String thumbnailFileName) {
			this.thumbnailFileName = thumbnailFileName;
		}

	}
}