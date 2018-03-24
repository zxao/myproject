package com.itheima.fastdfs;

import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

/**
 * 测试fastDSF
 * @author ZXAO
 *
 */
public class FastDFSTest {
	
	@Test
	public void testFastDFS() {
		try {
			//1.加载配置文件，配置文件中的内容为tracker服务地址
			ClientGlobal.init("D:/develop/eclipse-mars-2/workspace_taotao/taotao_manager_web/src/test/resources/resource/fast_dfs.conf");
			//2.创建一个trackerClient对象
			TrackerClient trackerClient = new TrackerClient();
			//3.创建连接，获得TranckerServer
			TrackerServer tranckerServer = trackerClient.getConnection();
			StorageServer storageService = null;
			//4.创建storageClient对象
			StorageClient storageClient = new StorageClient(tranckerServer,storageService );
			//使用storageClient对象上传文件
			String[] strings = storageClient.upload_file("f:/zxao123.jpg", "jpg", null);
			//返回数组（包含组名和图片路径）
			for (String string : strings) {
				System.out.println(string);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
