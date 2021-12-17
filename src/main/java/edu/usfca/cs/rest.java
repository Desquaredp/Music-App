package edu.usfca.cs;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class rest {


    /** TheAudioDB uses a REST interface, accepting URLs as input and returning JSON. Here's an example of
     * how to fetch info about The Beatles. (of course.)
     * TheAudioDB's API is described here. If you pay $3, you can get access to all their Patreon services; the free
     * ones are fine for this assignment.
     */
    public static void artistAudioDB() {
        String requestURL = "https://www.theaudiodb.com/api/v1/json/2/search.php?s=";
        String artist = "ed sheeran";
        StringBuilder response = new StringBuilder();
        URL u;
        try {
            u = new URL(requestURL + artist);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
            return;
        }
        try {
            URLConnection connection = u.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int code = httpConnection.getResponseCode();

            String message = httpConnection.getResponseMessage();
            System.out.println(code + " " + message);
            if (code != HttpURLConnection.HTTP_OK) {
                return;
            }
            InputStream instream = connection.getInputStream();
            Scanner in = new Scanner(instream);
            while (in.hasNextLine()) {
                response.append(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading response");
            return;
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response.toString());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray artists = (JSONArray)jsonObject.get("artists"); // get the list of all artists returned.
            JSONObject beatles =(JSONObject) artists.get(0);  // I happen to know that the beatles is the first entry. Otherwise I'd iterate.
            String mood = (String)beatles.get("strMood");
            System.out.println("Mood: " + mood);
            String bio = (String)beatles.get("strBiographyEN");
            System.out.println("Biography: " + bio);

        } catch(ParseException e) {
            System.out.println("Error parsing JSON");
            return;
        }

    }

    public static void main(String[] args) {
        artistAudioDB();

    }

}

