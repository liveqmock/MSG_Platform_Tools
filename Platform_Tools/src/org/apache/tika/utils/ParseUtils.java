/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.utils;

//JDK imports
import java.io.File;
import java.io.IOException;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.TikaMimeKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.Parser;

/**
 * Contains utility methods for parsing documents. Intended to provide simple
 * entry points into the Tika framework.
 */
@SuppressWarnings("deprecation")
public class ParseUtils implements TikaMimeKeys {

    /**
     * Returns a parser that can handle the specified MIME type, and is set to
     * receive input from a stream opened from the specified URL. NB: Close the
     * input stream when it is no longer needed!
     * 
     * @param config
     * @param mimeType
     *            the document's MIME type
     * @return a parser appropriate to this MIME type
     * @throws TikaException
     */
    public static Parser getParser(String mimeType, TikaConfig config)
            throws TikaException {
    	return config.getParser(MediaType.parse(mimeType));
    }

    /**
     * Returns a parser that can handle the specified MIME type, and is set to
     * receive input from a stream opened from the specified URL. NB: Close the
     * input stream when it is no longer needed!
     * 
     * @param documentFile
     *            File object pointing to the document to parse
     * @param config
     * @return a parser appropriate to this MIME type and ready to read input
     *         from the specified document
     * @throws TikaException
     * @throws IOException 
     */
    public static Parser getParser(File documentFile, TikaConfig config)
            throws TikaException, IOException {
    	Parser parser = null ;
		String mimetype = config.getMimeRepository().getMimeType(documentFile).getName();
    	if(parser==null){
	        parser = getParser(mimetype, config);
    	}
        return parser ;
    }

}
