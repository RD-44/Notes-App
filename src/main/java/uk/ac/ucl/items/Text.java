package uk.ac.ucl.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ucl.htmlfilter.Filter;

public class Text extends Item{
    public Text(@JsonProperty("id") int id, @JsonProperty("contents") String contents) {
        super(id, contents);
    }

    public String display() {
        return Filter.parse(contents);
    }

    public String getEditText() {
        return "Enter new text here:";
    }

}
