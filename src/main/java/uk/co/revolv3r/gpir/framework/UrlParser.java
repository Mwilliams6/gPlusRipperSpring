package uk.co.revolv3r.gpir.framework;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UrlParser
{
  public Set<String> retrieveAlbumsFromProfile(String url)
  {
    return parseInput(url);
  }
  
  private Set<String> parseInput(String url)
  {
    if (url.isEmpty())
      throw new IllegalArgumentException("URL cannot be empty");
    if (!url.contains("https://get.google.com/u/0/albumarchive/"))
      throw  new IllegalArgumentException("URL must begin: https://get.google.com/u/0/albumarchive/...");

    final RipImagesFromUrl mapper = new RipImagesFromUrl(
            "http://photos.googleapis.com/data/feed/api/user/",
            getUserId(url));

    return mapper.getAlbums();
  }

  private String getUserId(String url)
  {
    String aUserId;

    String[] stringArray = url.split("/");

    if (stringArray.length>1) {
      aUserId = stringArray[6];
      return aUserId;
    }

    return null;
  }

}
