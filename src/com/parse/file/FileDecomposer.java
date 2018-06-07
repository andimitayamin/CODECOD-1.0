package com.parse.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileDecomposer {
	public static final String DIR = "D:\\Java EE Workspace\\Uploaded\\ZIP";
	
	public List<String> extractZip (String zipPath) {
		byte[] buffer = new byte[2084];
		List<String> path = new ArrayList<String>();
		
		try {
		ZipFile zip = new ZipFile (zipPath);
		Enumeration<?> enu = zip.entries();
		
		while(enu.hasMoreElements()) {
			
			ZipEntry zipEntry = (ZipEntry)enu.nextElement();
			
			//Create the directory
			File files = new File(String.format("%s\\Extract\\%s",DIR,zipEntry.getName()));
			if((zipEntry.getName()).endsWith("/")) {
				files.mkdirs();
				continue;
			}
			
			File parent = files.getParentFile();
			if(parent != null) {
				parent.mkdirs();
			}
					
			//extract files ONLY JAVA FILES
			if((zipEntry.getName()).endsWith(".java")) {
				
			InputStream is = zip.getInputStream(zipEntry);			
			FileOutputStream outputFile = new FileOutputStream(files);
			int len;
			while ((len = is.read(buffer))>0) {
					outputFile.write(buffer, 0, len);
					path.add(new String(files.getAbsolutePath()));
			}
			is.close();
			outputFile.close();
			}
			
		}
		zip.close();
	}catch (IOException e) {
        e.printStackTrace();
	}
		
		return path;
 }
}
