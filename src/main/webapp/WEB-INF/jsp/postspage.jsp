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
    
    <link href="../css/style.css?version=1.0.3" rel="stylesheet" type="text/css" />
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
            <div id="categoryList">
                <h3 title="Categories">Categories</h3>
                <div class="categoryDIV">
                	<input id="category_0" type="checkbox" class="categoryCheckBoxAll" value="all" checked="checked"/>
                	<label for="category_0" class="categoryLabelAll">ALL</label>
                </div>  
            </div>
            <div id="archiveList">
                <h3 title="Archives">Archives</h3>
                <div class="archiveDIV">
                	<input id="archive_0" type="checkbox" class="archiveCheckBoxAll" value="all" checked="checked"/>
                	<label for="archive_0" class="archiveLabelAll">ALL</label>
                </div>                 
            </div>
            <div id="linksList">
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

	$.ajaxSetup({cache: false});

	var postCollection = new PostCollection();
	var filterCollection = new PostCollection();
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
	
	$(".categoryCheckBoxAll").click(function() {
		$(".categoryCheckBox").prop("checked", false);
		filterPosts();
	});
	
	$(".archiveCheckBoxAll").click(function() {
		$(".archiveCheckBox").prop("checked", false);
		filterPosts();
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
	    	filterCollection.add(newPostModel);
	   	    	
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

		var i = 1;
		$.each(data, function(key, category) {	
			
	    	var newDIV = $("<div class=\"categoryDIV\"></div>");
	    	var newBox = $("<input id=\"category_" + i + "\" type=\"checkbox\" class=\"categoryCheckBox\" value=\"" + category.categoryName + "\"/>");
	    	var newLbl = $("<label for=\"category_" + i + "\" class=\"categoryLabel\">" + category.categoryName + " (" + category.numberPosts + ")</label>");
	    	newBox.bind('click', function() {
				$(".categoryCheckBoxAll").prop("checked", false);
				filterPosts();
			});
	    	
	    	newDIV.append(newBox);
	    	newDIV.append(newLbl);	    	
	    	$("#categoryList").append(newDIV);
	    	i++;
		});	
	});
	
	$.getJSON('/mongoblog/content/json/archives', function(data) {

		var i = 1;
		$.each(data, function(key, archive) {	

	    	var newDIV = $("<div class=\"archiveDIV\"></div>");
	    	var newBox = $("<input id=\"archive_" + i + "\" type=\"checkbox\" class=\"archiveCheckBox\" value=\"" + archive.archiveYear + "_" + archive.archiveMonth + "\"/>");
	    	var newLbl = $("<label for=\"archive_" + i + "\" class=\"archiveLabel\">" + archive.archiveName + " (" + archive.numberPosts + ")</label>");
	    	newBox.bind('click', function() {
	    		$(".archiveCheckBoxAll").prop("checked", false);
	    		filterPosts();
			});
	    	
	    	newDIV.append(newBox);
	    	newDIV.append(newLbl);	    	
	    	$("#archiveList").append(newDIV);
	    	i++;	    	
		});	
	});	
	
	function populatePage(pageNumber) {

		// Initialize a few vars
		var indexVal = 0;
		var start = startPost(pageNumber);
		var end = endPost(pageNumber);
		
		// Loop through the post collection and append only those that fall in the start/end range
	    filterCollection.each(function(post) {

	    	if ( indexVal >= start && indexVal <= end ) {

	    		var newPostContainer = $("<div></div>");
	    		
	    		var test = post.get("dateCreated").substring(6,10) + "_" + post.get("dateCreated").substring(0,2);
	    		
				$("#postTemplate .blogEntry .blogTitle").html(post.get("title") + " : " + test);
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
	
	function filterPosts() {
		
		var filterArray;	
		
		var categoryArray = [];
		$(".categoryCheckBox").each(function() {
			if ( $(this).is(":checked") ) {
				categoryArray.push($(this).val());
			}
		});
		
		var archiveArray = [];
		$(".archiveCheckBox").each(function() {
			if ( $(this).is(":checked") ) {
				archiveArray.push($(this).val());
			}
		});		

		if ( categoryArray.length == 0 ) {
			$(".categoryCheckBoxAll").prop("checked", true);
		}
		
		if ( archiveArray.length == 0 ) {
			$(".archiveCheckBoxAll").prop("checked", true);
		}		
		
		var allCategories = $(".categoryCheckBoxAll").is(":checked");
		var allArchives   = $(".archiveCheckBoxAll").is(":checked");
	
		if ( allCategories && allArchives ) {
			
			filterArray = postCollection.filter(function(post) {
				return true;
			});	
			
		} else if ( allCategories && !allArchives ) {
		
			filterArray = postCollection.filter(function(post) {
				return $.inArray(post.get("dateCreated").substring(6,10) + "_" + post.get("dateCreated").substring(0,2), archiveArray) > -1;
			});	
	
		} else if ( !allCategories && allArchives ) {

			filterArray = postCollection.filter(function(post) {
				return _.intersect(post.get("categories"), categoryArray).length > 0;
			});	

		} else {

			filterArray = postCollection.filter(function(post) {
				return _.intersect(post.get("categories"), categoryArray).length > 0 &&
					   $.inArray(post.get("dateCreated").substring(6,10) + "_" + post.get("dateCreated").substring(0,2), archiveArray) > -1;
			});	
			
		}
	
		var filterSize = filterArray.length;

		if ( filterSize > 0 ) {
		
			// Reinit the collection
			filterCollection = new PostCollection();
			
			// Recalculate the "page" message
			currentPage = 1;
			totalNumberOfPages = Math.floor(filterSize / perPage);
			totalPages = totalNumberOfPages;
			if ( filterSize % perPage != 0 ) {
				totalNumberOfPages = totalNumberOfPages + 1;
			}
			$("#pageMessageDIV p").html("Page " + currentPage + " of " + totalNumberOfPages);
			
			// From Array to List, so we can use the Backbone/Underscore "each"
			for (var i in filterArray) {		
				filterCollection.add(filterArray[i]);
			}
			
			$("#content").html("");
			populatePage(1);
			
		} else {		
			$("#content").html('<div class="emptyFilter">There are no Posts that match your criteria.</div>');		
		}
	}	

</script>    
    
    
</body>
</html>