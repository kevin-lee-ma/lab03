package src;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * A Manager for a restaurant.
 *
 * The restaurant manager observes the status of the restaurant. As long as
 * there are parties with reservations still waiting to be served, the manager
 * decides when they should arrive and when they should depart.
 */
public class Manager implements IManager {

  private static final int PARTY_ARRIVES = 1;
  private static final int PARTY_DEPARTS = 0;

  private Restaurant restaurant;
  private Random random;

  /**
   * @param restaurant - the restaurant to manage
   */
  public Manager(Restaurant restaurant) {
    this.restaurant = restaurant;
    this.random = new Random();
  }

  @Override
  public void startManaging() {
    Collection<Party> upcomingReservations =
            this.restaurant.getUpcomingReservations();
    Collection<Party> seatedParties = this.restaurant.getSeatedParties();

    // Choose for a party to arrive or depart
    // Stop when both upcomingReservations and seatedParties are empty
    while (!(upcomingReservations.isEmpty() && seatedParties.isEmpty())) {
      int nextEvent = makeChoice();

      if (nextEvent == PARTY_ARRIVES) {
        partyArrives();
      } else if (nextEvent == PARTY_DEPARTS) {
        partyDeparts();
      }
    }
  }

  /**
   * Choose whether a party should arrive or a party should depart.
   *
   * @return either PARTY_ARRIVES or PARTY_DEPARTS
   */
  private int makeChoice() {
    // If everyone has arrived, only choose for parties to depart
    if (this.restaurant.getUpcomingReservations().isEmpty()
            && !this.restaurant.getSeatedParties().isEmpty()) {
      return PARTY_DEPARTS;
    }

    // If there are no parties seated, only choose for parties to arrive
    if (!this.restaurant.getUpcomingReservations().isEmpty()
            && this.restaurant.getSeatedParties().isEmpty()) {
      return PARTY_ARRIVES;
    }

    // If not everyone has arrived, and there are still parties seated,
    // choose for parties to arrive twice as often as they depart
    return Math.min(this.random.nextInt(3), 2);
  }

  /**
   * Choose a party to arrive at the restaurant.
   */
  private void partyArrives() {
    Party arrivingParty =
            chooseParty(this.restaurant.getUpcomingReservations());
    this.restaurant.arrive(arrivingParty);
  }

  /**
   * Choose a party to depart the restaurant.
   */
  private void partyDeparts() {
    Party departingParty = chooseParty(this.restaurant.getSeatedParties());
    this.restaurant.depart(departingParty);
  }

  /**
   * Select a random Party.
   *
   * @param parties - a collection of parties to choose from
   *
   * @return a random Party in parties
   */
  private Party chooseParty(Collection<Party> parties) {
    int partyIndex = this.random.nextInt(parties.size());

    // Create iterator and go to 0th element
    Iterator<Party> it = parties.iterator();
    Party current = it.next();

    // Find the element at partyIndex in the collection and return it
    for (int i = 0; i < partyIndex; i++) {
      current = it.next();
    }

    return current;
  }

}
