package com.neusoft.util.tools;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.lionsoul.jcseg.ASegment;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.IWord;
import org.lionsoul.jcseg.core.JcsegException;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

import com.neusoft.core.EapDataContext;
import com.neusoft.util.tools.seg.PlatformDictionary;
import com.neusoft.web.model.FAQModel;
import com.neusoft.web.model.Instruction;
import com.neusoft.web.model.Material;
//import com.webssky.jcseg.ASegment;
//import com.webssky.jcseg.core.ADictionary;
//import com.webssky.jcseg.core.DictionaryFactory;
//import com.webssky.jcseg.core.IWord;
//import com.webssky.jcseg.core.JcsegException;
//import com.webssky.jcseg.core.JcsegTaskConfig;
//import com.webssky.jcseg.core.SegmentFactory;

public class IndexTools {
    private Directory directory = null;
    private DirectoryReader reader = null;
    private IndexWriterConfig config = null;
    private IndexWriter writer = null;
    private IndexSearcher searcher ;
    private static IndexTools instance ;
    private JcsegTaskConfig jcsegConfig = new JcsegTaskConfig();
    private ADictionary dic = DictionaryFactory.createDefaultDictionary(jcsegConfig) ;
    private ASegment seg = null ;
    
    public static IndexTools getInstance(){
    	if(instance == null){
    		instance = new IndexTools() ;
    	}
    	return instance ;
    }

    @SuppressWarnings({ "unchecked"})
	public IndexTools() {
        try {
        	seg = (ASegment) SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE , new Object[]{jcsegConfig, dic}) ;
        	PlatformDictionary r3dic = new PlatformDictionary(jcsegConfig, true) ;
        	r3dic.loadWordsFromSMC(jcsegConfig, dic, "UTF-8") ;
        	boolean reindex = false ;
        	File indexFileDir = new File(EapDataContext.REAL_PATH , "data/index") ;
        	if(!indexFileDir.exists()){
        		reindex = true ;
        	}
            directory = FSDirectory.open(indexFileDir);
            config = new IndexWriterConfig(Version.LUCENE_44,new StandardAnalyzer(Version.LUCENE_44));
            writer = new IndexWriter(directory, config);
            if(reindex){
            	List<FAQModel> faqList = EapDataContext.getService().findAllByIObjectCType(FAQModel.class) ;
            	for(FAQModel faq : faqList){
            		if(faq.getTitle()!=null && faq.getOrgi()!=null && faq.getId()!=null){
            			faq(faq) ;
            		}
            	}
            	List<Instruction> insList = EapDataContext.getService().findAllByIObjectCType(Instruction.class) ;
            	for(Instruction ins : insList){
            		ins(ins) ;
            	}
            	List<Material> matList = EapDataContext.getService().findAllByIObjectCType(Material.class) ;
            	for(Material mat : matList){
            		mat(mat) ;
            	}
            }
            commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JcsegException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 添加FAQ
     * @throws IOException
     */
    public void faq(FAQModel faq) throws IOException {
    	Document document = new Document();
        document.add(new StringField("id", faq.getId(), Store.YES));
        document.add(new TextField("title", faq.getTitle(), Store.YES));
        document.add(new StringField("orgi", faq.getOrgi(), Store.YES));
        document.add(new TextField("text", faq.getMaincontext(), Store.YES));
        document.add(new org.apache.lucene.document.NumericDocValuesField("date", faq.getCreatetime().getTime()));
        document.add(new TextField("keyword", faq.getKeywords()!=null ? faq.getKeywords() : "", Store.YES)) ;
        document.add(new StringField("type", "faq", Store.YES));
        writer.updateDocument(new Term("id" , faq.getId()), document) ;
    }
    
    /**
     * 添加指令
     * @throws IOException
     */
    public void ins(Instruction ins) throws IOException {
    	Document document = new Document();
        document.add(new StringField("id", ins.getId(), Store.YES));
        document.add(new TextField("title", ins.getName(), Store.YES));
        document.add(new StringField("code", ins.getCode(), Store.YES));
        document.add(new StringField("orgi", ins.getOrgi(), Store.YES));
        document.add(new StringField("type", "ins", Store.YES)) ;
        writer.updateDocument(new Term("id" , ins.getId()), document) ;
    }
    
    /**
     * 添加素材
     * @throws IOException
     */
    public void mat(Material material) throws IOException {
    	Document document = new Document();
        document.add(new StringField("id", material.getId(), Store.YES));
        document.add(new TextField("title", material.getTitle(), Store.YES));
        document.add(new StringField("code", material.getCode(), Store.YES));
        document.add(new StringField("orgi", material.getOrgi(), Store.YES));
        document.add(new StringField("type", "mat", Store.YES)) ;
        writer.updateDocument(new Term("id" , material.getId()), document) ;
    }
    
    /**
     * 查询索引
     * @throws IOException
     * @throws ParseException
     */
    public List<Document> search(String qWord,String orgi , int docNum) throws IOException, ParseException {
    	return search(null , qWord , orgi , docNum , true);
    }

    /**
     * 查询索引
     * @throws IOException
     * @throws ParseException
     */
    public List<Document> search(String type ,String qWord,String orgi , int docNum , boolean token) throws IOException, ParseException {
    	List<Document> docList = new ArrayList<Document>();
    	BooleanQuery query = new BooleanQuery();
    	if(type!=null && type.length()>0){
    		query.add(new TermQuery(new Term("type", type)), Occur.MUST) ;
    	}
    	query.add(new TermQuery(new Term("orgi", orgi!=null && orgi.length() >0 ? orgi : "NOT_FOUND")), Occur.MUST) ;
    	seg.reset(new StringReader(qWord));
    	//获取分词结果
    	IWord word = null;
    	StringBuffer strb = new StringBuffer();
    	if(token){
	    	while ( (word = seg.next()) != null ) {
	    		if(word.getValue().length()>1 && strb.indexOf(word.getValue())<0){
	    			if(strb.length() >0){
	    				strb.append(" OR ") ;
	    			}
	    			strb.append("\"").append(word.getValue()).append("\"") ;
	    		}
	    	}
    	}else{
    		strb.append(qWord) ;
    	}
    	
    	if(strb.length()>0){
    		query.add(new QueryParser(Version.LUCENE_44, "title", new StandardAnalyzer(Version.LUCENE_44)).parse(strb.toString()) , Occur.MUST) ;
	        TopDocs docs = searcher.search(query, 5);
	        for (ScoreDoc doc : docs.scoreDocs) {
	            docList.add(reader.document(doc.doc)) ;
	        }
    	}
        return docList ;
    }

    public void commit(){
    	try {
			writer.commit();
			writer.maybeMerge();
			reader = DirectoryReader.open(directory);
            searcher = new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 删除索引 删除的索引会保存到一个新的文件中（以del为结尾的文件 相当于删除到回收站）
     * @throws IOException
     */
    public void delete(String id) throws IOException {
        writer.deleteDocuments(new Term("id", id));
    }

    /**
     * 删除所有的索引 删除的索引会保存到一个新的文件中（以del为结尾的文件 相当于删除到回收站）
     * @throws IOException
     */
    public void deleteAll(String orgi) throws IOException {
        writer = new IndexWriter(directory, config);
        writer.deleteDocuments(new Term("orgi", orgi)) ;
    }

    /**
     * 删除已经删除的索引 对应上一个删除方法 删除回收站的文件
     * @throws IOException
     */
    public void forceMergeDeletes() throws IOException {
        writer = new IndexWriter(directory, config);
        writer.forceMergeDeletes();// 清空回收站
    }

	public ADictionary getDic() {
		return dic;
	}

	public void setDic(ADictionary dic) {
		this.dic = dic;
	}
    
}
