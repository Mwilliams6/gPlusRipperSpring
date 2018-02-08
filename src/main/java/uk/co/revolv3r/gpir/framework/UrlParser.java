package uk.co.revolv3r.gpir.framework;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UrlParser
{
  public String formatUrl(String url)
  {
    return parseInput(url);
  }
  
  public String parseInput(String url)
  {
    if (url.isEmpty())
      throw new IllegalArgumentException("URL cannot be empty");
    if (!url.contains("https://get.google.com/u/0/albumarchive/"))
      throw  new IllegalArgumentException("URL must begin: https://get.google.com/u/0/albumarchive/...");

    url = getUserId(url);

    final RipImagesFromUrl mapper = new RipImagesFromUrl("http://photos.googleapis.com/data/feed/api/user/", url);
//    final Collection<int[]> idMaps = mapper.firstPass(xml, firstNumber, shift);
//    xml = mapper.secondPass(xml,idMaps);
//    xml = mapper.thirdPass(xml);
//    return xml;
    return mapper.getOutput();
  }

  private String getUserId(String url)
  {
    String aUserId = null;

    String[] stringArray = url.split("/");

    if (stringArray.length>1)
    {
      aUserId = stringArray[6];
    }
    else
    {
      return "/badUrl";
    }


    return aUserId;
  }

}
