package uk.co.revolv3r.gpir.framework;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class FileUrlParserTest
{
  
  private FileRenumberer mFileRenumberer;
//
//  private static Path MRV;
//
//  @BeforeClass
//  public static void beforeClass() throws URISyntaxException
//  {
//    MRV = Paths.get(Objects.requireNonNull(FileUrlParserTest.class.getClassLoader().getResource("MRV.bpmn")).toURI());
//  }
//
//  @Before
//  public void setUp() throws Exception
//  {
//    mFileRenumberer = new FileRenumberer();
//    ReflectionTestUtils.setField(mFileRenumberer,"mRenumberer", new UrlParser());
//    ReflectionTestUtils.setField(mFileRenumberer,"prefix","_");
//    ReflectionTestUtils.setField(mFileRenumberer,"padding",3);
//
//  }
//
//  @Test
//  public void renumberAfms() throws IOException
//  {
//    mFileRenumberer.renumberAfms(MRV,2,3);
//  }
}