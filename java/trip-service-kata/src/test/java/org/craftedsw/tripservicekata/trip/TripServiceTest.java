package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripServiceTest {

    class TestableTripService extends TripService {

        User user;

        public TestableTripService(User user) {
            this.user = user;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return Collections.singletonList(new Trip());
        }

        @Override
        protected User getLoggedUser() {
            return user;
        }
    }

    @Test
    public void should_throw_UserNotLoggedInException_when_logged_user_is_null() {
        // given


        TestableTripService tripService = new TestableTripService(null);

        // when
        UserNotLoggedInException exception =
                assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(new User()));

        // then
        assertNotNull(exception);
    }

    @Test
    @Disabled
    public void should_return_empty_trips_list_when_user_has_no_friend() {
        // given
        TestableTripService tripService = new TestableTripService(new User());

        // when
        List<Trip> trips = tripService.getTripsByUser(new User());

        // then
        assertEquals(0, trips.size());
    }

    @Test
    @Disabled
    public void should_return_trips_by_user() {
        // given
        User friend = new User();
        User loggedUser = new User();
        friend.addFriend(loggedUser);

        class TestableTripService extends TripService {

            User user;

            public TestableTripService(User user) {
                this.user = user;
            }

            @Override
            protected List<Trip> findTripsByUser(User user) {
                return Collections.singletonList(new Trip());
            }

            @Override
            protected User getLoggedUser() {
                loggedUser.addFriend(friend);
                return loggedUser;
            }
        }

        TripService tripService = new TestableTripService();

        // when
        List<Trip> trips = tripService.getTripsByUser(friend);

        // then
        assertTrue(trips.size() == 1);
    }
}
