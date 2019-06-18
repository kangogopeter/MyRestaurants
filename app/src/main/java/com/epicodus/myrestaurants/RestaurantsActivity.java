package com.epicodus.myrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Response;


public class RestaurantsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantsActivity.class.getSimpleName();

    private TextView mLocationTextView;

    private ListView mListView;


    private String[] restaurants = new String[]{"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};


    private String[] cuisines = new String[]{
            "Vegan food", "Breakfast", "fish dishs", "Scandinavian", "cofee", "English Food",
            "Burgers", "Fast Food", "Noodle soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Mexican"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

//        getRestaurants(location);

        ButterKnife.bind(this);


        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);


//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines); //must match with the constructor
//        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView) view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_LONG).show();
                Log.v(TAG, "IN the onItemClickListener!");

            }
        });


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
        Log.d(TAG, "In the onCreate method !");

    }

//    @BindView(R.id.locationEditText) TextView mLocationTextView;
//    @BindView(R.id.listView) ListView mListView

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();

         yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                        mRestaurants = yelpService.processResults(response);
                        RestaurantsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String[] restaurantNames = new String[mRestaurants.size()];
                                for (int i = 0; i<restaurantNames.length;i++){
                                    restaurantNames[i] = mRestaurants.get(i).getName();
                                }

                                ArrayAdapter adapter = new ArrayAdapter(RestaurantsActivity.this,
                                        android.R.layout.simple_list_item_1, restaurantNames);
                                mListView.setAdapter(adapter);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
