package com.neusoft.tools.spring;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.neusoft.core.EapDataContext;
import com.neusoft.tools.ProjectClassLoader;
import com.neusoft.util.PlatformMsgTools;
import com.neusoft.util.tools.FileUtil;
import com.neusoft.web.model.DataTableType;
import com.neusoft.web.model.DataTableView;
import com.neusoft.web.model.Database;
import com.neusoft.web.model.PageDataView;
import com.neusoft.web.model.Project;

import freemarker.template.TemplateException;

public class ApplicationContextUtils {
	private static Map<String , Object> contextMap = new HashMap<String , Object>();
	@SuppressWarnings("unchecked")
	public void buildProject(String orgi , String root , String pkg , Project project) throws IOException, TemplateException{
		List<DataTableView> tableViewList = EapDataContext.getService().findAllByCriteria(DetachedCriteria.forClass(DataTableView.class).add(Restrictions.eq("orgi", orgi)));
		List<DataTableType> typeList = EapDataContext.getService().findAllByCriteria(DetachedCriteria.forClass(DataTableType.class).add(Restrictions.eq("orgi", orgi))) ;
		buildBean(orgi , root , pkg ,tableViewList , typeList);
		buildWeb(orgi , root , pkg);
		buildSpringContext(orgi , root , pkg , project ,tableViewList , typeList);
		buildSpringServlet(orgi , root , pkg);
	}
	public void buildSpringServlet(String orgi , String root , String pkg) throws IOException, TemplateException{
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pkg", pkg) ;
		values.put("subpkg", EapDataContext.getWebPackageName()) ;
		FileUtil.writeFile(PlatformMsgTools.getTemplet(PlatformMsgTools.getSearchResultTemplet(EapDataContext.TemplateNameEnum.springServlet.toString()), values) , FileUtil.getWebFilePath(root) , "spring-servlet.xml") ;
	}
	public void buildSpringContext(String orgi , String root , String pkg , Project project , List<DataTableView> tableViewList , List<DataTableType> typeList) throws IOException, TemplateException{
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pkg", pkg) ;
		values.put("subpkg", EapDataContext.getWebPackageName()) ;
		values.put("db", EapDataContext.getService().getIObjectByPK(Database.class, project.getDbid())) ;
		values.put("tablelist", tableViewList) ;
		values.put("typelist", typeList) ;
		FileUtil.writeFile(PlatformMsgTools.getTemplet(PlatformMsgTools.getSearchResultTemplet(EapDataContext.TemplateNameEnum.applicationContext.toString()), values) , FileUtil.getClassFilePath(root) , "applicationContext.xml") ;
		FileUtil.writeFile(PlatformMsgTools.getTemplet(PlatformMsgTools.getSearchResultTemplet(EapDataContext.TemplateNameEnum.applicationContext.toString()), values) , FileUtil.getSourceFilePath(root) , "applicationContext.xml") ;
	}
	public void buildWeb(String orgi , String root , String pkg) throws IOException, TemplateException{
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("pkg", pkg) ;
		values.put("subpkg", EapDataContext.getWebPackageName()) ;
		FileUtil.writeFile(PlatformMsgTools.getTemplet(PlatformMsgTools.getSearchResultTemplet(EapDataContext.TemplateNameEnum.web.toString()), values) , FileUtil.getWebFilePath(root) , "web.xml") ;
	}
	@SuppressWarnings("unchecked")
	public void buildBean(String orgi , String root , String pkg ,List<DataTableView> tableViewList , List<DataTableType> typeList) throws IOException, TemplateException{
		List<File> javaFileList = new ArrayList<File>();
		
		for(DataTableView tableView : tableViewList){
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("data", tableView) ;
			values.put("pkg", pkg) ;
			values.put("subpkg", EapDataContext.getWebPackageName()) ;
			values.put("tablelist", tableViewList) ;
			values.put("typelist", typeList) ;
			javaFileList.add(writeJava(tableView, root, typeList ,pkg, PlatformMsgTools.getTemplet(PlatformMsgTools.getSearchResultTemplet(EapDataContext.TemplateNameEnum.JavaBean.toString()), values))) ;
		}
		compiler(javaFileList  , root);
	}
	
	public File buildJar(String root){
		return null;
	}
	
	public File writeJava(DataTableView tableView , String root , List<DataTableType> typeList,String pkg,String javacode) throws IOException{
		StringBuffer pkgName = new StringBuffer();
		pkgName.append(pkg).append(".").append(EapDataContext.getWebPackageName().get(EapDataContext.MODEL));
		for(DataTableType type : typeList){
			if(tableView.getTypeid().equals(type.getId()) && type.getCode()!=null){
				pkgName.append(".").append(type.getCode());
				break ;
			}
		}
		for(int i=0 ; i<pkgName.length() ; i++){
			if(pkgName.charAt(i)=='.'){
				pkgName.replace(i , i+1, File.separator) ;
			}
		}
		
		return FileUtil.writeFile(javacode , new File(FileUtil.getSourceFilePath(root) , pkgName.toString()), StringUtils.capitalize(tableView.getCode().toLowerCase())+".java");
	}
	
	public void compiler(List<File> fileList , String root){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);  
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		/** 
		* 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。 -sourcepath选项就是定义java源文件的查找目录， -classpath选项就是定义class文件的查找目录，-d就是编译文件的输出目录。 
		*/  
		if(!FileUtil.getClassFilePath(root).exists()){
			FileUtil.getClassFilePath(root).mkdirs();
		}
		Iterable<String> options = Arrays.asList("-d", FileUtil.getClassFilePath(root).getAbsolutePath(),"-classpath",EapDataContext.getJars(), "-encoding", "UTF-8" ,"-sourcepath", FileUtil.getSourceFilePath(root).getAbsolutePath());
		CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics, options, null, fileManager.getJavaFileObjectsFromFiles(fileList));  
		// 运行编译任务  
        boolean compilerResult = compilationTask.call();  
        if (!compilerResult) { 
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {  
                //   System.out.format("%s[line %d column %d]-->%s%n", diagnostic.getKind(), diagnostic.getLineNumber(),
                // diagnostic.getColumnNumber(),  
                // diagnostic.getMessage(null));  
                System.out.println(diagnostic.getMessage(null));  
            }  
        }  
	}
	/**
	 * 活动类路径
	 * @param pdv
	 * @param typeList
	 * @param pkg
	 * @return
	 */
	public static String getClassName(PageDataView pdv , List<DataTableType> typeList , String pkg){
		StringBuffer strb = new StringBuffer();
		DataTableType dataTableType = null ;
		for(DataTableType dataType : typeList){
			if(pdv.getDataview().getTypeid().equals(dataType.getId())){
				dataTableType = dataType ;
				break ;
			}
		}
		if(dataTableType!=null){
			strb.append(pkg).append(".").append(EapDataContext.getWebPackageName().get(EapDataContext.MODEL)).append(".");
			if(dataTableType.getCode()!=null){
				strb.append(dataTableType.getCode()).append(".").append(StringUtils.capitalize(pdv.getDataview().getCode())) ;
			}
		}
		return strb.length()>0 ? strb.toString() : null ; 
	}
	private static ProjectClassLoader classLoader = null ;
	
	/**
	 * 自定义ClassLoader
	 * @param root
	 * @return
	 * @throws MalformedURLException
	 */
	public synchronized static ProjectClassLoader getApplicationClassLoader() throws MalformedURLException{
		if(classLoader == null){
			classLoader = new ProjectClassLoader(FileUtil.getFileURLS(), Thread.currentThread().getContextClassLoader());
		}
		return classLoader ;
	}
	/**
	 * 
	 * @param dbid
	 * @param root
	 * @return
	 * @throws MalformedURLException 
	 */
	public synchronized static ApplicationContext getApplicationContext(String dbid , String root) throws MalformedURLException{
		ApplicationContext context = null ;
		if((context = (ApplicationContext) contextMap.get(dbid))==null){
			try {
				ApplicationContextUtils.getApplicationClassLoader().init(FileUtil.getClassFilePath(root)) ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Thread.currentThread().setContextClassLoader(ApplicationContextUtils.getApplicationClassLoader());
			contextMap.put(dbid, context = new FileSystemXmlApplicationContext(FileUtil.getApplicationContextFilePath(root , "applicationContext.xml").getAbsolutePath()));
//			Thread.currentThread().setContextClassLoader(currentClassLoader); 
		}
		return context ;
	}
}
