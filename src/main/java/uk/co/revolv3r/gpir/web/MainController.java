package uk.co.revolv3r.gpir.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import uk.co.revolv3r.gpir.config.GPIRConstants;
import uk.co.revolv3r.gpir.framework.UrlParser;

@Controller
@RequestMapping("/")
public class MainController
{
  
  @Resource
  private UrlParser mUrlParser;
  
  @RequestMapping
  public ModelAndView get()
  {
    return new ModelAndView("index");
  }
  
  @RequestMapping(value = "/parseUrl", method = RequestMethod.POST)
  public @ResponseBody String uploadAndRenumber(final String url)
          throws IOException
  {
    final Set<String> albumUrls = mUrlParser.retrieveAlbumsFromProfile(url);

    if (albumUrls.isEmpty())
      return "Error: no matches";

    StringBuilder albumUrlsString = new StringBuilder();

    for(String album : albumUrls)
    {
      //String friendlyUrl = "<a href='http://photos.googleapis.com/data/entry/api/user/" + album+"'>"+i+"</a><br/>";
      //String correctedUrl = correctAlbumUrl(album);
      //System.out.println(correctedUrl);
      //albumUrlsString.append(correctedUrl);

      //retrieve header into from corrected url
      try
      {
        //final String actualAlbumUrl = mUrlParser.retrieveActualAlbumCanonical(correctedUrl);
        System.out.println("oj did it");
      }
      catch (Exception e)
      {

      }


    }

    //"<a href='"+correctedUrl+"'>"+correctedUrl+"</a><br/>"
    return albumUrlsString.toString();
  }



}
