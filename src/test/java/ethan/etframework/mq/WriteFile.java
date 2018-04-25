package ethan.etframework.mq;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
	public static void main(String[] args) throws IOException{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:/ettest.dat");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos != null){
				fos.close();
			}
		}
		
	}
}
