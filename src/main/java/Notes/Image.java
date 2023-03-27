package Notes;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Image extends Item{
    public Image(@JsonProperty("contents") String imageURL) {
        super("<img src=\"" + imageURL + "\" width=\"400\" height=\"500\">");
    }

}
