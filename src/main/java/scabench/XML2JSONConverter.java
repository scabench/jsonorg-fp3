package scabench;

import org.json.JSONObject;
import org.json.XML;

/**
 * Simple application -- the XML to be converted and pretty-printed is read from a file,
 * but a precondition is imposed so that only small xml documents can be processed (for which the stack size is sufficient).
 * Therefore, the vulnerability cannot be exploited.
 * @author jens dietrich
 */
public class XML2JSONConverter {

    public static void main (String[] args) {
        String xml = args[0];
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
