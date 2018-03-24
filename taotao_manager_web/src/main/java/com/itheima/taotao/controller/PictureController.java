package com.itheima.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itheima.taotao.utils.FastDFSClient;

/**
 * 图片上传
 * @author ZXAO
 *
 */
@Controller
public class PictureController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map fileUpload(MultipartFile uploadFile){
		
		try {
			//获取文件名
			String fileName = uploadFile.getOriginalFilename();
			String extName = fileName.substring(fileName.lastIndexOf(".")+1);
			//2.创建fastDFS客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/fast_dfs.conf");
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			String url = IMAGE_SERVER_URL + path;
			//返回map
			Map map = new HashMap();
			map.put("error", 0);
			map.put("url", url);
			return map;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map map = new HashMap();
			map.put("error", 1);
			map.put("message", "图片上传失败");
			return map;
		}
		
	}
}
