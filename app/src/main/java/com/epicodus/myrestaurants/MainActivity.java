package com.epicodus.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditTextView;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;

    public static final String TAG = MainActivity.class.getSimpleName();


    private Button mFindRestaurantButton;
    private EditText mLocationEditText;
//private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);


        Typeface SquirrelFont = Typeface.createFromAsset(getAssets(), "fonts/GreatVibes-Regular.otf");
        mAppNameTextView.setTypeface(SquirrelFont);


        mFindRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mFindRestaurantsButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);

        }
    }
}