<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>MongoBlog</title>
    <meta http-equiv="Content-Type" content="application/xhtml+xml; charset=iso-8859-1" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="imagetoolbar" content="no" />
    
    <script src="../js/jquery-1.6.1.min.js" type="text/javascript"></script>
    <script src="../js/jquery.tmpl.min.js" type="text/javascript"></script>
    <script src="../js/floating-1.5.js" type="text/javascript"></script>
    <script src="../js/underscore.js" type="text/javascript"></script>
    <script src="../js/backbone.js" type="text/javascript"></script>    
    <script src="../js/models.js" type="text/javascript"></script> 
    
    <link href="../css/style.css?version=1.0.2" rel="stylesheet" type="text/css" />
</head>    
    
<body>
    <div id="container">
        <div id="header">
            <h1 title="Blue Leaves">Mongo Blog</h1>
            <h2 title="how strange">A blog using Spring Data and MongoDB...</h2>
        </div>
        <ul id="nav">
                <li><a href="#" title="Home" class="on">Home</a></li>
                <li><a href="#" title="About">About</a></li>
        </ul>
        <div id="sidebar">
            <div>
                <h3 title="Categories">Categories</h3>
                <ul id="categoryUL"></ul>
            </div>
            <div>
                <h3 title="Archives">Archives</h3>
                <ul>
                    <li>Month 1 (1)</li>
                    <li>Month 2 (4)</li>
                    <li>Month 3 (5)</li>
                    <li>Month 4 (2)</li>
                    <li>Month 5 (11)</li>
                    <li>Month 6 (7)</li>
                </ul>
            </div>
            <div>
                <h3 title="Links">Links</h3>
                <ul>
                    <li>Link 1</li>
                    <li>Link 2</li>
                    <li>Link 3</li>
                    <li>Link 4</li>
                    <li>Link 5</li>
                    <li>Link 6</li>
                </ul>
            </div>
        </div>
        <div id="content"></div>
        <div id="footer">
            Copyright &copy; 2005 Company. All Rights Reserved.<br />
            Design by <a href="http://www.growldesign.co.uk">growldesign</a>
        </div>
    </div>
    
<div id="postTemplate" style="display: none;"> 
	<div class="blogEntry" id="">
		<div class="blogTitle"></div>		
		<div class="blogContent"></div>		
	</div>
	<div class="blogAuthorDate"></div>
	<div class="blogCategories"></div>
</div>

<div id="pageMessageDIV">  
	<p></p>  
</div>
    
<script>

	var postCollection = new PostCollection();
	var firstPageLoaded = false;
	var totalPosts = 0;
	var totalPages = 0;
	var perPage = 10;
	var currentPage = 1;

	// Infinite Scroll
	$(window).scroll(function(){
		
		if ($(window).scrollTop() == $(document).height() - $(window).height()){
			
			currentPage++;
			
			if ( currentPage <= totalPages ) {				
				$("#pageMessageDIV p").html("Page " + currentPage + " of " + totalPages);
				populatePage(currentPage);
			}
        }
	}); 
	
	$("#pageMessageDIV").addFloating({  
		targetBottom: 10,  
		targetLeft: 10,  
		snap: false  
	});		

	$.getJSON('/mongoblog/content/json/posts', function(data) {

		$.each(data, function(key, post) {	
			
	    	var newPostModel = new PostModel();
	    	newPostModel.set({"postId":post.postId});
	    	newPostModel.set({"author":post.author});			
	    	newPostModel.set({"dateCreated":post.dateCreated});
	    	newPostModel.set({"title":post.title});			
	    	newPostModel.set({"content":post.content});
	    	newPostModel.set({"categories":post.categories});
	    	postCollection.add(newPostModel);
	   	    	
	    	if ( key == perPage-1 ) {
	    		if ( !firstPageLoaded ) {
	    			populatePage(currentPage);
	    			firstPageLoaded = true;
	    		}
	    	}	    	
		});	
		
		totalPosts = postCollection.size();	
		totalPages = Math.floor(totalPosts / perPage);
		if ( totalPosts % perPage != 0 ) {
			totalPages = totalPages + 1;
		} 
		
		$("#pageMessageDIV p").html("Page " + currentPage + " of " + totalPages);
	});
	
	$.getJSON('/mongoblog/content/json/categories', function(data) {

		$.each(data, function(key, category) {	

	    	var newLI = $("<li>" + category.categoryName + " (" + category.numberPosts + ")</li>");
	    	$("#categoryUL").append(newLI);

		});	
	});
	
	function populatePage(pageNumber) {

		// Initialize a few vars
		var indexVal = 0;
		var start = startPost(pageNumber);
		var end = endPost(pageNumber);
		
		// Loop through the post collection and append only those that fall in the start/end range
	    postCollection.each(function(post) {

	    	if ( indexVal >= start && indexVal <= end ) {

	    		var newPostContainer = $("<div></div>");
	    		
				$("#postTemplate .blogEntry .blogTitle").html(post.get("title"));
				$("#postTemplate .blogEntry .blogContent").html(post.get("content"));
				$("#postTemplate .blogEntry").attr("id", "blogEntry_"+indexVal);
				$("#postTemplate .blogAuthorDate").html("Entered on " + post.get("dateCreated") + " by " + post.get("author"));
				
				var categories = post.get("categories");
				var blogCats = "";
				for ( index in categories ) {
					blogCats = blogCats + categories[index] + ", ";					
				}
				$("#postTemplate .blogCategories").html("Category: " + blogCats.substring(0, blogCats.length-2));
				
				newPostContainer.html($("#postTemplate").html());
				
				var moreContainer = $('<div class="more" id="more_' + indexVal + '">More...</div>');
				moreContainer.bind('click', function() {					
					var id = $(this).attr("id");
					id = id.substring(5);
					
					var currentHeight = $("#blogEntry_"+id).css("height");
					if ( currentHeight == "115px" ) {
						$("#blogEntry_"+id).css({"height":"auto"});
						$(this).html("Less...");
					} else {
						$("#blogEntry_"+id).css({"height":"115px"});
						$(this).html("More...");
					}
				});
				
				var clearContainer = $('<div class="clearBoth"></div>');
				
				newPostContainer.append(moreContainer);
				newPostContainer.append(clearContainer);

				$("#content").append( newPostContainer );
	    	}
	    	indexVal++;
		});	 
	}	
	
	function startPost(pageNumber) {	
		return (pageNumber * perPage) - perPage;
	}

	function endPost(pageNumber) {	
		return (pageNumber * perPage) - 1;
	}
	
	util = {

	    zeroPad: function (digits, n) {
	        n = n.toString();
	        while (n.length < digits)
	          n = '0' + n;
	        return n;
	    },
	
	    timeString: function (date) {
	        var minutes = date.getMinutes().toString();
	        var hours = date.getHours().toString();
	        return this.zeroPad(2, hours) + ":" + this.zeroPad(2, minutes);
	    }

	}	

</script>    
    
    
</body>
</html>