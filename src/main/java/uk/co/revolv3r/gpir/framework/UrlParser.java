package uk.co.revolv3r.gpir.framework;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
public class UrlParser
{
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
  public CompletableFuture<Set<String>> retrieveImages(String passedValue)
  {
    passedValue = correctAlbumUrl(passedValue);
    String secondaryResult = retrieveActualAlbumCanonical(passedValue);

    return CompletableFuture.completedFuture(retrievePhotosFromAlbum(secondaryResult));


    //System.out.println(pathurl);
    //System.out.println(secondaryResult);

  }



  private Set<String> retrievePhotosFromAlbum(String aAlbumUrl) {
    final CurlUtils curler = new CurlUtils();

    try{
      return curler.curlIt(aAlbumUrl, null, null, "<img src=\"(.*?)\"", null);
    }catch (Exception e)
    {
      e.printStackTrace();
    }
    return new HashSet<>();
  }

  private String correctAlbumUrl(String album) {

    String grabbedURl = album;

    grabbedURl = grabbedURl.replace("albumid", "album");
    grabbedURl = grabbedURl.substring(0, grabbedURl.length()-9);
    return "https://get.google.com/albumarchive/pwaf/" + grabbedURl;

  }

  private void validateInput(String url) throws IllegalArgumentException
  {
    if (url.isEmpty())
      throw new IllegalArgumentException("URL cannot be empty");
    if (!url.contains("https://get.google.com/u/0/albumarchive/"))
      throw  new IllegalArgumentException("URL must begin: https://get.google.com/u/0/albumarchive/...");
  }


  public Set<String> retrieveInitialAlbumPages(String url){
    final CurlUtils curler = new CurlUtils();
    String userId = getUserId(url);
    return curler.getInitialAlbumPages("http://photos.googleapis.com/data/feed/api/user/"+userId,
            userId + "/albumid/(.*?)\\?alt=json");
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

  public String retrieveActualAlbumCanonical(String aUrl) {

      final CurlUtils curler = new CurlUtils();

      return curler.getActualAlbumPage(aUrl);
  }
}
