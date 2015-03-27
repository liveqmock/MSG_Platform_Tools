package com.neusoft.util.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import com.neusoft.core.EapDataContext;
import com.neusoft.web.model.Project;
import com.neusoft.web.model.ProjectFile;

public class FileUtil {
	private static final File rootFile = new File(EapDataContext.REAL_PATH , "source");
	
	private static String getWebInfo(){
		return new StringBuffer().append("web").append(File.separator).append("WEB-INF").toString() ;
	}
	public static File getWebFilePath(String root){
		return new File(new File(rootFile , root), new StringBuffer().append(getWebInfo()).append(File.separator).toString()) ;
	}
	public static File getClassFilePath(String root){
		return new File(new File(rootFile , root), new StringBuffer().append(getWebInfo()).append(File.separator).append("classes").toString()) ;
	}
	public static File getSourceFilePath(String root){
		return new File(new File(rootFile , root) , "src") ;
	}
	public static File getApplicationContextFilePath(String root , String name){
		return new File(FileUtil.getClassFilePath(root) , "applicationContext.xml") ;
	}
	public static File getWebLib(){
		return new File(EapDataContext.REAL_PATH , "lib") ;
	}
	
	@SuppressWarnings("deprecation")
	public static URL[] getFileURLS() throws MalformedURLException{
		File[] jarFile = getWebLib().listFiles();
		List<URL> jarFiles = new ArrayList<URL>();
		for(File jar : jarFile){
			jarFiles.add(jar.toURL());
		}
		return jarFiles.toArray(new URL[jarFiles.size()]);
	}
	
	/**
	 * 写入文件
	 * @param javacode
	 * @param path
	 * @throws IOException
	 */
	public static File writeFile(String javacode , File javaFilePath , String name) throws IOException{
		if(!javaFilePath.exists()){
			javaFilePath.mkdirs();
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(javaFilePath = new File(javaFilePath , name));
			osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(javacode) ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(osw!=null){
				osw.close();
			}
			if(fos!=null){
				fos.close();
			}
		}
        return javaFilePath;
	}
	/**
	 * 
	 * @param prj
	 * @return
	 */
	public static ProjectFile listFiles(Project prj){
		File file = new File(rootFile , prj.getId());
		if(!file.exists()){
			file.mkdirs();
			initProjectFile(file);
		}
		int sortno = 0 ;
		ProjectFile project = new ProjectFile(prj.getName(),"dir",file.lastModified() , null , file , 0 , sortno++) ;
		project = listFile(project , project); 
		project.setSortno(0) ;
		return project ;
	}
	
	/**
	 * 
	 * @param prj
	 * @return
	 */
	public static ProjectFile listFiles(Project prj , String filePath){
		File rootFile = new File(filePath);
		ProjectFile root = new ProjectFile(prj.getName(),"dir",rootFile.lastModified() , null , rootFile , 0 , 0) ;
		File[] listFile = rootFile.listFiles() ; 
		if(listFile!=null && listFile.length>0){
			for(File lf : listFile){
				ProjectFile project = new ProjectFile(lf.getName(),lf.isDirectory()?"dir":"file",lf.lastModified() , root , lf , root.getLevel()+1 , 1) ;
				root.getFileList().add(project);
			}
		}
		return root ;
	}
	/**
	 * 
	 * @param prj
	 * @return
	 */
	public static ProjectFile viewFile(Project prj , String filePath){
		File rootFile = new File(filePath);
		return new ProjectFile(prj.getName(),"dir",rootFile.lastModified() , null , rootFile , 0 , 0) ;
	}
	
	public static String getContextType(String fileName){
 		String contentType = "" ;
		if(fileName.toLowerCase().endsWith(".css")){
			contentType = "text/css" ;
		}else if(fileName.toLowerCase().endsWith(".js")){
			contentType = "text/javascript" ;
		}else if(fileName.toLowerCase().endsWith(".png")){
			contentType = "image/png" ;
		}else if(fileName.toLowerCase().endsWith(".html") || fileName.toLowerCase().endsWith(".htm") || fileName.toLowerCase().endsWith(".xml")){
			contentType = "html/text" ;
		}else{
			contentType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileName);
		}
		return contentType ;
	}
	/**
	 * 初始化目录结构
	 * @param file
	 */
	public static void initProjectFile(File file){
		File srcFile = new File(file , "src") ;
		if(!srcFile.exists()){
			srcFile.mkdirs();
		}
		File webFile = new File(file , "web") ;
		if(!webFile.exists()){
			webFile.mkdirs();
		}
		File webInfoFile = new File(webFile , "WEB-INF") ;
		if(!webInfoFile.exists()){
			webInfoFile.mkdirs();
		}
	}
	/**
	 * 递归遍历文件目录
	 * @param prj
	 * @return
	 */
	public static ProjectFile listFile(ProjectFile prj , ProjectFile root){
		if(prj!=null && prj.getFile()!=null){
			File[] subFile = prj.getFile().listFiles();
			for(File file : subFile){
				root.setSortno(root.getSortno()+1);
				int sortno = root.getSortno() ;
				if(file.isDirectory()){
					ProjectFile project = new ProjectFile(file.getName(),"dir",file.lastModified() , prj , file , prj.getLevel()+1 , sortno) ;
					prj.getFileList().add(listFile(project , root));
				}else{
					ProjectFile project = new ProjectFile(file.getName(),"file",file.lastModified() , prj , file , prj.getLevel()+1 , sortno) ;
					prj.getFileList().add(project);
				}
			}
		}
		return prj ;
	}
}
