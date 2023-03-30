package uk.ac.ucl.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ucl.htmlfilter.Filter;

public class Image extends Item{
    public Image(@JsonProperty("id") int id, @JsonProperty("contents") String imgURL) {
        super(id, imgURL);
    }

    public String display() {
        return "<img src=\""+ Filter.parse(contents) + "\">";
    } // Adds in HTML code to display the image

    public String getEditText() {
        return "Enter new image link here:";
    }
}
