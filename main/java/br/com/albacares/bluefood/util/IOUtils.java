package br.com.albacares.bluefood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IOUtils {
	
	public static void copy(InputStream in, String filename, String outputDir) throws IOException {
		Files.copy(in, Paths.get(outputDir, filename), StandardCopyOption.REPLACE_EXISTING);		
	}

}
