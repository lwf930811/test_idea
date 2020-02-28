package com.dj.ssm.commons.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public class UploadAndDown {

	public static String upload(MultipartFile file) {

		try {
			// �ж�fileName�Ƿ�Ϊ��
			if(!file.isEmpty()) {
				String path = "H:/upload/";
				File filepath = new File(path);
				if (!filepath.exists()) {
					filepath.mkdirs();
				}
				String fileName=UUID.randomUUID().toString().replace("-", "");
				String hz=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String newFileName=fileName+hz;
				file.transferTo(new File(path+newFileName));
				return newFileName;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}

	/**
	 * �ļ����ش�����
	 */
	public static void downlaod(HttpServletResponse response, String img) {

		try {

			// ��ʽ�淶
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(img, "UTF-8"));

			// ��ȡ�����ļ� Ҳ���Ǵ�(D:/upload/)��ַ����
			InputStream input = new FileInputStream("H:/upload/" + img);

			// ���response�����
			OutputStream out = response.getOutputStream();

			int len = 0;
			byte[] b = new byte[4096];

			while ((len = input.read(b)) > 0) {

				out.write(b, 0, len);

			}

			out.close();

			input.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
