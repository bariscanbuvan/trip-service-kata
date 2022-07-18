package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

	// make this class singleton
	private static TripDAO instance = new TripDAO();

	public TripDAO() {
	}

	public static TripDAO getInstance() {
		return instance;
	}

	@Deprecated
	public static List<Trip> findTripsByUser(User user) {
		return instance.getTripsByUser(user);
	}

	public List<Trip> getTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}