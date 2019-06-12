package com.epicodus.myrestaurants;

import android.os.Build;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

//@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class RestaurantActivityTest {
    private RestaurantsActivity activity;
    private ListView mRestaurantListView;

    @Before
    public void setup() {
        mRestaurantListView = (ListView) activity.findViewById(R.id.listView);
    }
    @Test
    public void restaurantListViewPopulates(){
        assertNotNull(mRestaurantListView.getAdapter());
        assertEquals(mRestaurantListView.getAdapter().getCount(),15);
    }
}

