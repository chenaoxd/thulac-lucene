package TokenizerTest;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class MyTokenizer extends Tokenizer{
	private final CharTermAttribute termAtt;
	private final OffsetAttribute offsetAtt;
	private final TypeAttribute typeAtt;
	private String content = "";
	private ArrayList<Term> terms;
	private int currentTerm;
	private int length;

	private static ArrayList<Term> thulacSeg(String content){
		ArrayList<Term> resultList = new ArrayList<Term>();
		String tmp = "";
		int begin = 0, end = 0;
		String type = "word";
		boolean isWord = true;
		for(int i = 0; i < content.length(); i ++){
			if(content.charAt(i) == '_'){
				resultList.add(new Term(tmp, type, begin, i));
				tmp = "";
			}else if(content.charAt(i) == ' '){
				tmp = "";
				begin = i + 1;
			}else{
				tmp += content.charAt(i);
			}
		}
		return resultList;
	}
	
	protected MyTokenizer(Reader input) {
		super(input);
		offsetAtt = addAttribute(OffsetAttribute.class);
		typeAtt = addAttribute(TypeAttribute.class);
		termAtt = addAttribute(CharTermAttribute.class);
		content = Helper.readerToString(input);
		
		String segResult = content;
		terms = thulacSeg(segResult);
		currentTerm = 0;
		length = terms.size();
		System.out.println(length);
	}

	@Override
	public boolean incrementToken() throws IOException {
		clearAttributes();
		if(currentTerm < length){
			Term tmp = terms.get(currentTerm);
			termAtt.append(tmp.term);
			offsetAtt.setOffset(tmp.beginOffset, tmp.endOffset);
			typeAtt.setType(tmp.type);
			currentTerm ++;
			return true;
		}
		return false;
	}

}
