package com.parse.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.codecod.connection.MySQLConnection;
import com.codecod.model.MicrotaskModel;
import com.codecod.model.TaskModel;

/**
 * Servlet implementation class jarDemo
 */
@WebServlet("/jarDemo")
public class jarDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	
	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jarDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.println("Error: From must has enctype=multipart/form-data");
			writer.flush();
			return;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");

		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			    
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName= " + fileItem.getFieldName());
				System.out.println("FileName=" + fileItem.getName());
				System.out.println("Size in bytes =" + fileItem.getSize());
				
//				File file = new File("D:\\Java EE Workspace\\Uploaded"+File.separator+fileItem.getName());
//				System.out.println("Absolute Path at server = "+file.getAbsolutePath());
//				fileItem.write(file);
				
				 JarFile jarfile = new JarFile("D:\\Java EE Workspace\\Uploaded\\"+File.separator+fileItem.getName());
				 Enumeration<java.util.jar.JarEntry> enu= jarfile.entries();
				 while(enu.hasMoreElements()) {
					
					 String destdir = "D:\\Java EE Workspace\\Uploaded\\JAR";     //my destination directory
				     java.util.jar.JarEntry je = enu.nextElement();

				        System.out.println(je.getName());

				        java.io.File fl = new File(destdir, je.getName());
				        if(!fl.exists())
				        {
				            fl.getParentFile().mkdirs();
				            fl = new java.io.File(destdir, je.getName());
				        }
				        if(je.isDirectory())
				        {
				            continue;
				        }
				        java.io.InputStream is = jarfile.getInputStream(je);
				        java.io.FileOutputStream fo = new java.io.FileOutputStream(fl);
				        while(is.available()>0)
				        {
				            fo.write(is.read());
				        }
				        fo.close();
				        is.close();
				 }
				out.write("File "+fileItem.getName()+" uploaded successfully");
			}
			
		} catch (FileUploadException e) {
			out.write("exception in uploading file");
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

}
