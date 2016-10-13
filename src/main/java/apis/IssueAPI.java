package apis;

import utils.RequestSender;

public class IssueAPI extends RequestSender {

    RequestSender requestSender = new RequestSender();

    public void createIssue(String body) {
        requestSender
                .createRequest(body)
                .post(ApiUrls.ISSUE.getUri());

    }

    public void deleteIssue(String issueId) {
        requestSender
                .createRequest()
                .delete(ApiUrls.ISSUE.getUri(issueId));

    }

    public void addComment(String issueId, String body) {
        requestSender
                .createRequest(body)
                .post(ApiUrls.ISSUE.getUri() + "/" + issueId + "/comment");

    }
    public void getIssue (String issueId){
        requestSender
                .createRequest()
                .get(ApiUrls.ISSUE.getUri(issueId));
    }

}