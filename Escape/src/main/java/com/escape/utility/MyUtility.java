package com.escape.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.escape.controller.SuperController;
//이 클래스는 유틸리티 용도로 사용됩니다.
public class MyUtility {
	public static void deleteOldImageFile(String realPath, MultipartRequest mr) {
		// 상품 수정시 과거에 업로드한 이미지들을 웹 서버에서 삭제하도록 합니다. 
		String[] deleteImage = 
			{
				mr.getParameter("deleteImage01"), 
				mr.getParameter("deleteImage02"), 
				mr.getParameter("deleteImage03")
			}; 
		
		for (int i = 0; i < deleteImage.length; i++) {
			if(deleteImage[i] != null) {
				String deleteFile = realPath + "/" + deleteImage[i] ;
				File delFile = new File(deleteFile) ;
				if(delFile.delete()) {
					System.out.println(deleteFile + " file delete success"); 
				}
			}
		}
	}
	
	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String realPath) {
		// 이미지 업로드를 위한 멀티 파트 객체 생성 후 반환합니다.
		MultipartRequest mr = null; // 파일 업로드 객체
		int maxPostSize = 10 * 1024 *1024 ; // 업로드 최대 용량
		final String ENCODING = "UTF-8"; // 인코딩 문자열
		
		try {		
			mr = new MultipartRequest(
					request, 
					realPath,
					maxPostSize,
					ENCODING,
					new DefaultFileRenamePolicy());
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mr;
	} 
	
	
	
	public static Map<String, String> getSettingMap(String workingFile) {
		// workingFile을 이용하여 자바의 맵 형식으로 반환해 줍니다.
		Map<String, String> map = new HashMap<String, String>() ;
		Properties prop = null ;
		
		prop = getPropertiesData(workingFile) ;
		
		Enumeration<Object> keys = prop.keys() ;
		while(keys.hasMoreElements()){
			String key = keys.nextElement().toString() ;
			String value = prop.getProperty(key) ;
			map.put(key, value) ;
		}
		
		return map;
	}

	// 스트림을 이용하여 프로퍼티 목록을 반환해 줍니다.
	private static Properties getPropertiesData(String workingFile) {
		FileInputStream fis = null ;
		Properties properties = null;
		
		try {
			fis = new FileInputStream(workingFile) ;
			
			properties = new Properties();
			properties.load(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(fis != null) {fis.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		System.out.println("properties.toString()");
		System.out.println(properties.toString());
		
		return properties;
	}


	public static Map<String, SuperController> getTodolistMap(String workingFile) {
		// 쇼핑몰에서 사용할 투두 리스트를 맵으로 만들어 반환합니다.
		Map<String, SuperController> map = new HashMap<String, SuperController>() ;
		Properties prop = null ;
		
		prop = getPropertiesData(workingFile) ;
		
		Enumeration<Object> enunTrans = prop.keys() ;
		
		while(enunTrans.hasMoreElements()) {
			String command = enunTrans.nextElement().toString();
			String className = prop.getProperty(command) ;
			//System.out.println(command + "/" + className); 
			
			try {
				Class<?> handleClass = Class.forName(className);
				
				SuperController instance = (SuperController)handleClass.newInstance() ;
				
				map.put(command, instance) ;
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return map;
	}


}

