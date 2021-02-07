package src;
/**
 * An interface for a restaurant Manager.
 */
public interface IManager {

  /**
   * Manage the restaurant, choosing parties with upcoming reservations to
   * arrive, and seated parties to depart, until everyone with reservations has
   * been served.
   */
  public void startManaging();

}
