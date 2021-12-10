
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Context;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.CookieHandler;
import java.util.List;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FIRST_NEIGHBOUR = 1;

    private ListNeighbourActivity mActivity;
    private NeighbourRepository mNeighbourRepository;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mNeighbourRepository = DI.getNeighbourApiService();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     * ( Phase 2 : test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs
     * compte bien un utilisateur en moins.)
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * Phase 2 :"test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
     * détails est bien lancé".
     */
    @Test
    public void myDetailNeighbour_shouldNotBeEmpty() {
        // When: click on the neighbour of the first position
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));
        //Then : Displayed view is the detail view
        onView(ViewMatchers.withId(R.id.bloc0)).check(matches(isDisplayed()));

    }

    /**
     * Phase 2 : test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
     * le nom de l’utilisateur en question est bien rempli ;
     */
    @Test
    public void editTextTextPersonName2_shouldNotBeEmpty() {
        // When:click on the neighbour of the first position
        // (remark position 8 is "Emma" because of the delete of the test "myNeighboursList_deleteAction_shouldRemoveItem")
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(8, click()));
        //Then :On the displayed view the editText is empty
        onView(ViewMatchers.withId(R.id.editTextTextPersonName2)).check(matches(withText("Emma")));

    }

    /**
     * test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
     * favoris.
     */
    @Test
    public void list_favorite_neighbours_contains_neighbour_set_as_favorite() {
        //First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(matches(hasMinimumChildCount(0)));
        // When:the neighbour is favorite
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(click());
        onView(ViewMatchers.withId(R.id.upButton)).perform(click());
        //second favorite
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));
        onView(ViewMatchers.withId(R.id.floatingActionButton)).perform(click());
        onView(ViewMatchers.withId(R.id.upButton)).perform(click());
        //Then :the fragment list_favorite_neighbours display neighbour set as favorite
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(swipeLeft());
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours)).check(withItemCount(2));
    }


}
