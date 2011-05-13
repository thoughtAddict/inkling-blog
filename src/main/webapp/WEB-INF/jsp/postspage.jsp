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
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
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
                <ul>
                    <li>Category 1</li>
                    <li>Category 2</li>
                    <li>Category 3</li>
                    <li>Category 4</li>
                    <li>Category 5</li>
                    <li>Category 6</li>
                </ul>
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
        <div id="content">
        
<c:forEach items="${posts}" var="post">
            <div>
                <h3 id="idea" title="${post.title}">${post.title}</h3>
                <p>${post.content}</p>
            </div>
</c:forEach>        
        
        </div>
        <div id="footer">
            Copyright &copy; 2005 Company. All Rights Reserved.<br />
            <!-- If you would like to use this template, I ask that you keep the following line of code intact -->
            Design by <a href="http://www.growldesign.co.uk">growldesign</a>
        </div>
    </div>
</body>
</html>