package Notes;


import com.fasterxml.jackson.annotation.JsonProperty;

public class URL extends Item{
    public URL(@JsonProperty("contents") String url) {
        super("<a href =\"" + url + "\" target=\"_blank\">" + url + "</a>");
    }


}
