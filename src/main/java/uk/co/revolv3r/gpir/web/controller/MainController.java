package uk.co.revolv3r.gpir.web.controller;

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
  public Set<String> uploadAndRenumber(HttpServletRequest request, HttpServletResponse response)
  throws IOException
  {
    final Set<String> albumUrls = mUrlParser.retrieveAlbumsFromProfile(request.getParameter("urlPath"));

    if (albumUrls.isEmpty())
      return new HashSet<>(Arrays.asList("Error: no matches"));

    for(String album : albumUrls)
    {
      mUrlParser.retrieveImages(album);


    }

    //"<a href='"+correctedUrl+"'>"+correctedUrl+"</a><br/>"
    return albumUrls;
  }



}
