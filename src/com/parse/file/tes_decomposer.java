package com.parse.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.codecod.connection.MySQLConnection;
import com.codecod.controller.ClazzController;
import com.codecod.model.ClazzModel;
import com.codecod.model.MicrotaskModel;
import com.codecod.model.TaskModel;
import com.mysql.cj.jdbc.PreparedStatement;

/**
 * Servlet implementation class tes_decomposer
 */
@WebServlet("/tes_decomposer")
public class tes_decomposer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	String DIR = "D:\\Java EE Workspace\\Uploaded\\ZIP";

	
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
    public tes_decomposer() {
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
				
//if (fileItem.isFormField()) {
//    String name = fileItem.getFieldName();
//    String value = fileItem.getString();
//    System.out.println(name + " = '" + value + "'");
//}else {
//	System.out.println("not caugth");
//}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		
//		String description = request.getParameter("description");
//		String comment = request.getParameter("comment");
//		System.out.println(description);
//		System.out.println(comment);
		

		HttpSession session = request.getSession(false);
		String id = null;
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();	
			
			if(session!=null) {
				id=(String)session.getAttribute("username");  
			}
			while(fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();			
				File files = new File(String.format("%s//%s", DIR, fileItem.getName()));
				fileItem.write(files);
				
				FileDecomposer zipFiles = new FileDecomposer();
				List<String>PathFromDecomposer = zipFiles.extractZip(String.format("%s", files.getAbsolutePath()));
			
				MySQLConnection connection = MySQLConnection.getInstance();

				//Save method as microtask into DB
				TaskModel task = new TaskModel();				
				List<MicrotaskModel> microtasks = task.getMicrotaskByMethod(PathFromDecomposer);
				for (MicrotaskModel microtask : microtasks) {
					connection.executeStore(microtask.insertMicrotask());
				}  
				
				//Save class as microtask into DB
				ClazzController clazz = new ClazzController();
				List<ClazzModel>classes = clazz.getMicrotaskByClass(PathFromDecomposer);
				for(ClazzModel temp : classes) {
									
					connection.executeStore(temp.insertClazz());
					
					System.out.println("diclass");
				}
				 
				//Save task file into DB
				for (String temp : PathFromDecomposer) {					

					task.setTaskPath(temp.replace("\\", "\\\\"));
					task.setTaskName(files.getName());
					task.setReqId(id);
					connection.executeStore(task.insertTask());
					
					System.out.println("diTask");
				}				

			}
			
			response.sendRedirect("list_of_task");
			
		} catch (FileUploadException e) {
			out.write("exception in uploading file");
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

}
