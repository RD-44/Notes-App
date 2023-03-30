package uk.ac.ucl.model;

// Used to retrieve a reference to the Model object.
public class ModelFactory
{
  private static Model model;

  public static Model getModel() {
    if (model == null)
    {
      model = new Model();
      model.readFile();
    }
    return model;
  }
}
