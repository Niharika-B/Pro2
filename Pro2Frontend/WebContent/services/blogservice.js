/**
 * Blog Service 
 */

app.factory('BlogService',function($http){
	
	var BASE_URL="http://localhost:8080/Pro2Middleware"
		var blogService={}
	
	blogService.saveBlog=function(blog){
	return  $http.post(BASE_URL +"/saveblog",blog)	
	}
	blogService.getBlogsApproved=function(){
		return $http.get(BASE_URL + "/getblogs/"+1)
	}
	
	//select * from blogpost where approved=0
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL + "/getblogs/"+0)
	}
	
	blogService.getBlogPost=function(id){
		return $http.get(BASE_URL + "/getblog/"+id)
	}
	
	blogService.updateBlogPost=function(blogPost,rejectionReason){
		if(rejectionReason==undefined)
		return $http.put(BASE_URL + "/updateapprovalstatus?rejectionReason="+'Not Mentioned',blogPost)
		else
			return $http.put(BASE_URL + "/updateapprovalstatus?rejectionReason="+rejectionReason,blogPost)
	}
	blogService.userlikes=function(id){
		return $http.get(BASE_URL + "/userlikes/" + id)
	} 
	
	 blogService.updatelikes=function(blogPost){
	    	return $http.put(BASE_URL + "/updatelikes",blog);
	    }
	   
	 blogService.addComment=function(commentedText,id){
		 return $http.post(BASE_URL + "/addcomment?commentedText="+commentedText+'&id='+id)
	 }

	return blogService;
	})
	

