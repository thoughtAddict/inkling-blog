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
		logger.debug("=======================================================");
		
		int startWithLimit = 100;
		
		mongoTemplate.dropCollection("posts");
		
		Map<Integer,String> authorMap = new HashMap<Integer,String>();
		authorMap.put(new Integer(0), "ThoughtAddict");
		authorMap.put(new Integer(1), "E. Nigma");
		authorMap.put(new Integer(2), "Tom Foolery");
		authorMap.put(new Integer(3), "Justin Case");
		
		Map<Integer,String> categoryMap = new HashMap<Integer,String>();
		categoryMap.put(new Integer(0), "Technology");
		categoryMap.put(new Integer(1), "General Talk");
		categoryMap.put(new Integer(2), "Wangled Widgets");
		categoryMap.put(new Integer(3), "News of the 1800's");
				
		for ( int i = 0; i < startWithLimit; i++ ) {
			
			int author = randomInt(4);
			int category1 = randomInt(4);
			int category2 = randomInt(4);
			String date = variableDateToString(DEFAULT_DATEFORMAT, i-startWithLimit+1);
			String title = "Review: '" + randomString(36) + "' JS library.";
			String theAuthor = authorMap.get(author);

			BlogPost p = new BlogPost();
			p.setPostId(UUID.randomUUID().toString());
			p.setAuthor(theAuthor);
			p.setDateCreated(date);
			p.setTitle(title);
			p.setContent(i + " Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		    p.addCategories(categoryMap.get(category1));
		    p.addCategories(categoryMap.get(category2));
			mongoTemplate.insert("posts", p);
		}
		logger.debug("======================================================= " + startWithLimit);
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
