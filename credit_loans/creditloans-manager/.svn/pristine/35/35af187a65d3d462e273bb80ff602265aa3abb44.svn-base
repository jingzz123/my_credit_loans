package cn.creditloans.manager.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
	private static final Log loger = LogFactory.getLog(UploadFileController.class);
	/**
	 * 文件上传
	 * @param templateName
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
	public @ResponseBody String fileUpload(@RequestParam(value = "templateName") MultipartFile templateName, HttpServletRequest request){
		 String realPath = request.getSession().getServletContext().getRealPath("/template/");
		 String filePath = null;
		 String fileName = templateName.getOriginalFilename();
		 String newFileName = null;
		 if(fileName != null && "" != fileName){
			 int index = fileName.lastIndexOf("."); 
			 newFileName = System.currentTimeMillis() + "." + fileName.substring(index+1, fileName.length());
			 try {
					FileUtils.copyInputStreamToFile(templateName.getInputStream(), new File(realPath, newFileName));
					filePath = realPath + System.getProperty("file.separator") + newFileName;
					filePath = filePath.replaceAll("\\\\", "/");
		         } catch (IOException e) {
					loger.info("<<<<<<<<<<<<<<<<<<<<<<<<文件上传失败");
					e.printStackTrace();
				} 
		 }
		 return filePath;
	}

	/*@RequestMapping("/uploadFile")
	public void uploadPic(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException{
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
		MultipartFile mf = mr.getFile(fileName);
		String tFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for(int i = 0; i < 3; i++){
			tFileName = tFileName + random.nextInt(10);
		}
		String oriFileName = mf.getOriginalFilename();
		//后缀名
		String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		//相对路径
		String relativePath = "/upload/"+tFileName+suffix;
		StringBuffer buf = new StringBuffer();
		try{
			FileOutputStream os = new FileOutputStream(relativePath); 
			FileInputStream in = (FileInputStream) mf.getInputStream();  
			int b = 0;  
            while((b=in.read()) != -1){  
                os.write(b);  
            }  
            os.flush();  
            os.close();  
            in.close();
		} catch (Exception e) {
			buf.append("<response>");
			buf.append("<tips>上传失败").append("</tips>\r\n");
			buf.append("</response>");
			e.printStackTrace();
		} finally {
			response.setContentType("text/plain;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().println(buf);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}*/
}
