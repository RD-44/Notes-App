package uk.ac.ucl.model;
import java.io.File;
import java.io.IOException;

// Used to retrieve a reference to the Model object.

public class ModelFactory
{
  private static Model model;

  public static Model getModel() throws IOException
  {
    if (model == null)
    {
      model = new Model();
      model.readFile();
    }
    return model;
  }
}
