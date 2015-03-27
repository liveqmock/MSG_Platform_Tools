package com.neusoft.util.tools.seg;

import java.util.List;

import org.lionsoul.jcseg.Dictionary;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.ILexicon;
import org.lionsoul.jcseg.core.IWord;
import org.lionsoul.jcseg.core.JcsegTaskConfig;

import com.neusoft.core.EapDataContext;
import com.neusoft.web.model.Keyword;

public class PlatformDictionary extends Dictionary{

	public PlatformDictionary(JcsegTaskConfig arg0, Boolean arg1) {
		super(arg0, arg1);
	}
	
	@SuppressWarnings("unchecked")
	public static void loadWordsFromSMC( 
			JcsegTaskConfig config, 
			ADictionary dic, String charset ) {
		List<Keyword> kwList = EapDataContext.getService().findAllByIObjectCType(Keyword.class) ;
    	for(Keyword word : kwList){
    		if(word!=null && word.getKeyword()!=null){
    			int t = ADictionary.getIndex(word.getKeyword());
        		boolean olen = (t == ILexicon.EC_MIXED_WORD);
        		olen = olen || (t == ILexicon.CE_MIXED_WORD);
        		olen = olen || (t == ILexicon.EN_PUN_WORD);
        		if ( olen || word.getKeyword().length() <= config.MAX_LENGTH ) {
        			dic.add(t, word.getKeyword(), IWord.T_CJK_WORD);
        		}
    		}
    	}
	}

}
