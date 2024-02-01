package file_io.readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadAndPrintExam {
	
	public static void main(String[] args) {
		
		File file = new File("C:\\Java Exam", "Java Exam.txt");
		
		if (file.exists() && file.isFile()) {
			FileReader reader = null;
			BufferedReader bufferedReader = null;
			
			try { 
				reader = new FileReader(file, Charset.forName("UTF-8"));
				bufferedReader = new BufferedReader(reader);
				String line = null;
				
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
			} catch(IOException ioe) {
				System.out.println(ioe.getMessage());
				ioe.printStackTrace();
			}
			finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch(IOException ioe) {}
					
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException ioe) {}
					
				}
								
			}
			
		}		
						
		}

	}
}
