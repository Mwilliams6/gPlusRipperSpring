package uk.co.revolv3r.gpir.web;

import java.io.IOException;
import java.io.OutputStream;

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
    final String xml = mUrlParser.formatUrl(url);


    return xml;
  }
}
