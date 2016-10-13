package fixture;

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
}
