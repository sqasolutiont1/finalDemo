package pageObjects.dictionary;

public enum MainMenuItems {
  NewReleases("New Releases");

  // declaring private variable for getting values
  private final String item;

  // enum constructor - cannot be public or protected
  MainMenuItems(String item) {
    this.item = item;
  }

  // getter method
  public String getItem() {
    return this.item;
  }
}
