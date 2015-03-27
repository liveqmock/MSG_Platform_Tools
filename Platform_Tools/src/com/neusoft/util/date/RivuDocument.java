/**
 * 
 */
package com.neusoft.util.date;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;

import com.neusoft.core.CloudContext;
import com.neusoft.util.PlatformMsgTools;

/**
 * @author iceworld
 *
 */
public class RivuDocument {
	private SolrDocument document ;
	private Map<String , List<String>> hlList ;
	private SolrDocumentList morelike = new SolrDocumentList();
	public RivuDocument(){}
	public RivuDocument(SolrDocument document , Map<String , List<String>> hlList){
		this.document = document ;
		this.hlList = hlList!=null ? hlList: new java.util.HashMap() ;
	}
	public SolrDocument getDocument() {
		return document;
	}
	public void setDocument(SolrDocument document) {
		this.document = document;
	}
	
	public Map<String, List<String>> getHlList() {
		return hlList;
	}
	public void setHlList(Map<String, List<String>> hlList) {
		this.hlList = hlList;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name){
		return getValue(document.getFieldValue(name)) ;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public String get(String name , boolean replace){
		String value = getValue(document.getFieldValue(name)) ; 
		return escape(replace?value.replace("<", "&lt;").replaceAll(">", "&gt;").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "").replaceAll("[ \\t\\r]*?\\n[ \\t\\r]*?\\n[ \\t\\r]*?\\n", "\n").replaceAll("\\?", "&#63;"):value) ;
	}	
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String get(String name , String encode) throws UnsupportedEncodingException{
		return java.net.URLEncoder.encode(get(name),encode) ; 
	}	
	/**
	 * Get HightLight
	 * @param name
	 * @return
	 */
	public String getHl(String name){
		String value = getValue(hlList.get(name)) ;
		
		if(value == null){
			value = new StringBuffer().append(getValueLimit(get(name)).substring(0 , CloudContext.hlNum)).append("......").toString() ;
		}
		return escape(value) ;
	}
	
	public String escape(String text){
		return text!=null?text.toLowerCase().replaceAll("<table", "&lt;table").replaceAll("table>", "table&gt;").replaceAll("<div", "&lt;div").replaceAll("div>", "div&gt;").replaceAll("<tr", "&lt;tr").replaceAll("tr>", "tr&gt;").replaceAll("<td", "&lt;td").replaceAll("td>", "td&gt;"):"" ;
	}
	/**
	 * Convert 
	 * @param strArr
	 * @return
	 */
	private String getValue(Object strArr){
		if(strArr==null)
			return null ;
		StringBuffer strb = new StringBuffer() ;
		if(strArr instanceof String[]){
			for(String str : (String[])strArr)
				strb.append(str) ;
		}else if(strArr instanceof List){
			for(Object str : (List)strArr){
				if(strb.length()>0){
					strb.append(" ") ;
				}
				if(str instanceof Date){
					strb.append(PlatformMsgTools.formatDate((Date)str)) ;
				}else{
					strb.append(str!=null?str.toString():"") ;
				}
			}
		}else if(strArr instanceof String){
			strb.append((String)strArr) ;
		}else if(strArr instanceof Date){
			strb.append(PlatformMsgTools.formatDate((Date)strArr)) ;
		}else{
	    	strb.append(strArr) ;
	    }
		return strb.toString() ;
	}

	/**
	 * Convert 
	 * @param strArr
	 * @return
	 */
	private String getValueLimit(String text){
		if(text==null)
			return "" ;
		return text ;
	}
	public SolrDocumentList getMorelike() {
		return morelike;
	}
	public void setMorelike(SolrDocumentList morelike) {
		this.morelike = morelike;
	}
	
}
