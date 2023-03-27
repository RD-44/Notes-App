package Notes;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Text extends Item{
    public Text(@JsonProperty("contents") String contents) {
        super(contents);
    }

}
