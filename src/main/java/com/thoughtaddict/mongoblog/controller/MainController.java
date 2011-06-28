package com.thoughtaddict.mongoblog.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtaddict.mongoblog.service.BlogService;


@SuppressWarnings("restriction")
@Controller
@RequestMapping("/")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name="blogService")
	private BlogService blogService;

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String getPosts(Model model) {
		return "postspage";
	}

}