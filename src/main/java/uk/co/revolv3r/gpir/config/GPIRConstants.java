package uk.co.revolv3r.gpir.config;

import java.nio.charset.Charset;

public class GPIRConstants
{
  private static final Charset sDefaultCharset = Charset.forName("UTF-8");
  public static Charset defaultCharset()
  {
    return sDefaultCharset;
  }
}
