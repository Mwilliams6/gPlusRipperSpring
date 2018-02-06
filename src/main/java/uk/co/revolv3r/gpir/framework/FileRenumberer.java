package uk.co.revolv3r.gpir.framework;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import uk.co.revolv3r.gpir.config.GPIRConstants;

@Component
public class FileRenumberer
{
  @Resource
  private UrlParser mUrlParser;
  
  @Value("${bpmnf.pattern.prefix:_}")
  private String prefix;
  
  @Value("${bpmnf.pattern.suffix:}")
  private String suffix;
  
  @Value("${bpmnf.pattern.padding:3}")
  private int padding;
  
//  public Path renumberAfms(Path input, int start, int shift) throws IOException
//  {
//    String xml = new String(Files.readAllBytes(GPIRTools.assertValidInput(input)), GPIRConstants.defaultCharset());
//
//    xml = mUrlParser.renumber(xml,start,shift,prefix,suffix,padding);
//
//    final Path output = getOutputPathFromInput(input, 0);
//    Files.createFile(output);
//    Files.write(output,xml.getBytes(Charset.defaultCharset()));
//    return output;
//  }
  
  private static Path getOutputPathFromInput(final Path input, int attempt)
  {
    final String suffix = attempt == 0 ? "_renumbered" : "_renumbered_" + attempt;
    final String newFileName = StringUtils.substringBeforeLast(Objects.toString(input.getFileName()),".") + suffix + ".bpmn";
    final Path output = Paths.get(
            input.getParent() + File.separator + newFileName);
    
    if (!Files.exists(output))
      return output;
    
    return getOutputPathFromInput(input, attempt+1);
  }
}
