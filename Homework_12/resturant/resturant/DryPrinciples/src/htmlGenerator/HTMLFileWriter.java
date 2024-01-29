package htmlGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HTMLFileWriter {
	public static void writeHTMLToFile(String fileName, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(content);
            System.out.println(fileName + " has been generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

}
