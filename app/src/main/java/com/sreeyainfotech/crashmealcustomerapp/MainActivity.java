package com.sreeyainfotech.crashmealcustomerapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sreeyainfotech.crashmealcustomerapp.adapter.SlidingImage_Adapter;
import com.sreeyainfotech.crashmealcustomerapp.model.AppLocationService;
import com.sreeyainfotech.crashmealcustomerapp.model.LocationAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.five};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private EditText location_self_edttxt;
    Context context;
    private ImageView current_location_adress_img;
    //    private Place place, place1;
    private Location fromLocation, toLocation;
    //    private LatLng latlangObj;
    public static final int PERMISSIONS_REQUEST = 10;
    AppLocationService appLocationService;
    private CircleIndicator indicator;
    private TextView login_txt, sigunup_txt;
    private Timer swipeTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        int PERMISSION_ALL = 10;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        findViewes();
    }

    private void findViewes() {
        appLocationService = new AppLocationService(MainActivity.this);

        mPager = (ViewPager) findViewById(R.id.pager);
        location_self_edttxt = (EditText) findViewById(R.id.location_self_edttxt);
        location_self_edttxt.setOnClickListener(this);
        current_location_adress_img = (ImageView) findViewById(R.id.current_location_adress_img);
        current_location_adress_img.setOnClickListener(this);
        login_txt = (TextView) findViewById(R.id.login_txt);
        login_txt.setOnClickListener(this);
        sigunup_txt = (TextView) findViewById(R.id.sigunup_txt);
        sigunup_txt.setOnClickListener(this);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        updateViewPager();
    }

    private void updateViewPager() {
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this, ImagesArray));
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == IMAGES.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
         swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.location_self_edttxt:
//                try {
//                    Intent intent =
//                            new PlaceAutocomplete
//                                    .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                                    .build(MainActivity.this);
//                    startActivityForResult(intent, 2);
//                } catch (GooglePlayServicesRepairableException e) {
//                    // TODO: Handle the error.
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    // TODO: Handle the error.
//                }
                break;

            case R.id.current_location_adress_img:
                currentLocation();
                break;

            case R.id.login_txt:
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
                break;

            case R.id.sigunup_txt:
                Intent signup = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signup);
                finish();
                break;

        }
    }

    // A place has been received; use requestCode to track the request.
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                // retrive the data by using getPlace() method.
//                place = PlaceAutocomplete.getPlace(this, data);
//
//                latlangObj = place.getLatLng();
//                Log.v("latitude:", "" + latlangObj.latitude);
//                Log.v("longitude:", "" + latlangObj.longitude);
//
//                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
//
//                fromLocation = new Location("from");//provider name is unecessary
//                fromLocation.setLatitude(latlangObj.latitude);//your coords of course
//                fromLocation.setLongitude(latlangObj.longitude);
//                location_self_edttxt.setText(place.getName());
//
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                // TODO: Handle the error.
//                Log.e("Tag", status.getStatusMessage());
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // The user canceled the operation.
//            }
//        }
//
//
//        }


    private void currentLocation() {

        double latitude, longitude;
        LocationAddress locationAddress;

        Location gpslocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        Location networkLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (networkLocation != null) {
            String result = null;
            latitude = networkLocation.getLatitude();
            longitude = networkLocation.getLongitude();
            locationAddress = new LocationAddress();
            Geocoder gcd = new Geocoder(getBaseContext(), Locale
                    .getDefault());
            try {

                List<Address> addressList = gcd.getFromLocation(
                        latitude, longitude, 1);
                if (addressList != null && addressList.size() > 0) {
                    Address address = addressList.get(0);
                    StringBuilder sb = new StringBuilder();

                    sb.append(address.getAddressLine(0)).append(",");
                    sb.append(address.getAddressLine(1)).append(",");
                    sb.append(address.getAddressLine(2)).append(",");
                    sb.append(address.getAddressLine(3)).append(".");

                    result = sb.toString();

                    location_self_edttxt.setText(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (gpslocation != null) {

            try {
                latitude = gpslocation.getLatitude();
                longitude = gpslocation.getLongitude();
                locationAddress = new LocationAddress();
                locationAddress.getAddressFromLocation(latitude, longitude,
                        getApplicationContext(), new GeocoderHandler());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showSettingsAlert();
        }

    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            location_self_edttxt.setText(locationAddress);
        }
    }

    private void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog
                .setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });
        // ACTION_LOCATION_SOURCE_SETTINGS
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {

                }
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                } else {
                    if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    } else {
                        Log.e("set to never ask again", permission);
                    }
                }
                return false;
            }
        }
        return true;
    }
}


