package notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import htmlfilter.Filter;

public class URL extends Item{
    public URL(@JsonProperty("id") int id, @JsonProperty("contents") String url) {
        super(id, url);
    }

    public String display() {
        return "<a href=\""+ Filter.parse(contents) + "\">"+ Filter.parse(contents) + "</a>";
    }

    public String getEditText() {
        return "Enter new url here:";
    }

}
