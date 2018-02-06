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

  
  RipImagesFromUrl(String aPath)
  {
    this(aPath, true);
  }
  
  RipImagesFromUrl(String aPath, Boolean aOption)
  {
    this.basePath = aPath;

    System.out.println(firstPass(aPath));
  }

  public String curlIt(String aUrl) throws Exception{
    StringBuilder urlBuilder = new StringBuilder(aUrl);
    urlBuilder.append("?alt=json");

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

  String firstPass(String aPath)
  {
    String returnValue= null;
    try{
       curlIt(aPath);
    }
    catch (Exception e) {
    }
    return returnValue;
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
