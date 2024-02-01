package file_io.writefile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import file_io.readfile.ReadAndPrintExam;

public class WriteExam {
	
	/**
	 * 파일을 쓴다
	 * @param parent 파일을 쓸 경로
	 * @param filename 쓸 파일의 이름
	 * @param append 이어서 쓸 것인지 여부
	 */
	// 1.8미만에서 작성되는 코드
	public static void writeFile(String parent, String filename, boolean append) {
				
		File file = new File(parent, filename);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		List<String> originalFileDescription = new ArrayList<>();
		
		if (! append) {
			// (파일을 덮어 쓰지 않기 위한 코드)이어서 쓰지 않을 것이라면..영역
			int index = 2; 
			while (file.exists()) {
				file = new File("C:\\java\\outputs",
						"java_output (" + (index++) + ").txt");
			}
		} else {
			// 이어서 쓸 것이라면..영역
			// 기존의 파일 내용을 읽어와서 List<String>으로 반환 받는다
			// 반환 받은 내용을 originalFileDescription 에 addAll 한다
			originalFileDescription.addAll(ReadAndPrintExam.getAllLines(file) );
		}
		// (위 코드가 없다면) 파일을 이어서 쓸 수는 없나?
		// 파일을 이어서 쓸 수 있는 메소드는 x
		// Java 1.8 도입 ==> 이어쓰기 O
		// Java 1.8 미만
		//   기존의 파일 내용을 다 읽어와서 새롭게 파일을 쓴다
		
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileWriter = new FileWriter(file, Charset.forName("UTF-8"));
			bufferedWriter = new BufferedWriter(fileWriter);
			
			if(append) {
				for(String originalFileLine : originalFileDescription) {
					bufferedWriter.write(originalFileLine);
				}
			}
			bufferedWriter.write("파일을 씁니다1.\n");
			bufferedWriter.write("파일을 씁니다2.\n");
			bufferedWriter.write("파일을 씁니다3.\n");
			bufferedWriter.flush();
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		finally {
			if(bufferedWriter != null) {
				try {
					bufferedWriter.close();
				}
				catch(IOException ioe) {}
			}
			if (fileWriter != null) {
				try {
					fileWriter.close();
				}
				catch(IOException ioe) {}
			}
			System.out.println(file.getAbsolutePath());
		} 
				
	}
	
	public static void main(String[] args) {
		writeFile("C:\\java\\outputs", "java_output.txt", false);
		writeFile("C:\\java\\outputs", "java_output.txt", true);
		writeFile("C:\\java\\outputs", "java_output.txt", true);
		writeFile("C:\\java\\outputs", "java_output.txt", false);
		
	}

}
