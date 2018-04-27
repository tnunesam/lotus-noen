package br.com.inmetrics.neon.config;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.testdroid.api.http.MultipartFormDataContent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class BaseTest {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static int counter;

    public static String uploadFile(String targetAppPath, String serverURL, String testdroid_apikey) throws IOException {
        //final HttpHeaders headers = new HttpHeaders().setBasicAuthentication(testDroidUser, testDroidPassword);
    	final HttpHeaders headers = new HttpHeaders().setBasicAuthentication(testdroid_apikey, "");

        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                        request.setHeaders(headers);
                    }


                });
        MultipartFormDataContent multipartContent = new MultipartFormDataContent();
        FileContent fileContent = new FileContent("application/octet-stream", new File(targetAppPath));

        MultipartFormDataContent.Part filePart = new MultipartFormDataContent.Part("file", fileContent);
        multipartContent.addPart(filePart);

        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(serverURL+"/upload"), multipartContent);
        request.setConnectTimeout(20000);
        HttpResponse response = request.execute();
        System.out.println("response:" + response.parseAsString());

        AppiumResponse appiumResponse = request.execute().parseAs(AppiumResponse.class);
        System.out.println("File id:" + appiumResponse.uploadStatus.fileInfo.file);

        return appiumResponse.uploadStatus.fileInfo.file;

    }

    public static void takeScreenshot(String screenshotName, WebDriver driver) {
    	
        counter = counter + 1;
        String fullFileName = System.getProperty("user.dir") + "/Screenshots/" + getScreenshotsCounter() + "_" + screenshotName + ".png";

        screenshot(fullFileName, driver);
    }

    private static File screenshot(String name, WebDriver driver) {
        System.out.println("Taking screenshot...");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            File testScreenshot = new File(name);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getScreenshotsCounter() {
        if (counter < 10) {
            return "0" + counter;
        } else {
            return String.valueOf(counter);
        }
    }

    public static class AppiumResponse {
        Integer status;
        @Key("sessionId")
        String sessionId;

        @Key("value")
        BaseTest.UploadStatus uploadStatus;

    }

    public static class UploadedFile {
        @Key("file")
        String file;
    }

    public static class UploadStatus {
        @Key("message")
        String message;
        @Key("uploadCount")
        Integer uploadCount;
        @Key("expiresIn")
        Integer expiresIn;
        @Key("uploads")
        BaseTest.UploadedFile fileInfo;
    }
}