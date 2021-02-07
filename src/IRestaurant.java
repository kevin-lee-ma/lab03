package src;
/**
 * An interface for restaurants, where parties can arrive and depart.
 */
public interface IRestaurant {

  /**
   * A party arrives at the restaurant. They are seated if there is room,
   * otherwise they must wait in line. In either case, they should be removed
   * from the list of upcoming reservations.
   *
   * @param party - the arriving party
   */
  public void arrive(Party party);

  /**
   * A party departs from the restaurant. If there are parties waiting in line,
   * the first party in line should be seated. The party that departs from the
   * restaurant should be removed from the list of seated parties.
   *
   * @param party - the departing party
   */
  public void depart(Party party);

}
