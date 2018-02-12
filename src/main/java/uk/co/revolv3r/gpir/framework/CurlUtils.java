package uk.co.revolv3r.gpir.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

class CurlUtils
{
  private final String USER_AGENT = "Mozilla/5.0";

  private Set<String> mAlbums = new HashSet<>();


  /**
   * Get a profile page content
   * @param aPath complete profile url
   * @param aRegex the regex identifying individual albums
   * @return a set of unique album urls
   */
  public Set<String> getInitialAlbumPages(String aPath, String aRegex)
  {
    Set<String> albumUrls = new HashSet<>();
    try{
      albumUrls.addAll(curlIt(aPath, null, "?alt=json", aRegex, null));
    }
    catch (Exception e) {
      albumUrls.add("Failed grab: " + e.getMessage());
    }
    return albumUrls;
  }

  public String getActualAlbumPage(String aPath)
  {
    try{
      Set<String> values = curlIt(aPath, null, null, null, 300);
      if (!values.isEmpty())
        return values.iterator().next();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  public Set<String> curlIt(String aUrl, String aUrlPrefix, String aUrlSuffix, String aRegex, Integer aLimitChars) throws Exception{
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
      if (aLimitChars != null){
        line = line.substring(151, aLimitChars);
        line = line.substring(0, line.indexOf('"'));

        if(!albumList.contains(line))
          albumList.add(line);

        break;
      }

      if(aRegex!=null) {
        Matcher m = Pattern.compile(aRegex)
                .matcher(line);

        while (m.find()) {
          if(!albumList.contains(m.group()))
            albumList.add(m.group());
        }
      }
      else {
        if(!albumList.contains(line))
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
