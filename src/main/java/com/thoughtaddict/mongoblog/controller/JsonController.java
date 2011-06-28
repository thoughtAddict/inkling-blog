package com.thoughtaddict.mongoblog.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.thoughtaddict.mongoblog.javabean.*;
import com.thoughtaddict.mongoblog.service.BlogService;


@SuppressWarnings("restriction")
@Controller
@RequestMapping("/json")
public class JsonController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name="blogService")
	private BlogService blogService;

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String getPosts(Model model) {

		List<BlogPost> posts = blogService.getAllPosts();
		
		Gson gson = new Gson();
		Type listType = new TypeToken<List<BlogPost>>() {}.getType();
		String jsonOutput = gson.toJson(posts, listType);
		
		model.addAttribute("posts", jsonOutput);

		return "json/posts";
	}	
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String getCategories(Model model) {

		List<BlogCategory> categories = blogService.getAllCategories();
		
		Gson gson = new Gson();
		Type listType = new TypeToken<List<BlogCategory>>() {}.getType();
		String jsonOutput = gson.toJson(categories, listType);
		
		model.addAttribute("categories", jsonOutput);

		return "json/categories";
	}	
	
	@RequestMapping(value = "/archives", method = RequestMethod.GET)
	public String getArchives(Model model) {

		List<BlogArchive> archives = blogService.getAllArchives();
		
		Gson gson = new Gson();
		Type listType = new TypeToken<List<BlogArchive>>() {}.getType();
		String jsonOutput = gson.toJson(archives, listType);
		
		model.addAttribute("archives", jsonOutput);

		return "json/archives";
	}	
}
