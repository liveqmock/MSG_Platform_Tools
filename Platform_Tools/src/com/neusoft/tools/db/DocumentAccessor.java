package com.neusoft.tools.db;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;

public class DocumentAccessor {
	 // Person accessors 
    // 
    public PrimaryIndex<String,Sequence> id; 
  
    public DocumentAccessor(EntityStore store) 
        throws DatabaseException { 
        id = store.getPrimaryIndex(String.class, Sequence.class);    
    } 
}
