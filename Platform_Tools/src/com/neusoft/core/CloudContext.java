package com.neusoft.core;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.SimpleOrderedMap;

import com.neusoft.util.date.RivuDocument;
import com.neusoft.util.date.SearchResult;


public class CloudContext {
	public final static  String R3_SERVER_ADDRESS = "http://demo.rivues.com:885/search/" ;
//	public final static  String R3_SERVER_ADDRESS_PUSH = "http://127.0.0.1:890/push" ;
	private static HttpSolrServer server = null ;
	public static int hlNum = 150 ;
	
	public synchronized static SolrServer getSolrServer(String orgi) throws SolrServerException{
		if(server == null){
			try {
				server = new HttpSolrServer(R3_SERVER_ADDRESS);
				server.setFollowRedirects(false) ;
			} catch (Exception e) {
				throw new SolrServerException(e) ;
			}
		}
		return server ;
	}
	/**
	 * 
	 * @param ct
	 * @param kb
	 * @param ps
	 * @return
	 */
	public static SolrQuery createSearchQuery(String fq , int ps , int start){
		SolrQuery query = new org.apache.solr.client.solrj.SolrQuery();
		query.set("q", "{!join from=dataid to=id}status:1 AND flowstatus:1") ;
		query.add("hl", "true") ;
		query.add("hl.fl", "title") ;
		query.add("hl.fl", "text") ;
		query.add("hl.simple.pre", "<font color='red'>") ;
		query.add("hl.simple.post", "</font>") ;
		query.add("hl.usePhraseHighlighter", "true") ;
		query.add("hl.snippets", "3") ;
		query.add("hl.fragsize", "50") ;
		query.add("hl.q", query.get("q")) ;
		query.set("fq", fq) ;
		query.set("rows", ps) ;
		query.set("start", start) ;
		return query ;
	}
	
	/**
	 * 
	 * @param ct
	 * @param kb
	 * @param ps
	 * @return
	 * @throws SolrServerException 
	 */
	
	public static SearchResult getKnowledge(String orgi , String ct , String kb , int ps , int start) throws SolrServerException{
		StringBuffer strb = new StringBuffer().append("orgi:").append(orgi).append(" AND (kmtypecate:").append(kb) ;
		if(ct!=null && ct.length()>0){
			strb.append(" OR kmtypecate:").append(ct);
		}
		strb.append(")") ;
		return search(CloudContext.createSearchQuery(strb.toString() , 20 , start) , orgi) ;
	}
	
	/**
	 * 
	 * @param ct
	 * @param kb
	 * @param ps
	 * @return
	 * @throws SolrServerException 
	 */
	
	public static SearchResult getDocument(String orgi , String id, int ps , int start) throws SolrServerException{
		return search(CloudContext.createSearchQuery(new StringBuffer().append("id:").append(id).append(" AND orgi:").append(orgi).toString() , 20 , start) , orgi) ;
	}
	/**
	 * 搜索
	 * @param query
	 * @param orgi
	 * @return
	 * @throws SolrServerException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static SearchResult search(SolrQuery query , String orgi) throws SolrServerException{
		SearchResult result = new SearchResult();
		QueryResponse rsp = CloudContext.getSolrServer(orgi).query(query) ;
		result.setDocNum(rsp.getResults().getNumFound()) ;
		result.setStart(rsp.getResults().getStart()) ;
		result.setRows(rsp.getResults().size()) ;
		result.setTime(rsp.getQTime()) ;
		result.setStatus(rsp.getStatus()) ;
		if(true){
			result.getFacetList().setFaceFieldList(rsp.getFacetFields()) ;
			result.getFacetList().setFacetQuery(rsp.getFacetQuery()) ;
			result.getFacetList().setFacetDateList(rsp.getFacetDates()) ;
		}
		RivuDocument rivuDocument = null ;
		SimpleOrderedMap nlt = (SimpleOrderedMap) rsp.getResponse().get("moreLikeThis") ;
		for(int i=0 ; i<rsp.getResults().size() ; i++){
			rivuDocument = new RivuDocument(rsp.getResults().get(i), rsp.getHighlighting()!=null?rsp.getHighlighting().get(rsp.getResults().get(i).get("id")):null) ;
			if(nlt!=null && nlt.size()>0 && nlt.get(rivuDocument.get("id"))!=null){
				rivuDocument.setMorelike((SolrDocumentList) (nlt.get(rivuDocument.get("id")))) ;
			}
			result.getDocList().add(rivuDocument) ;
		}
		
		return result ;
	}
}
