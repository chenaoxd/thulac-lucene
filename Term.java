package TokenizerTest;

public class Term {
	public String term;
	public String type;
	public int beginOffset;
	public int endOffset;
	
	public Term(String _content, String _type, int _beginOffset, int _endOffset){
		term = _content;
		type = _type;
		beginOffset = _beginOffset;
		endOffset = _endOffset;
	}
	
	public String toString(){
		return term + "<" + beginOffset + ',' + endOffset + ">";
	}
}
