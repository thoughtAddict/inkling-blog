package com.thoughtaddict.mongoblog.service;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtaddict.mongoblog.javabean.BlogCategory;
import com.thoughtaddict.mongoblog.javabean.BlogPost;

import static org.springframework.data.document.mongodb.query.Criteria.where;


@SuppressWarnings("restriction")
@Service("blogService")
@Transactional
public class BlogService {

	protected static Logger logger = Logger.getLogger("service");
	
	private static final String collectionName = "posts";

	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;

	public List<BlogPost> getAllPosts() {
		
		Query query = new Query(where("postId").exists(true));
		List<BlogPost> posts = mongoTemplate.find(query, BlogPost.class);

		return posts;
	}
	
	public List<BlogCategory> getAllCategories() {

		// To Do: Find a way to do this with Mongo command, or spring data method
		
		List<BlogCategory> blogCategoryList = new ArrayList<BlogCategory>();
		Map<String,BlogCategory> holderMap = new HashMap<String,BlogCategory>();
		
		Query query = new Query(where("postId").exists(true));
		List<BlogPost> posts = mongoTemplate.find(query, BlogPost.class);
		
		for ( BlogPost post : posts ) {
			
			List<String> categories = post.getCategories();
			
			if ( null != categories && categories.size() > 0 ) {
				
				for ( String categoryName : categories ) {
				
					BlogCategory currBlogCat = holderMap.get(categoryName);
					
					if ( null == currBlogCat ) {
						currBlogCat = new BlogCategory();
						currBlogCat.setCategoryName(categoryName);
						currBlogCat.setNumberPosts(1);
						holderMap.put(categoryName, currBlogCat);
					} else {						
						currBlogCat.setNumberPosts( currBlogCat.getNumberPosts() + 1 );
					}					
				}
			}			
		}
		
		Iterator iterator = holderMap.keySet().iterator();
		while( iterator.hasNext() ) {			
			String key = (String) iterator.next();
			BlogCategory blogCategory = holderMap.get(key);
			blogCategoryList.add(blogCategory);
		}

		return blogCategoryList;
	}	

	public BlogPost get(String postId) {
		
		logger.debug("Retrieving an existing post.");
		Query query = new Query(where("postId").is(postId));
		BlogPost post = mongoTemplate.findOne(collectionName, query, BlogPost.class);

		return post;
	}

	public Boolean add(BlogPost post) {
		
		logger.debug("Adding a new user");

		try {

			post.setPostId(UUID.randomUUID().toString());
			mongoTemplate.insert(collectionName, post);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new post", e);
			return false;
		}
	}

	public Boolean delete(String postId) {
		
		logger.debug("Deleting existing post.");

		try {

			Query query = new Query(where("postId").is(postId));
			mongoTemplate.remove(query);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new post", e);
			return false;
		}
	}

	public Boolean edit(BlogPost post) {
		
		logger.debug("Editing existing post");

		try {

			Query query = new Query(where("postId").is(post.getPostId()));

			Update update = new Update();

			update.set("title", post.getTitle());
			mongoTemplate.updateMulti(query, update);

			update.set("content", post.getContent());
			mongoTemplate.updateMulti(query, update);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing post", e);
			return false;
		}

	}
}
