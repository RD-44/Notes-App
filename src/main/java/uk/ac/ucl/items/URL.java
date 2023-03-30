package uk.ac.ucl.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ucl.htmlfilter.Filter;

public class URL extends Item{
    public URL(@JsonProperty("id") int id, @JsonProperty("contents") String url) {
        super(id, url);
    }

    public String display() {
        return "<a href=\""+ Filter.parse(contents) + "\">"+ Filter.parse(contents) + "</a>";
    } // Uses appropriate HTML code to display a link.

    public String getEditText() {
        return "Enter new url here:";
    }

}
