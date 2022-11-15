import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.json.JSONArray;

public class ParseTest {
    public static void main(String[] args) throws IOException {
        JSONArray json = new JSONArray(Files.readString(Path.of("parser/test_grammar.json")));
        // Get all nonterminal symbols

        // Array of all nonterminals
        JSONObject[] objects = toJSONObjects(json);
        String[] universe_nonterminals = Stream.of(objects)
            .filter(obj -> obj.getString("$event").equals("declare-term-type"))
            .map(obj -> obj.getString("name"))
            .toArray(String[]::new);

        // Parser always stores grammar from synth-fun (if no new grammar is specified, it uses background grammar/theory
        // with "__agtt" appended to the end of each nonterminal)

        JSONObject grammar = Stream.of(objects)
            .filter(obj -> obj.getString("$event").equals("synth-fun"))
            .findFirst().orElse(null)
            .getJSONObject("grammar");
        
        // subgrammar nonterminals
        String[] grammar_nonterminals = Stream.of(toJSONObjects(grammar.getJSONArray("nonTerminals")))
            .map(obj -> obj.getString("name"))
            .toArray(String[]::new);
        
        Equation[] productions = Stream.of(grammar_nonterminals)
            .map(nonterminal -> 
                 Stream.of(toJSONObjects(grammar.getJSONArray("productions")))
                 .filter(obj -> obj.getString("instance").equals(nonterminal))
                 .
            )
    }

    // convert JSONArray to array of JSONObjects (json.toList() returns generic Object)
    public static JSONObject[] toJSONObjects(JSONArray jsonArr) {
        JSONObject[] objects = new JSONObject[jsonArr.length()];
        for(int i = 0; i < jsonArr.length(); i++) {
            objects[i] = jsonArr.getJSONObject(i);
        }
        return objects;
    }
}