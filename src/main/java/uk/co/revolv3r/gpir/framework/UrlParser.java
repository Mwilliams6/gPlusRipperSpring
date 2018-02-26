package uk.co.revolv3r.gpir.framework;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class UrlParser
{
  private Logger mLogger = Logger.getLogger(UrlParser.class);
  final String BASE_URL = "http://photos.googleapis.com/data/feed/api/user/";
  public Set<String> retrieveAlbumsFromProfile(String url)
  {

    int i =0;
    Set<String> result = new HashSet<>();
    try
    {
      validateInput(url);
      result=retrieveInitialAlbumPages(url);




    } catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    }
    return result;
  }


  @Async
  public CompletableFuture<Set<String>> retrieveImages(String passedValue) throws IOException
  {
    passedValue = correctAlbumUrl(passedValue);

    return CompletableFuture.completedFuture(retrieveActualAlbumCanonical(passedValue));

  }



  private Set<String> retrievePhotosFromAlbum(String aAlbumUrl) {

    mLogger.info(aAlbumUrl);

    return new HashSet<>();
  }

  private String correctAlbumUrl(String album) {

    String grabbedURl = album;

    grabbedURl = grabbedURl.replace("albumid", "album");
    grabbedURl = grabbedURl.substring(0, grabbedURl.length()-6);
    grabbedURl = grabbedURl.substring(55, grabbedURl.length());
    return "https://get.google.com/albumarchive/pwaf/" + grabbedURl;

  }

  private void validateInput(String url) throws IllegalArgumentException
  {
    if (url.isEmpty())
      throw new IllegalArgumentException("URL cannot be empty");
    if (!url.contains("https://get.google.com/u/0/albumarchive/"))
      throw  new IllegalArgumentException("URL must begin: https://get.google.com/u/0/albumarchive/...");
  }


  private Set<String> retrieveInitialAlbumPages(String url){
    final CurlUtils curler = new CurlUtils();
    String userId = getUserId(url);
    return curler.getInitialAlbumPages("http://photos.googleapis.com/data/feed/api/user/"+userId, "id");
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

  private Set<String> retrieveActualAlbumCanonical(String aUrl) throws IOException{

      final CurlUtils curler = new CurlUtils();

      Set<String> imagePaths =  curler.getActualAlbumPage(aUrl);

      for(String imageVal : imagePaths)
      {
        mLogger.info(imageVal);
      }

      return imagePaths;
  }
}
