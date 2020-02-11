package com.courtesypoint.apiresearch;

import java.util.List;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;

public class RedmineApi {

	public static void main(String[] args) throws RedmineException {
		String uri = "http://161.202.176.21/redmine/";
	    String apiAccessKey = "38eba4610307518150794019e79d3f8a543c0e2e";
	    String projectKey = "CMS - Java";
	    Integer queryId = null; // any

	    RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
	    IssueManager issueManager = mgr.getIssueManager();
	    List<Issue> issues = issueManager.getIssues(projectKey, queryId);
	    for (Issue issue : issues) {
	        System.out.println(issue.toString());
	    }

	    // Create issue
//	    Issue issueToCreate = IssueFactory.createWithSubject("some subject");
//	    Issue createdIssue = issueManager.createIssue(projectKey , issueToCreate);
//
//	    // Get issue by ID:
//	    Issue issue = issueManager.getIssueById(123);
	}

}
