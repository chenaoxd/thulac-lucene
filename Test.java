package TokenizerTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Test{
	
    public static void main(String[] args){
    	Analyzer analyzer = new MyAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
        Directory dir;
        IndexWriter indexWriter = null;
        try{
            dir = FSDirectory.open(new File("./index"));
            indexWriter = new IndexWriter(dir, config);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        String content = "我有一份数据";
        Document doc = new Document();
        Field contentField = new Field("content", content, Field.Store.YES, Field.Index.ANALYZED);
        Field idField = new Field("id", "id_1", Field.Store.YES, Field.Index.NO);
        doc.add(contentField);
        doc.add(idField);
        
        String content2 = "数据结构课程";
        Document doc2 = new Document();
        Field contentField2 = new Field("content", content2, Field.Store.YES, Field.Index.ANALYZED);
        Field idField2 = new Field("id", "id_2", Field.Store.YES, Field.Index.NO);
        doc2.add(contentField2);
        doc2.add(idField2);

        try {
			indexWriter.addDocument(doc);

			indexWriter.addDocument(doc2);

			indexWriter.commit();
			indexWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        QueryParser parser = new QueryParser(Version.LUCENE_47, "content", new MyAnalyzer());
        IndexReader reader = null;
		try {
            reader = IndexReader.open(FSDirectory.open(new File("./index")));
        } catch(IOException e) {
            e.printStackTrace();
        }
 
        List<Document> array = new ArrayList();
        IndexSearcher searcher = new IndexSearcher(reader);
        Query query;
        String queryString = "数据";
		try {
			query = parser.parse(queryString);
			TopDocs tops = searcher.search(query, null, 10);
			ScoreDoc[] hits = tops.scoreDocs;
			for(int i = 0;i < hits.length; i ++){
				array.add(searcher.doc(hits[i].doc));
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
        for(int i = 0; i < array.size(); i ++){
            System.out.println(array.get(i).get("id"));
            System.out.println(array.get(i).get("content"));
        }
    }
}
