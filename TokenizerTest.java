package TokenizerTest;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;

public class TokenizerTest {
	public static void main(String[] args) throws IOException{
		Analyzer analyzer = new MyAnalyzer();
		String string = "今天很荣幸参加肖老师组织的数据结构课程";
		TokenStream ts;
		try {
			ts = analyzer.tokenStream("dummy", new StringReader(string));
			ts.reset();
			OffsetAttribute offsetAttribute = ts.getAttribute(OffsetAttribute.class);
			CharTermAttribute termAttribute = ts.getAttribute(CharTermAttribute.class);

            System.out.println(ts.incrementToken());
			while (ts.incrementToken()) {
				int startOffset = offsetAttribute.startOffset();
				int endOffset = offsetAttribute.endOffset();
				String term = termAttribute.toString();
				System.out.println(ts);
			}
			ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
}
