package scabench;


import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Simple application -- the XML to be converted and pretty-printed is read from a file,
 * but a precondition is exposed so that only small documents can be processed.
 * Therefore, the vulnerability cannot be exploited.
 * @author jens dietrich
 */
public class XML2JSONConverter {

    public static void main (String[] args) throws IOException {
        File input = new File(args[0]);

        String xml = Files.readString(input.toPath(), Charset.defaultCharset());
        // simplified and ignoring closing tags (as they could be hidden in comments)
        long potentialMaxDepth = xml.chars().filter(c->c=='<').count();
        if (potentialMaxDepth<1000) {
            JSONObject json = XML.toJSONObject(xml);
            System.out.println(json.toString(4));
        }
        else {
            throw new IllegalArgumentException("unsafe input");
        }
    }


}
