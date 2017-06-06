package sp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import com.jfinal.core.Controller;

public class FileController extends Controller{

	/** 上传目录名 */
	private static final String uploadFolderName = "uploadFiles";

	/** 上传临时文件存储目录 */
	private static final String tempFolderName = "tempFiles";

	/** 上传文件最大为30M */
	private static final Long fileMaxSize = 30000000L;

	/** 允许上传的扩展名 */
	private static final String[] extensionPermit = { "jpg", "txt", "zip" };

	/** 统一的编码格式 */
	private static final String encode = "UTF-8";
	public void index(){
		Map<String,Object> map=new HashMap<String,Object>();
		HttpServletRequest request = getRequest();
		if(ServletFileUpload.isMultipartContent(request)){
			//TODO 取得当前列的id
			//String id=getPara("id");
			String curlProjectPath = getRequest().getServletContext().getRealPath("/");
			String saveDirectoryPath = curlProjectPath + "/" + uploadFolderName;
			String tempDirectoryPath = curlProjectPath + "/" + tempFolderName;
			File saveDirectory = new File(saveDirectoryPath);
			File tempDirectory = new File(tempDirectoryPath);
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			if (!tempDirectory.exists()) {
				tempDirectory.mkdirs();
			}
			factory.setRepository(tempDirectory);
			/*FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
					.getFileCleaningTracker(request.getServletContext());
			factory.setFileCleaningTracker(fileCleaningTracker);*/
			System.out.println(System.getProperty("java.io.tmpdir"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			/*
			 * //设置文件上传进度监听器 FileProcessListener processListener = new
			 * FileProcessListener(request.getSession());
			 * upload.setProgressListener(processListener);
			 */
			// 设置文件上传的大小限制
			upload.setFileSizeMax(fileMaxSize);
			// 设置文件上传的头编码，如果需要正确接收中文文件路径或者文件名
			// 这里需要设置对应的字符编码，为了通用这里设置为UTF-8
			upload.setHeaderEncoding(encode);
			// 解析请求数据包
			List<FileItem> fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 遍历解析完成后的Form数据和上传文件数据
			for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {
				FileItem fileItem = iterator.next();
				String fieldName = fileItem.getFieldName();
				String name = fileItem.getName();
				// 如果为上传文件数据
				if (!fileItem.isFormField()) {
					System.out.println("fieldName[" + fieldName + "] fileName[" + name + "] ");
					if (fileItem.getSize() > 0) {
						String fileExtension = FilenameUtils.getExtension(name);
						if (!ArrayUtils.contains(extensionPermit, fileExtension)) {
						}
						String fileName = FilenameUtils.getName(name);
						try {
							FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File(saveDirectory, fileName));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						map.put("id", fieldName);
						map.put("imgUrl", "/uploadFiles/"+fileName);
						String imagUrl;
						/*String sql="update room set floor2_image="+"'\\\\uploadFiles\\\\"+fileName  +"'  where id="+fieldName;
						int rd=Db.update(sql);*/
					}
				} else { // Form表单数据
					String value;
					try {
						value = fileItem.getString(encode);
						System.out.println("fieldName[" + value + "] fieldValue[" + fieldName + "]");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			System.out.println("请上传文件");
		}
		renderJson(map);
	}
}
