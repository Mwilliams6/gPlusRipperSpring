package uk.co.revolv3r.gpir.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import uk.co.revolv3r.gpir.framework.UrlParser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

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

  @RequestMapping( method=RequestMethod.GET, value="/subView" )
  public ModelAndView getSubView( Model model ) {
    model.addAttribute( "user", "Joe Dirt" );
    model.addAttribute( "time", new Date() );
    return new ModelAndView( "subView" );
  }

  @RequestMapping(value = "/parseUrl", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> uploadAndRenumber(HttpServletRequest request, HttpServletResponse response)
  throws IOException
  {
    String returnStr = null;
    int i =0;

    Set<String> results = null;
    final Set<String> albumUrls = mUrlParser.retrieveAlbumsFromProfile(request.getParameter("urlPath"));

    if (albumUrls.isEmpty())
      return ResponseEntity.ok("no data");
    for(String album : albumUrls)
    {
      i++;
      results = mUrlParser.retrieveImages(album);

      for (String value : results)
      {
        returnStr += value;
      }


      if (i>2)
      {

        break;
      }

    }

    //"<a href='"+correctedUrl+"'>"+correctedUrl+"</a><br/>"
    //return albumUrls;
    return ResponseEntity.ok(returnStr);
  }



}
