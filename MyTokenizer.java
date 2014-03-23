package TokenizerTest;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class MyTokenizer extends Tokenizer{
	private final CharTermAttribute termAtt;
	private final OffsetAttribute offsetAtt;
	private final TypeAttribute typeAtt;
	private int counter = 0;
	private int length = 0;
	private String content = "";

	protected MyTokenizer(Reader input) {
		super(input);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
		termAtt = addAttribute(CharTermAttribute.class);
		content = Helper.readerToString(input);
		length = content.length();
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		if(counter < length){
			termAtt.append(content.charAt(counter));
			offsetAtt.setOffset(counter, counter + 1);
			typeAtt.setType("word");
			counter ++;
			return true;
		}
		return false;
	}

}
