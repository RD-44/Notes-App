package notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import htmlfilter.Filter;

public class Image extends Item{
    public Image(@JsonProperty("id") int id, @JsonProperty("contents") String imgURL) {
        super(id, imgURL);
    }

    public String display() {
        return "<img src=\""+ Filter.parse(contents) + "\">";
    }

    public String getEditText() {
        return "Enter new image link here:";
    }
}
