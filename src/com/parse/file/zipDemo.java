package com.parse.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class zipDemo
 */
@WebServlet("/zipDemo")
public class zipDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
	byte[] buffer = new byte[1024];
	
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
    public zipDemo() {
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
			
			while(fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				
				File files = new File("D:\\Java EE Workspace\\Uploaded"+File.separator+fileItem.getName());
				fileItem.write(files);
				
				//get the zip file content
		    	ZipInputStream zis = new ZipInputStream(new FileInputStream(files.getAbsolutePath()));
		    	//get the zipped file list entry
		    	ZipEntry ze = zis.getNextEntry();
		    	
		    	System.out.print(ze);
		    	
		    	while(ze!=null){
		    		
		     	   String fileName = ze.getName();
		           File newFile = new File("D:\\Java EE Workspace\\Uploaded\\ZIP" + File.separator + fileName);
		                
		           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
		                
		            //create all non exists folders
		            //else you will hit FileNotFoundException for compressed folder
		            new File(newFile.getParent()).mkdirs();
		              
		            FileOutputStream fos = new FileOutputStream(newFile);             

		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		            }
		            
		            fos.close();   
		            ze = zis.getNextEntry();
		    	}
		    	zis.closeEntry();
		    	zis.close();
		    		
		    	System.out.println("Done");			
			}
		} catch (FileUploadException e) {
			out.write("exception in uploading file");
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	
	}

}
