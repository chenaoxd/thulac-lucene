package TokenizerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Helper {
	public static String readerToString(Reader reader){
		BufferedReader r = new BufferedReader(reader);
		StringBuilder b = new StringBuilder();
		String line;
		int counter = 0;
		try {
			while((line=r.readLine())!=null) {
				if(counter != 0){
					b.append("\r\n");
				}
				b.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try{
            reader.reset();
        }catch(Exception e){
            e.printStackTrace();
        }

		return b.toString();
	}
}
