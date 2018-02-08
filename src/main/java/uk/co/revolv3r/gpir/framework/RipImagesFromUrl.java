package uk.co.revolv3r.gpir.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class RipImagesFromUrl
{
  private final String USER_AGENT = "Mozilla/5.0";

  private static final String PASS1_PATTERN = " id=\"%s(.*?)%s\" ";
  private static final String PASS2_PATTERN = " id=\"%s(.*?)%s\" ";
  String basePath = null;
  private String output = null;
  
  RipImagesFromUrl(String aPath, String aUserId)
  {
    output = getProfilePage(aPath+ aUserId);

    Set<String> matches = getAlbumUrls(output, aUserId);

    for (String item : matches)
    {
      System.out.println(item);
    }
  }

  private Set<String> getAlbumUrls(String output, String userId) {
    String regex = userId+ "/albumid/(.*?)\\?alt=json";

    Set<String> allMatches = new HashSet<>();
    Matcher m = Pattern.compile(regex)
            .matcher(output);
    while (m.find()) {
      if(!allMatches.contains(m.group()))
        allMatches.add(m.group());
    }
    return allMatches;
  }

//  RipImagesFromUrl(String aPath, Boolean aOption)
//  {
//    this.basePath = aPath;
//
//    output = firstPass(aPath);
//
//
//  }

  public String curlIt(String aUrl, String aUrlPrefix, String aUrlSuffix, String aRegex) throws Exception{
    StringBuilder urlBuilder = new StringBuilder((aUrlPrefix!=null)?aUrlPrefix+aUrl : aUrl);
    urlBuilder.append(aUrlSuffix);

    URL obj = new URL(urlBuilder.toString());

    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", USER_AGENT);
    //connection.setRequestProperty("Accept-Charset", "UTF-8");

    System.out.println("\nSending request to URL : " + urlBuilder);
    System.out.println("Response Code : " + con.getResponseCode());
    System.out.println("Response Message : " + con.getResponseMessage());

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String line;
    StringBuffer response = new StringBuffer();

    while ((line = in.readLine()) != null) {

      response.append(line);
    }
    in.close();

    return response.toString();
  }

  String getProfilePage(String aPath)
  {
    String returnValue= null;
    try{
       returnValue= curlIt(aPath, null, "?alt=json", null);
    }
    catch (Exception e) {
      returnValue = "Failed grab: " + e.getMessage();
    }
    return returnValue;
  }

  public String getOutput() {
    return output;
  }

//  String secondPass(String xml, Collection<int[]> idMaps)
//  {
//    for (int[] idMap : idMaps)
//    {
//      final String current = prefix + StringUtils.leftPad(String.valueOf(idMap[0]),padding,'0') + suffix;
//      final char[] chars = new char[padding];
//      {
//        final String paddedNumber = StringUtils.leftPad(String.valueOf(idMap[1]), padding, '0');
//        int i = 0;
//        for (Character integer : paddedNumber.toCharArray())
//          chars[i++] = CHARS.get(Integer.parseInt(integer.toString()));
//      }
//      final String replacement = new String(chars);
//      xml = xml.replace(current,String.format(tempPattern,replacement));
//    }
//    return xml;
//  }
//
//
//  String thirdPass(String xml)
//  {
//    final Matcher matcher = Pattern.compile(String.format(tempPattern, "(.*?)")).matcher(xml);
//
//    while (matcher.find())
//    {
//      final StringBuilder sb = new StringBuilder(prefix);
//      for (char c : matcher.group(1).toCharArray())
//        sb.append(CHARS.indexOf(c));
//      sb.append(suffix);
//      xml = xml.replace(matcher.group(), sb.toString());
//    }
//    return xml;
//  }
}
