package fixture;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JiraJSONFixture {


    public String generateJSONForLogin() {
        JSONObject credentials = new JSONObject();
        credentials.put("username", "katherinebilous");
        credentials.put("password", "Polis484)");

        return credentials.toJSONString();
    }
    public String generateJSONForSampleIssue() {
        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject issuetype =new JSONObject();
        JSONObject assignee = new JSONObject();
        JSONObject reporter = new JSONObject();

        fields.put("summary", "RESTAssured summary");
        project.put("id", "10315");
        issuetype.put("id","10004");
        assignee.put("name","katherinebilous");
        reporter.put("name","katherinebilous");
        issue.put("username", "katherinebilous");
        issue.put("password", "Polis484)");
        issue.put("fields", fields);
        fields.put("project",project);
        fields.put("issuetype", issuetype);
        fields.put("assignee", assignee);
        fields.put("reporter", reporter);

        return issue.toJSONString();
    }
    public String generateJSONForEditSummary(){

        JSONObject update =new JSONObject();
        JSONArray summary=new JSONArray();
        update.put("summary","new edit summary");
         return update.toJSONString();
    }
    public String geneerateJSONForIssueType(){
        JSONObject fields= new JSONObject();
        JSONObject issuetype = new JSONObject();
        fields.put("issuetype", issuetype);
        issuetype.put("id", "10003");
        return fields.toJSONString();

    }
    public String generateJSONForSearchFilter(){
        JSONObject bodyFilterValue=new JSONObject();
        JSONArray fields=new JSONArray();

        bodyFilterValue.put("jql", " project = QAAUT");
        bodyFilterValue.put("startAt","0");
        bodyFilterValue.put("maxResults", " 15");
        fields.add("summary");
        fields.add( "status");
        fields.add("assignee");
        return bodyFilterValue.toJSONString();
    }
    public String generateJSONForAddComment(){
        JSONObject addCommentValue=new JSONObject();
        addCommentValue.put("body", "This is a comment regarding the quality of the response.");
        return addCommentValue.toJSONString();
    }

}
