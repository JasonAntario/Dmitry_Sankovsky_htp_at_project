package runners;

import utills.JsonParser;

import java.io.IOException;

public class JSONParserRunner {
    public static void main(String[] args) throws IOException {
        JsonParser jsonParser = new JsonParser();
       // jsonParser.parseJSON();
       jsonParser.parseGson();
      //  jsonParser.parseJackson();
      //  jsonParser.fromGson();
    }
}
