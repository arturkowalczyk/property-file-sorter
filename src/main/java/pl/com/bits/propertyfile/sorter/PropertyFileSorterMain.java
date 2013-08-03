package pl.com.bits.propertyfile.sorter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import pl.com.bits.propertyfile.sorter.domain.PropertyFile;
import pl.com.bits.propertyfile.sorter.domain.PropertyFileParser;

public class PropertyFileSorterMain {

  public static void main(String[] filePaths) throws IOException {
    if (filePaths.length > 0) {
      processAllFiles(filePaths);

      info("Done ");
    } else {
      error("Usage java -jar property-file-sorter-[...].jar [file-path]+");
    }
  }

  private static void processAllFiles(String[] filePaths) throws IOException {
    for (String filePath : filePaths) {
      final File file = new File(filePath);

      info("File exists: " + file.exists());
      info("Sorting file: " + file.getAbsolutePath());

      if (file.exists()) {
        processFile(file);
      }
    }
  }

  private static void processFile(File file) throws IOException {
    final InputStream inStream = new FileInputStream(file);
    final PropertyFileParser parser = new PropertyFileParser(inStream);
    final PropertyFile messages = parser.parse();

    FileOutputStream fos = new FileOutputStream(file);
    messages.serialize(fos);
    fos.close();
  }

  private static void info(String message) {
    System.out.println(message);
  }

  private static void error(String message) {
    System.err.println(message);
  }
}
