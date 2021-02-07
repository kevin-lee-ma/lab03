package src;
/**
 * A class representing a (dining) party, which can make a reservation to dine
 * at a restaurant.
 */
public class Party {

  private String name;

  /**
   * @param name - the name of the party
   */
  public Party(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name + " party";
  }

}
