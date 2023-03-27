package uk.ac.ucl.model;
import java.io.*;
import java.util.*;

import Notes.*;
import com.fasterxml.jackson.databind.*;

public class Model
{
  private final int TEXT = 0, URL = 1, LIST = 2, IMG = 3;
  private ItemList main;
  private HashMap<ItemList, ArrayList<Item>> results;
  private Stack<ItemList> history;
  public ItemList getMain(){
    return main;
  }

  public void readFile(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper(); // Used to map json entries to objects.
    try (MappingIterator<ItemList> it = mapper.readerFor(ItemList.class).readValues(file)){ // Maps to the ItemList class
      if (it.hasNextValue()) {
        try{
          main = it.nextValue();
        }catch(JsonMappingException e){
          System.err.println("Problem reading file: " + e.getMessage());
        }
      }else{
        main = new ItemList("MAIN"); // If json is empty we make a new list
        updateJSON();
      }
    }
    history = new Stack<>(); // Initialise stack to store history
  }

  private void updateJSON(){ // Rewrites the main list to the json file to save any updates made.
    ObjectMapper mapper = new ObjectMapper();
    try (SequenceWriter seq = mapper.writer()
            .writeValues(new FileOutputStream("items.json"))) {
      seq.write(main);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void traverseSearch(ItemList itemList, String keyword){
     // Recursively visits all lists via depth first search.
      if (itemList.getContents().contains(keyword)){
        results.put(itemList, new ArrayList<>());
      }
      for (Item item : itemList.getItems()){
        if (item.getContents().contains((keyword))){
          if(results.containsKey(itemList)){
            results.get(itemList).add(item);
          }else{
            results.put(itemList, new ArrayList<>());
            results.get(itemList).add(item);
          }
        }
      }
      for (ItemList nestedItemList: itemList.getLists()){
        traverseSearch(nestedItemList, keyword);
      }
  }

  private void insert(ItemList currList, ItemList parent, String content, int type){
    for (ItemList note : currList.getLists()){
      if (note == parent){
        switch (type) {
          case URL -> note.addItem(new URL(content));
          case TEXT -> note.addItem(new Text(content));
          case IMG -> note.addItem(new Image(content));
          case LIST -> note.addItem(new ItemList(content));
        }
        updateJSON();
        return; // Break out of loop and recursion once found.
      }
      insert(note, parent, content, type); // Traverse until found.
    }
  }

  public HashMap<ItemList, ArrayList<Item>> searchFor(String keyword)
  {
    results = new HashMap<>();
    for (ItemList itemList : main.getLists()){
      traverseSearch(itemList, keyword);
    }
    return results;
  }

  public void addMainNote(String name){
    main.addItem(new ItemList(name));
    updateJSON();
  }

  public void addItem(String name, ItemList parent, String type){
    if (type.contains("text")){
      insert(main, parent, name, TEXT);
    }else if (type.contains("url")){
      insert(main, parent, name, URL);
    }else if (type.contains("note")){
      insert(main, parent, name, LIST);
    }else if (type.contains("image")){
      insert(main, parent, name, IMG);
    }
  }

  public void delList(ItemList listToDelete, ItemList parent){
    parent.deleteList(listToDelete);
    updateJSON();
  }

  public void delItem(Item itemToDelete, ItemList parent){
    parent.deleteItem(itemToDelete);
    updateJSON();
  }



  public ItemList prev(){
    try {
      return history.pop();
    }catch (EmptyStackException e){
      System.err.println("Error - History stack is empty");
      return null;
    }
  }

  public void pushList(ItemList list){
    history.push(list);
  }


}
