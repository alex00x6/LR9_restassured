import apis.IssueAPI;
import com.jayway.restassured.http.ContentType;
import fixture.JiraJSONFixture;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RequestSender;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class MyIssue {

    String sessionId = "";
    String IssueKey = "";
    String commentId="";
    String issueSummary="";
    String issueType="";
    String Comment="";








    JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();



    @BeforeTest
    public void login() {

        RequestSender requestSender= new RequestSender();
        long id = Thread.currentThread().getId();
        System.out.println("BeforeTest. Thread id is: " + id);
        requestSender.authenticate();


        sessionId =requestSender.extractResponseByPath("session.value");
        assertNotNull(requestSender.extractResponseByPath("session.value"));
        System.out.println(requestSender.extractResponseByPath("session.value"));


    }

    @Test
    public void DeleteIssue() {
        long id = Thread.currentThread().getId();
        System.out.println("DeleteIssue. Thread id is: " + id);



        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue

        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);
        // delete issue

        IssueKey = issueAPI.extractResponseByPath("key");
        System.out.println(IssueKey);


        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));

        issueAPI.deleteIssue(IssueKey);




    }

    @Test
    public void CreateIssue() {
        long id = Thread.currentThread().getId();
        System.out.println("CreateIssue. Thread id is: " + id);




        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);

       // удалить Issue
       IssueKey = issueAPI.extractResponseByPath("key");

        System.out.println(IssueKey);



       assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));
        issueAPI.deleteIssue(IssueKey);


    }
    @Test
    public void getIssue(){
        long id = Thread.currentThread().getId();
        System.out.println("getIssue. Thread id is: " + id);





        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue




        // получить Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);
        IssueKey = issueAPI.extractResponseByPath("id");

        issueAPI.getSecureIssue(IssueKey);
        System.out.println(IssueKey);
        assertEquals(issueAPI.response.statusCode(), 200);
       assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));




        // delete issue
        IssueKey = issueAPI.extractResponseByPath("id");
        issueAPI.deleteIssue(IssueKey);


    }


    @Test(enabled = false)
    public void editSummary() {
        long id = Thread.currentThread().getId();
        System.out.println("editSummary. Thread id is: " + id);

        //  не меняет самери

        // create issue


        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        IssueAPI issueAPI=new IssueAPI();
        issueAPI.createRequest(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        String editSummary=jiraJSONFixture.generateJSONForEditSummary();
        issueAPI.editSummarySecure(IssueKey, editSummary);

        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));


        issueAPI.deleteIssue(IssueKey);



        // edit summary


     /*   RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = ("{  \n" +
                "   \"update\":{  \n" +
                "      \"summary\":[  \n" +
                "         {  \n" +
                "            \"set\":\"new edit summary\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}");

                put("/rest/api/2/issue/"+IssueKey).then().statusCode(204);*/



    }
    @Test(enabled = false)
    public void changeIssueType(){
        long id = Thread.currentThread().getId();
        System.out.println("changeIssueType. Thread id is: " + id);

        // не меняет тип бага



        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        //change issue type
        String issuetype=jiraJSONFixture.generateJSONForIssueType();
        issueAPI.changeIssueType( IssueKey, issuetype);



        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));

        issueAPI.deleteIssue(IssueKey);








        // change issue type

       /* RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body ="{\n" +
                " \"fields\": \n" +
                " {\n" +
                "  \"issuetype\": {\"id\": \"10003\"}\n" +
                " }\n" +
                "}";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/"+IssueKey).then().statusCode(204);*/




    }
    @Test
    public void searchFilter()
    {  long id = Thread.currentThread().getId();
        System.out.println("searchFilter. Thread id is: " + id);

        // не работает
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        String search=jiraJSONFixture.generateJSONForSearchFilter();
        issueAPI.secureSearch(IssueKey, search);


        assertEquals(issueAPI.response.statusCode(), 200);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));


        issueAPI.deleteIssue(IssueKey);












    }
    @Test(enabled = false)
    public void Assign ()
            // create issue

    { long id = Thread.currentThread().getId();
        System.out.println("Assign. Thread id is: " + id);


        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);


        IssueKey = issueAPI.extractResponseByPath("key");


        String assign = jiraJSONFixture.generateJSONForAssign();
        issueAPI.secureAssign(IssueKey,assign);


        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));


        issueAPI.deleteIssue(IssueKey);






        // assign

      /*  RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body =" {\n" +
                "    \"name\": \"a.a.piluck\"\n" +
                "}";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/"+IssueKey+"/assignee").then().statusCode(204);*/


    }

    @Test
    public void  addComment(){

        long id = Thread.currentThread().getId();
        System.out.println("addComment. Thread id is: " + id);

        // create issue
        String issue =jiraJSONFixture.generateJSONForSampleIssue();

        IssueAPI issueAPI = new IssueAPI();
        issueAPI.createRequest(issue);
        IssueKey = issueAPI.extractResponseByPath("key");


        // add comment
        String comment = jiraJSONFixture.generateJSONForAddComment();
        issueAPI.secureAddComment(IssueKey, comment);
        System.out.println(IssueKey);

        // delete issue

        issueAPI.deleteIssue(IssueKey);

    }











        @Test
        public  void deleteComment(){

            long id = Thread.currentThread().getId();
            System.out.println("deleteComment. Thread id is: " + id);

            /// create issue
            String issue =jiraJSONFixture.generateJSONForSampleIssue();

            IssueAPI issueAPI = new IssueAPI();
            issueAPI.createRequest(issue);
            IssueKey = issueAPI.extractResponseByPath("key");


            // add comment
            String comment = jiraJSONFixture.generateJSONForAddComment();
            issueAPI.secureAddComment(IssueKey, comment);
            System.out.println(IssueKey);

            // delete issue

            issueAPI.secureDeleteIssue(IssueKey);
        }



    }
















