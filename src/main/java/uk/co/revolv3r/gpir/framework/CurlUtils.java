package uk.co.revolv3r.gpir.framework;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CurlUtils
{
  private final String USER_AGENT = "Mozilla/5.0";

  private Set<String> mAlbums = new HashSet<>();
  private final Logger mLogger =Logger.getLogger(CurlUtils.class);

  /**
   * Get a profile page content
   * @param aPath complete profile url
   * @param aRegex the regex identifying individual albums
   * @return a set of unique album urls
   */
  public Set<String> getInitialAlbumPages(String aPath, String aRegex)
  {
    try{
      return parseXmlPath(aPath, aRegex);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Set<String> getActualAlbumPage(String aPath) throws IOException
  {
    Set<String> albumList = new HashSet<>();

    Document doc = Jsoup.connect(aPath).get();

    mLogger.info(doc.title());

    Elements matchingDivIds = doc.select("img");
    for (Element headline : matchingDivIds) {
      mLogger.info(String.format("%s\n",
              headline.toString()));
      albumList.add(headline.toString());
    }

    return albumList;
  }


  private Set<String> parseXmlPath(String aUrl, String aCssAttrib) throws Exception{
    Set<String> albumList = new HashSet<>();

    Document doc = Jsoup.connect(aUrl).get();

    mLogger.info(doc.title());

    Elements matchingDivIds = doc.select(aCssAttrib);
    for (Element headline : matchingDivIds) {
      mLogger.info(String.format("%s\n",
              headline.toString()));
      albumList.add(headline.toString());
    }

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
