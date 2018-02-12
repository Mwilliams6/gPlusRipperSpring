package uk.co.revolv3r.gpir.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @RequestMapping( method=RequestMethod.GET, value="/subView" )
  public ModelAndView getSubView( Model model ) {
    model.addAttribute( "user", "Joe Dirt" );
    model.addAttribute( "time", new Date() );
    return new ModelAndView( "subView" );
  }

  @RequestMapping(value = "/parseUrl", method = RequestMethod.POST)
  @ResponseBody
  public Set<String> uploadAndRenumber(final String url, Model aModel)
  throws IOException
  {
    final Set<String> albumUrls = mUrlParser.retrieveAlbumsFromProfile(url);

    if (albumUrls.isEmpty())
      return new HashSet<>(Arrays.asList("Error: no matches"));

    for(String album : albumUrls)
    {
      aModel.addAttribute("text", album);


    }

    //"<a href='"+correctedUrl+"'>"+correctedUrl+"</a><br/>"
    return albumUrls;
  }



}
