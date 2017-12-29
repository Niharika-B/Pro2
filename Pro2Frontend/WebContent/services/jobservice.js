/**
 * JobService
 */

app.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("http://localhost:8080/Pro2Middleware/savejob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8080/Pro2Middleware/getalljobs")
	}
	
	jobService.getJobDetails=function(id){
		return $http.get("http://localhost:8080/Pro2Middleware/getjobbyid/"+id)
	}
	
	return jobService;
})