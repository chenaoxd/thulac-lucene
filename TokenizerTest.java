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
		String string = "今天_t 很_d 荣幸_a 参加_v 肖_np 老师_n 组织_v 的_u";
		Analyzer analyzer = new MyAnalyzer();
		TokenStream ts;
		try {
			ts = analyzer.tokenStream("dummy", new StringReader(string));
			ts.reset();
			OffsetAttribute offsetAttribute = ts.getAttribute(OffsetAttribute.class);
			CharTermAttribute termAttribute = ts.getAttribute(CharTermAttribute.class);

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
