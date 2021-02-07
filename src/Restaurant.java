package src;
import java.util.*;

/**
 * A class representing a restaurant with a limited capacity.
 */
public class Restaurant implements IRestaurant {

  private String name;
  private int numTables;

  private Collection<Party> upcomingReservations;
  private Collection<Party> seatedParties;
  private Queue<Party> waitingParties;

  private Manager manager;

  /**
   * @param name - the name of the restaurant
   * @param numTables - the number of tables at the restaurant
   * @param reservations - a collection of parties who wish to dine at the
   *          restaurant
   */
  public Restaurant(String name, int numTables, Collection<Party> reservations) {
    this.name = name;
    this.numTables = numTables;

    upcomingReservations = new LinkedList<Party>(reservations);
    seatedParties = new LinkedList<Party>();
    waitingParties = new LinkedList<Party>();

    manager = new Manager(this);

    openRestaurant();
  }

  /**
   * Open the restaurant for the night.
   */
  public void openRestaurant() {
    System.out.println(this + " is opening for the night.");
    manager.startManaging();
    System.out.println(this + " is closing down for the night.");
  }

  Collection<Party> getUpcomingReservations() {
    return upcomingReservations;
  }

  Collection<Party> getSeatedParties() {
    return seatedParties;
  }

  @Override
  public void arrive(Party party) {
    if (!upcomingReservations.contains(party)) {
      System.out.println(party + " does not have a reservation at " + this
          + "!");
      return;
    }

    System.out.print(party + " arrived at " + this + ". ");

    // Seat the arriving party if there is room; otherwise, make them wait
    if (seatedParties.size() > numTables) {
      wait(party);
    } else {
      seat(party);
    }
  }

  @Override
  public void depart(Party party) {
    if (!seatedParties.contains(party)) {
      System.out.println(party + " has not yet been seated at " + this + "!");
      return;
    }

    System.out.println(party + " departed from " + this + ".");
    seatedParties.remove(party);

    // Seat a waiting party if one exists
    if (!waitingParties.isEmpty()) {
      // the poll method returns and removes the first element in the queue
      seat(waitingParties.poll());
    }
  }

  /**
   * Adds party to the queue.
   *
   * @param - the party to add to the queue
   */
  private void wait(Party party) {
    System.out.println(party + " has to wait in line to be seated.");
    waitingParties.add(party);
  }

  /**
   * Seats the party.
   *
   * @param party - the party to seat
   */
  private void seat(Party party) {
    System.out.println(party + " was seated.");
    seatedParties.add(party);
  }

  @Override
  public String toString() {
    return name;
  }

  public static void main(String[] args) {
    String name = "Favorite Colors Restaurant";
    int numTables = 2;

    LinkedList<Party> reservations = new LinkedList<Party>();
    // The most bizarre interior paint shade names of all time
    // Source: buzzfeed
    reservations.add(new Party("hugs and kisses"));
    reservations.add(new Party("mayonnaise"));
    reservations.add(new Party("potentially purple"));
    reservations.add(new Party("dragons blood"));
    reservations.add(new Party("song of summer"));
    reservations.add(new Party("bath salts"));
    reservations.add(new Party("friendship"));
    reservations.add(new Party("anonymous"));
    reservations.add(new Party("nacho cheese"));
    reservations.add(new Party("grandma's sweater"));

    new Restaurant(name, numTables, reservations);
  }

}
