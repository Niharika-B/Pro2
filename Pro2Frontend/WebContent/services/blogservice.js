/**
 * Blog Service 
 */

app.factory('BlogService',function($http){
	
	var BASE_URL="http://localhost:8080/Pro2Middleware"
	var blogService={}  //user defined name
	
	blogService.saveBlog=function(blog){
	return  $http.post(BASE_URL +"/saveblog",blog)	
	}
	
	return blogService;
	})
	

