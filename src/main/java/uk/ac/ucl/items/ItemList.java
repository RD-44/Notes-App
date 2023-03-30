package uk.ac.ucl.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.ucl.htmlfilter.Filter;
import java.util.ArrayList;

public class ItemList extends Item{ // lists of items are also an item type, to allow nested lists.
    private ArrayList<Item> items;
    private ArrayList<ItemList> lists;
    private int childId;

    @JsonCreator
    public ItemList(@JsonProperty("id")  int id, @JsonProperty("contents") String name, @JsonProperty("childId") int childId) {
        super(id, name);
        this.childId = childId;
        this.items = new ArrayList<>();
        this.lists = new ArrayList<>();
    }

    //Alternative constructor used for making new lists that don't have a previous childId
    public ItemList(int id, String name){
        super(id, name);
        this.childId = 1;
        this.items = new ArrayList<>();
        this.lists = new ArrayList<>();
    }

    public int getChildId(){
        return childId;
    }

    public String getEditText() {
        return "Enter new list name here:";
    }

    public String display() {
        return Filter.parse(contents);
    }

    public void addItem(Item newItem){
        items.add(newItem);
        childId++;
    }
    public void addItem(ItemList newList){
        lists.add(newList);
        childId++;
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
