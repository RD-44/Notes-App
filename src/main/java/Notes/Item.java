package Notes;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Text.class, name = "Text"),
        @JsonSubTypes.Type(value = Image.class, name = "Image"),
        @JsonSubTypes.Type(value = Image.class, name = "URL"),
        @JsonSubTypes.Type(value = Image.class, name = "ItemList")}
)

public abstract class Item implements Serializable{
    private String contents;
    //Maybe display content, search content, id

    public Item(@JsonProperty("contents") String contents){
        this.contents = contents;
    }


    public String getContents() {
        return contents;
    }


}
