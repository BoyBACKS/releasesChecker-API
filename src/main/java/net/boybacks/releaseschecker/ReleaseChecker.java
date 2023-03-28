package net.boybacks.releaseschecker;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;

public class ReleaseChecker {

  public static String version;
  public static String repositoryURL;

  public static boolean releaseChecker() {
    if (version == null) {
      System.out.println("[ERROR] Assign a version using getVersion(). Setting default value as FALSE.");
      return false;
    }
    if (repositoryURL == null) {
      System.out.println("[ERROR] Assign a repository URL using getRepository(). Setting default value as FALSE.");
      return false;
    }
    try {
      URL url = new URL("https://api.github.com/repos/" + repositoryURL + "/releases");
      JSONParser parser = new JSONParser();
      JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(url.openStream()));
      JSONObject json = (JSONObject) jsonArray.get(0);
      String repoVersion = json.get("tag_name").toString();
      return repoVersion.equalsIgnoreCase(version);
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static String getVersion(String releaseVersion) {
    return version = releaseVersion;
  }

  public static String getRepository(String author, String repositoryName) {
    return repositoryURL = author + "/" + repositoryName;
  }

//Here you can see example release checker!

//  public static void main(String[] args) {
//    getVersion("v2.0");
//    getRepository("boybacks", "xorencrypt");
//    if (releaseChecker()) {
//      System.out.println("There is no new versions");
//    }
//    else {
//      System.out.println("There is new version");
//    }
//  }

}
