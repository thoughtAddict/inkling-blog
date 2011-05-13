package com.thoughtaddict.mongoblog.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.thoughtaddict.mongoblog.javabean.BlogPost;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class InitService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	private static final String DEFAULT_DATEFORMAT = "MM/dd/yyyy H:mm a";

	private void init() {

		logger.debug("Init MongoDB users");
		
		int startWithLimit = 5;
		
		mongoTemplate.dropCollection("posts");
		
		Map<String,String> authorMap = new HashMap<String,String>();
		authorMap.put("0", "ThoughtAddict");
		authorMap.put("1", "E. Nigma");
		authorMap.put("2", "Tom Foolery");
		authorMap.put("3", "Justin Case");
		
		for ( int i = 0; i < startWithLimit; i++ ) {
			
			int author = randomInt(4);
			String date = variableDateToString(DEFAULT_DATEFORMAT, i-startWithLimit+1);
			String title = "Review: '" + randomString(36) + "' JS library.";

			BlogPost p = new BlogPost();
			p.setPostId(UUID.randomUUID().toString());
			p.setAuthor(authorMap.get(author));
			p.setDateCreated(date);
			p.setTitle(title);
			p.setContent(i + " Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		    mongoTemplate.insert("posts", p);
		}
	}
	
	public static String randomString(int radixValue) {
		  Random r = new Random();
		  return Long.toString(Math.abs(r.nextLong()), radixValue);
	}

	public static int randomInt(int limit) {
		  Random r = new Random();
		  return Math.abs(r.nextInt(limit));
	}
	
	public static String variableDateToString(String format, int offset) {

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}
		
		Calendar cal = Calendar.getInstance() ;
		cal.setTime( new Date() ) ;
		cal.add( Calendar.DATE, offset ) ;
		Date td = cal.getTime() ;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String today = sdf.format(td);

		return today;
	}
}
