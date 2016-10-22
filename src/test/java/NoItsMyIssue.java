import apis.IssueAPI;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import fixture.JiraJSONFixture;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RequestSender;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyIssue {


    @BeforeTest
    public void login() {

        RequestSender requestSender= new RequestSender();
        long id = Thread.currentThread().getId();
        System.out.println("BeforeTest. Thread id is: " + id);
        requestSender.authenticate();


        //sessionId =requestSender.extractResponseByPath("session.value");
       // assertNotNull(requestSender.extractResponseByPath("session.value"));
       // System.out.println(requestSender.extractResponseByPath("session.value"));


    }

    @Test
    public void DeleteIssue() {
        long id = Thread.currentThread().getId();
        System.out.println("DeleteIssue. Thread id is: " + id);


        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue

        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.secureCreateIssue(issue);

        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);


        // delete issue

        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));






    }

    @Test
    public void CreateIssue() {
        long id = Thread.currentThread().getId();
        System.out.println("CreateIssue. Thread id is: " + id);



        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // cоздать Issue
        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);

       // удалить Issue

        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));



    }
    @Test
    public void getIssue(){
        long id = Thread.currentThread().getId();
        System.out.println("getIssue. Thread id is: " + id);




        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // создание Issue

        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String  IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);


        // получить Issue

       Response  getissue= issueAPI.getSecureIssue(IssueKey);
        System.out.println(IssueKey);
        assertEquals(getissue.statusCode(), 200);
        assertTrue(getissue.contentType().contains(ContentType.JSON.toString()));


        // delete issue
        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));





    }


    @Test(enabled = false)
    public void editSummary() {
        long id = Thread.currentThread().getId();
        System.out.println("editSummary. Thread id is: " + id);
        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        //  не меняет самери

        // create issue
        String issue = jiraJSONFixture.generateJSONForSampleIssue();
        // create issue


        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);

        // edit summary

        String editSummary=jiraJSONFixture.generateJSONForEditSummary();
        Response newSummary = issueAPI.editSummary(IssueKey, editSummary);

        assertEquals(newSummary.statusCode(), 204);
        assertTrue(newSummary.contentType().contains(ContentType.JSON.toString()));

        // delete issue


        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));







    }
    @Test(enabled = false)
    public void changeIssueType(){
        long id = Thread.currentThread().getId();
        System.out.println("changeIssueType. Thread id is: " + id);

        // не меняет тип бага


        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // create Issue
        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.secureCreateIssue(issue);

        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);



        //change issue type
        String issuetype=jiraJSONFixture.generateJSONForIssueType();

       Response changeIssueType= issueAPI.changeIssueType( IssueKey, issuetype);

        assertEquals(changeIssueType.statusCode(), 204);
        assertTrue(changeIssueType.contentType().contains(ContentType.JSON.toString()));

        // delete issue



        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));




    }
    @Test
    public void searchFilter()
    {  long id = Thread.currentThread().getId();
        System.out.println("searchFilter. Thread id is: " + id);

        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // create Issue
        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);

        // search filter

       String search=jiraJSONFixture.generateJSONForSearchFilter();

       Response searchFilter = issueAPI.search(IssueKey, search);


        assertEquals(searchFilter.statusCode(), 200);
       assertTrue(searchFilter.contentType().contains(ContentType.JSON.toString()));

        // delete issue
        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        //assertEquals(deleteIssueResponse.statusCode(), 204);
       // assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));

    }
    @Test(enabled = false)
    public void Assign ()
            // create issue

    { long id = Thread.currentThread().getId();
        System.out.println("Assign. Thread id is: " + id);

        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        String issue = jiraJSONFixture.generateJSONForSampleIssue();

        // create Issue

        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);

        // assign


        String assign = jiraJSONFixture.generateJSONForAssign();
       Response createAssign = issueAPI.assign(IssueKey,assign);


        assertEquals(createAssign.statusCode(), 204);
        assertTrue(createAssign.contentType().contains(ContentType.JSON.toString()));

        // delete issue


        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));

    }

    @Test
    public void  addComment(){

        long id = Thread.currentThread().getId();
        System.out.println("addComment. Thread id is: " + id);
        JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
        // create issue
        String issue =jiraJSONFixture.generateJSONForSampleIssue();

        IssueAPI issueAPI = new IssueAPI();
        Response createIssueResponse= issueAPI.createIssue(issue);
        String IssueKey = createIssueResponse.then().extract().path("key");
        System.out.println(IssueKey);

        // add comment
        String comment = jiraJSONFixture.generateJSONForAddComment();


        Response addcomment = issueAPI.addComment(IssueKey, comment);
        assertEquals(addcomment.statusCode(), 201);
        assertTrue(addcomment.contentType().contains(ContentType.JSON.toString()));


        // delete issue

        Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
        assertEquals(deleteIssueResponse.statusCode(), 204);
        assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));

    }











        @Test
        public  void deleteComment(){

            long id = Thread.currentThread().getId();
            System.out.println("deleteComment. Thread id is: " + id);
            JiraJSONFixture jiraJSONFixture=new JiraJSONFixture();
            /// create issue
            String issue =jiraJSONFixture.generateJSONForSampleIssue();

            IssueAPI issueAPI = new IssueAPI();
            Response createIssueResponse= issueAPI.createIssue(issue);
            String IssueKey = createIssueResponse.then().extract().path("key");
            System.out.println(IssueKey);


            // add comment
            String comment = jiraJSONFixture.generateJSONForAddComment();

            Response addcomment = issueAPI.addComment(IssueKey, comment);
            assertEquals(addcomment.statusCode(), 201);
            assertTrue(addcomment.contentType().contains(ContentType.JSON.toString()));

            // delete comment
            Response deletecomment = issueAPI.deleteComment(IssueKey);

            // delete issue

            Response deleteIssueResponse = issueAPI.deleteIssue(IssueKey);
            assertEquals(deleteIssueResponse.statusCode(), 204);
            assertTrue(deleteIssueResponse.contentType().contains(ContentType.JSON.toString()));
        }



    }

