package com.codecod.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * Servlet implementation class UploadTaskController
 */
@WebServlet("/UploadTaskController")
@MultipartConfig
public class UploadTaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	MySQLConnection connection = MySQLConnection.getInstance();

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
	public UploadTaskController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				
//				TaskModel task = new TaskModel(fileItem);
				
//				MySQLConnection connection = MySQLConnection.getInstance();
//				connection.executeStore(task.insertTask());

		//		List<MicrotaskModel> microtasks = task.getMicrotaskByMethod();
				
//				for (MicrotaskModel microtask : microtasks) {
//					connection.executeStore(microtask.insertMicrotask());
//					System.out.println(microtask);
//				}
			}
		} catch (FileUploadException e) {
			out.write("exception in uploading file");
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}
}
