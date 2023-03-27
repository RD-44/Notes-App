package Notes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "contents")
public class ItemList extends Item{ // lists of items are also an item type, to allow nested classes.
    private ArrayList<Item> items;


    private ArrayList<ItemList> lists;

    public ItemList(@JsonProperty("content") String name, @JsonProperty("items") ArrayList<Item> items, @JsonProperty("lists") ArrayList<ItemList> lists) {
        super(name);
        this.items = items;
        this.lists = lists;
    }

    public ItemList(String name, ArrayList<Item> items, ItemList parent) {
        super(name);
        this.items = items;
        this.lists = new ArrayList<>();
    }

    public ItemList(String name) {
        super(name);
        this.items = new ArrayList<>();
        this.lists = new ArrayList<>();
    }


    public void addItem(Item newItem){
        items.add(newItem);
    }
    public void addItem(ItemList newList){
        lists.add(newList);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public ArrayList<ItemList> getLists(){
        return lists;
    }

    public void deleteList(ItemList list){
        lists.remove(list);
    }

    public void deleteItem(Item item){
        items.remove(item);
    }

}
