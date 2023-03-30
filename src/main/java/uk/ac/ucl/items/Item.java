package uk.ac.ucl.items;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;

// Below ensures subclass information is stored in the json file
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME) // Stores subclass name in the "type" field in the JSON file.
@JsonSubTypes({ // Maps names to each subclass
        @JsonSubTypes.Type(value = Text.class, name = "Text"),
        @JsonSubTypes.Type(value = Image.class, name = "Image"),
        @JsonSubTypes.Type(value = URL.class, name = "URL"),
        @JsonSubTypes.Type(value = ItemList.class, name = "ItemList")}
)

public abstract class Item implements Serializable{
    private int id;
    protected String contents;

    public Item(@JsonProperty("id") int id, @JsonProperty("contents") String contents){
        this.id = id; // Number which is unique for items in the same list.
        //id is used to position items in the right order on a page and distinguish between items of the same content.
        this.contents = contents; // String which contains the information that will be shown for this item.
    }

    public String getContents() {
        return contents;
    }

    @JsonIgnore
    public abstract String getEditText(); // Used to get the right text to appear for the edit input box.

    // Used to get the text which will be displayed in html when this item is shown on a page.
    // This is heavily related to the content, hence content is a protected attribute.
    public abstract String display();

    public int getId() {
        return id;
    }

    public void setContents(String contents){
        this.contents = contents;
    }



}
