package uk.co.revolv3r.gpir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import uk.co.revolv3r.gpir.util.BrowserUtil;

@SpringBootApplication
public class GPlusImageRipperApplication {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(GPlusImageRipperApplication.class);
		try
		{
			logger.info("Launching GPlus Image Ripper");
			final ConfigurableApplicationContext context = SpringApplication.run(
					GPlusImageRipperApplication.class, args);
			BrowserUtil.localHost(context.getEnvironment());
		}
		catch (Throwable aE)
		{
			logger.error("Problems: ", aE);
		}
	}
}
