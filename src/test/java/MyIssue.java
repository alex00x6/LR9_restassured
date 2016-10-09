import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Comment;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.sessionId;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by Kate on 26.09.2016.
 */
public class MyIssue {
       String sessionId = "";
    String IssueKey = "";
    String commentId="";
    String issueSummary="";
    String issueType="";
    String Comment="";





    @BeforeTest
    public void login() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis484)\"\n" +
                "} ";
        sessionId = given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                extract().
                path("session.value");
    }

    @Test
    public void DeleteIssue() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
        // создание Issue

        IssueKey = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("key");
        System.out.println(IssueKey);


        // удалить issue

        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);

    }

    @Test
    public void CreateIssue() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

        IssueKey = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("key");
        System.out.println(IssueKey);

        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);
    }
    @Test
    public void getIssue(){



        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
        // создание Issue

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


        // получить Issue


       Response response= given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                get("/rest/api/2/issue/" + IssueKey);
        assertTrue(response.getStatusCode() == 200);


        System.out.println(response.asString());
        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);

    }


    @Test
    public void editSummary() {

        // create issue

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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

        // edit summary

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = ("{  \n" +
                "   \"update\":{  \n" +
                "      \"summary\":[  \n" +
                "         {  \n" +
                "            \"set\":\"new edit summary\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}");
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/"+IssueKey).then().statusCode(204);

       /* // get summary
        issueSummary = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(b).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("summary");
        System.out.println(issueSummary);

        // remove summary
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String bo = ("{  \n" +
                "\" +\n" +
                "                \"   \\\"update\\\":{  \\n\" +\n" +
                "                \"      \\\"summary\\\":[  \\n\" +\n" +
                "                \"         {  \\n\" +\n" +
                "                \"            \\\"remove\\\":\\\"new edit summary\\\"\\n\" +\n" +
                "                \"         }\\n\" +\n" +
                "                \"      ]\\n\" +\n" +
                "                \"   }\\n\" +\n" +
                "                \"}");
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(bo).
                when().
                delete("/rest/api/2/issue/"+IssueKey).then().statusCode(204);*/


        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);

    }
    @Test
    public void changeIssueType(){
        // create issue

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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


        // change issue type

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
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
                put("/rest/api/2/issue/"+IssueKey).then().statusCode(204);

       /* // get issue type
        issueType = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(b).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("issuetype");
        System.out.println(issueType);*/

        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);


    }
    @Test
    public void searchFilter()
    {  // create issue
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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


        // search filter

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
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
                post("/rest/api/2/search");
        assertTrue(response.getStatusCode()==200);

        System.out.println(response.asString());

        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);



    }
    @Test
    public void Assign ()
            // create issue
    {

        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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

        // assign

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body =" {\n" +
                "    \"name\": \"a.a.piluck\"\n" +
                "}";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/"+IssueKey+"/assignee").then().statusCode(204);

        // delete issue
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);


    }
    @Test
    public void negativeTestLogin(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String b = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis484)\"\n" +
                "} ";
        sessionId = given().
                header("Content-Type", "application/json").
                body(b).
                when().
                post("/rest/auth/1/session").
                then().
                extract().
                path("session.value");

        // negativeTestLogin


        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis485)\"\n" +
                "} ";
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().statusCode(401);

    }
    @Test
    public void  addComment(){
        // create issue
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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

        // удаление комментария
       RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";


        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/"+IssueKey+"/comment/"+commentId).then().statusCode(204);

        // удаление issue

        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);


    }





    @Test
    public  void  getComment() {

        // create issue
        String b = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";

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

        /*//get comment

        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";



              Comment=  given().
                        header("Cookie", "JSESSIONID=" + sessionId).
                        header("Content-Type", "application/json").
                        when().
                        get("/rest/api/2/issue/"+IssueKey+"/comment/"+commentId).then().statusCode(201).extract().path("body");
        System.out.println(Comment);*/


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
                delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);




    }





        @Test
        public  void deleteComment(){
            // create issue
            String b = "{\n" +
                    "\n" +
                    "\t\"fields\": {\n" +
                    "\t\t\"project\": {\n" +
                    "\t\t\t\"id\": \"10315\"\n" +
                    "\t\t},\n" +
                    "\t\t\"summary\": \"RESTAssured summary\",\n" +
                    "\t\t\"issuetype\": {\n" +
                    "\t\t\t\"id\": \"10004\"\n" +
                    "\t\t},\n" +
                    "\t\t\"assignee\": {\n" +
                    "\t\t\t\"name\": \"katherinebilous\"\n" +
                    "\t\t},\n" +
                    "\t\t\"reporter\": {\n" +
                    "\t\t\t\"name\": \"katherinebilous\"\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "}\n";

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



            // delete comment
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
                    delete("/rest/api/2/issue/" + IssueKey).then().statusCode(204);







        }
  /*  @Test
    public void removeSummary(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String bo = ("{\n" +
                "    \"update\" : {\n" +
                "        \"components\" : [{\"remove\" : {\"summary\" : \"RESTAssured summary\"}}]\n" +
                "    }\n" +
                "}");
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(bo).
                when().
                put("/rest/api/2/issue/QAAUT-411").then().statusCode(204);*/
    }
















