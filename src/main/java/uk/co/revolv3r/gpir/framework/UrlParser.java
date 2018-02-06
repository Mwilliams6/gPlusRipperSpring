package uk.co.revolv3r.gpir.framework;

import org.springframework.stereotype.Component;


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
    
    final RipImagesFromUrl mapper = new RipImagesFromUrl(url);
//    final Collection<int[]> idMaps = mapper.firstPass(xml, firstNumber, shift);
//    xml = mapper.secondPass(xml,idMaps);
//    xml = mapper.thirdPass(xml);
//    return xml;
    return null;
  }
  
}
