package TokenizerTest;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

public class MyAnalyzer extends Analyzer{

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader in) {
        System.out.println("<createComponents> " + Helper.readerToString(in));
        try{
            in.reset();
        } catch(Exception e){
            e.printStackTrace();
        }
		Tokenizer _IKTokenizer = new MyTokenizer(in);
		return new TokenStreamComponents(_IKTokenizer);
	}
	
}
