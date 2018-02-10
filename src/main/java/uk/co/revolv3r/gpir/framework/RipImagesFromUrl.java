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

class RipImagesFromUrl
{
  private final String USER_AGENT = "Mozilla/5.0";

  private Set<String> mAlbums = new HashSet<>();

  /**
   * Rip Image from URL
   * @param aPath base url
   * @param aUserId specific user id
   */
  RipImagesFromUrl(String aPath, String aUserId)
  {
    mAlbums = getProfilePage(aPath+aUserId, aUserId+ "/albumid/(.*?)\\?alt=json");

    //mAlbums = getAlbumUrls(output, aUserId+ "/albumid/(.*?)\\?alt=json");

    //get images from album

    //get download links

  }

  /**
   * Get a profile page content
   * @param aPath complete profile url
   * @param aRegex the regex identifying individual albums
   * @return a set of unique album urls
   */
  private Set<String> getProfilePage(String aPath, String aRegex)
  {
    Set<String> albumUrls = new HashSet<>();
    try{
      albumUrls.addAll(curlIt(aPath, null, "?alt=json", aRegex));
    }
    catch (Exception e) {
      albumUrls.add("Failed grab: " + e.getMessage());
    }
    return albumUrls;
  }


  private Set<String> curlIt(String aUrl, String aUrlPrefix, String aUrlSuffix, String aRegex) throws Exception{
    Set<String> albumList = new HashSet<>();

    String urlBuilder = (aUrlPrefix!=null)?aUrlPrefix+aUrl : aUrl;
    urlBuilder += (aUrlSuffix!=null)?aUrlSuffix:"";

    URL obj = new URL(urlBuilder);

    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //con.setRequestMethod("GET");
    //con.setRequestProperty("User-Agent", USER_AGENT);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String line;

    while ((line = in.readLine()) != null) {
      if(aRegex!=null) {
        Matcher m = Pattern.compile(aRegex)
                .matcher(line);

        while (m.find()) {
          if(!albumList.contains(m.group()))
            albumList.add(m.group());
        }
      }
      else {
        albumList.add(line);
      }
    }
    in.close();

    return albumList;
  }

  Set<String> getAlbums() {
    return mAlbums;
  }



//  private Set<String> getAlbumUrls(String output, String regex) {
//
//    Set<String> allMatches = new HashSet<>();
//    Matcher m = Pattern.compile(regex)
//            .matcher(output);
//    while (m.find()) {
//      if(!allMatches.contains(m.group()))
//        allMatches.add(m.group());
//    }
//    return allMatches;
//  }

//  RipImagesFromUrl(String aPath, Boolean aOption)
//  {
//    this.basePath = aPath;
//
//    output = firstPass(aPath);
//
//
//  }

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
