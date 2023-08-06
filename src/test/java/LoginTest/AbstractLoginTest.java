package LoginTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class AbstractTest {

    private static InputStream configFile;
    private static Properties prop = new Properties();
    private static String baseURL;
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;


    @BeforeAll

    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        baseURL = prop.getProperty("base_url");

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(10000L))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .setContentType("multipart/form-data")
                .addMultiPart("username", "ebb5d22")
                .addMultiPart("password", "07172d43f1")
                .build();

    }

    public static String getURL() {
        return baseURL + "gateway/login";
    }

}
