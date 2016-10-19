package apis;

import utils.RequestSender;

public class IssueAPI extends RequestSender {

    RequestSender requestSender = new RequestSender();

    public void secureCreateIssue(String body) {
        requestSender
                .secureCreateRequest(body)
                .post(ApiUrls.ISSUE.getUri());

    }


    public void deleteIssue(String issueId) {
        requestSender
                .voidCreateRequest()
                .delete(ApiUrls.ISSUE.getUri(issueId));

    }
    public  void secureDeleteIssue(String issueId){
        requestSender
                .secureCreateRequest()
                .delete(ApiUrls.ISSUE.getUri(issueId));
    }

    public void secureAddComment(String issueId, String body) {
        requestSender
                .secureCreateRequest(body)
                .post(ApiUrls.ISSUE.getUri() + "/" + issueId + "/comment");

    }
    public void getSecureIssue (String issueId){
        requestSender
                .secureCreateRequest()
                .get(ApiUrls.ISSUE.getUri(issueId));
    }
    public void editSummarySecure (String issueId, String body){
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId));
    }
    public void secureGetComment(String issueId, String CommentId){
        requestSender
                .secureCreateRequest()
                .get(ApiUrls.ISSUE.getUri()+"/"+issueId+"/comment/"+CommentId);
    }
    public void changeIssueType (String issueId, String body){
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId));

    }
    public void secureSearch (String issueId, String body){
        requestSender
                .secureCreateRequest(body)
                .get(ApiUrls.SEARCH.getUri(issueId));
    }
    public void secureAssign (String issueId, String body){
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId)+"/assignee");

    }



}