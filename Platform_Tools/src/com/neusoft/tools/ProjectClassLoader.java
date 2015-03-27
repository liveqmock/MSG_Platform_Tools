package com.neusoft.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;


public class ProjectClassLoader extends URLClassLoader {

	public ProjectClassLoader(URL[] urls, ClassLoader parent) {
		super(urls , parent);
	}
	
	/**
	 * 
	 * @param filePath
	 * @throws ClassNotFoundException
	 */
	public void init(File filePath) throws ClassNotFoundException{
		listClass(filePath) ;
	}
	/**
	 * 
	 * @param filePath
	 * @throws ClassNotFoundException
	 */
	private void listClass(File filePath) throws ClassNotFoundException{
		if(filePath.exists()){
			File[] listFile = filePath.listFiles();
			for(File file : listFile){
				if(file.isDirectory()){
					listClass(file) ;
				}else{
					if(file.getName().toLowerCase().endsWith(".class")){
						findClass(file);
					}
				}
			}
		}
	}
	/**
	 * 根据类名字符串从指定的目录查找类，并返回类对象
	 */
	@SuppressWarnings("deprecation")
	protected Class<?> findClass(File file) throws ClassNotFoundException {
		byte[] classData = null;
		try {
			classData = loadClassData(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.defineClass(classData, 0, classData.length);
	}

	/**
	 * 根据类名字符串加载类 byte 数据流
	 * 
	 * @param name
	 *            类名字符串 例如： com.cmw.entity.SysEntity
	 * @return 返回类文件 byte 流数据
	 * @throws IOException
	 */
	private byte[] loadClassData(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] arrData = new byte[(int) file.length()];
		fis.read(arrData);
		return arrData;
	}
}