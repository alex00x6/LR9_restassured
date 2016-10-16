import apis.ApiUrls;
import apis.IssueAPI;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import fixture.JiraJSONFixture;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Comment;
import utils.RequestSender;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.sessionId;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


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
        requestSender.authenticate();


        sessionId =requestSender.extractResponseByPath("session.value");

        assertNotNull(requestSender.extractResponseByPath("session.value"));
        System.out.println(requestSender.extractResponseByPath("session.value"));


    }

    @Test
    public void DeleteIssue() {



        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue

        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        // delete issue

        IssueKey = issueAPI.extractResponseByPath("id");
        System.out.println(IssueKey);
        issueAPI.secureDeleteIssue(IssueKey);


        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));





    }

    @Test
    public void CreateIssue() {


        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);

       // удалить Issue
       IssueKey = issueAPI.extractResponseByPath("key");
        System.out.println(IssueKey);
       issueAPI.secureDeleteIssue(IssueKey);


        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));



    }
    @Test
    public void getIssue(){




       String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue




        // получить Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("id");

        issueAPI.getSecureIssue(IssueKey);
        System.out.println(IssueKey);
        assertEquals(issueAPI.response.statusCode(), 200);
       assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));




        // delete issue
        IssueKey = issueAPI.extractResponseByPath("id");
        issueAPI.deleteIssue(IssueKey);


    }


    @Test()
    public void editSummary() {
        //  не меняет самери

        // create issue


        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        IssueAPI issueAPI=new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        String editSummary=jiraJSONFixture.generateJSONForEditSummary();
        issueAPI.editSummarySecure(IssueKey, editSummary);

        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));



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
    @Test
    public void changeIssueType(){
        // не меняет тип бага



        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        //change issue type
        String issuetype=jiraJSONFixture.generateJSONForIssueType();
        issueAPI.changeIssueType( IssueKey, issuetype);



        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));








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
    { // не работает
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("key");

        String search=jiraJSONFixture.generateJSONForSearchFilter();
        issueAPI.secureSearch(IssueKey, search);


        assertEquals(issueAPI.response.statusCode(), 200);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));









        // search filter

      /*  RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body =" {\n" +
                "    \"jql\": \"project = QAAUT\",\n" +
                "    \"startAt\": 0,\n" +
                "    \"maxResults\": 15,\n" +
                "    \"fields\": [\n" +
                "        \"summary\",\n" +
                "        \"status\",\n" +
                "        \"assignee\"\n" +
                "    ]\n" +
                "}";
        Response response= given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/search");*/




    }
    @Test
    public void Assign ()
            // create issue

    {

        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);


        IssueKey = issueAPI.extractResponseByPath("key");


        String assign = jiraJSONFixture.generateJSONForAssign();
        issueAPI.secureAssign(IssueKey,assign);


        assertEquals(issueAPI.response.statusCode(), 204);
        assertTrue(issueAPI.response.contentType().contains(ContentType.JSON.toString()));






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
        // create issue
        String issue =jiraJSONFixture.generateJSONForSampleIssue();

        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("key");


        // add comment
        String comment = jiraJSONFixture.generateJSONForAddComment();
        issueAPI.secureAddComment(IssueKey, comment);
        System.out.println(IssueKey);

        // delete issue

        issueAPI.secureDeleteIssue(IssueKey);

    }





    @Test
    public  void  getComment() {
        // create issue
       /* String issue =jiraJSONFixture.generateJSONForSampleIssue();

        IssueAPI issueAPI = new IssueAPI();
        issueAPI.secureCreateIssue(issue);
        IssueKey = issueAPI.extractResponseByPath("key");


        // add comment
        String comment = jiraJSONFixture.generateJSONForAddComment();
        issueAPI.secureAddComment(IssueKey, comment);
        commentId=issueAPI.extractResponseByPath("id");

        // get comment

        System.out.println(commentId);
        issueAPI.secureGetComment(commentId);


        // create issue
        String b = jiraJSONFixture.generateJSONForSampleIssue();
        IssueKey = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(b).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("key");
        System.out.println(IssueKey);

        // add comment

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body ="{\n" +
                "    \"body\": \"This is a comment regarding the quality of the response.\"\n" +
                "}";


        commentId= given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("rest/api/2/issue/"+IssueKey+"/comment").then().statusCode(201).extract().path("id");
        // assertTrue(response.getStatusCode()==201);
        System.out.println(commentId);

        //get comment

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";



              Comment=  given().
                        header("Cookie", "JSESSIONID=" + sessionId).
                        header("Content-Type", "application/json").
                        when().
                        get("/rest/api/2/issue/"+IssueKey+"/comment/"+commentId).then().statusCode(201).extract().path("body");
        System.out.println(Comment);


        //delete comment
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";


        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/"+IssueKey+"/comment/"+commentId).then().statusCode(204);

        // delete issue

        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);*/




    }





        @Test
        public  void deleteComment(){
            /// create issue
            String issue =jiraJSONFixture.generateJSONForSampleIssue();

            IssueAPI issueAPI = new IssueAPI();
            issueAPI.secureCreateIssue(issue);
            IssueKey = issueAPI.extractResponseByPath("key");


            // add comment
            String comment = jiraJSONFixture.generateJSONForAddComment();
            issueAPI.secureAddComment(IssueKey, comment);
            System.out.println(IssueKey);

            // delete issue

            issueAPI.secureDeleteIssue(IssueKey);
        }

    }
















