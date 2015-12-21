package com.example.mutairi.termproject;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistActivity extends FragmentActivity {
    Context mContext;
    GoogleMap map;
    LocationManager manager;
    MyLocationListener listener;
    Spinner spinner;
    SpinnerAdapter spinnerAdapter;

    EditText titleText;

    String title;
    String address;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    Double latitude;
    Double longitude;

    final String[] spinnerItem = {"click to select",
            DefineConstant.MEAL,
            DefineConstant.STUDY,
            DefineConstant.LECTURE,
            DefineConstant.REST,
            DefineConstant.DRINK,
            DefineConstant.SLEEP,
            DefineConstant.HOBBY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        mContext = this;

        titleText = (EditText)findViewById(R.id.editText);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinnerAdapter = new SpinnerAdapter();

        SupportMapFragment fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        map = fragment.getMap();

        manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener();

        spinner.setAdapter(spinnerAdapter);



    }

    public void addButtonClicked(View v)
    {
        title = titleText.getText().toString();
        year = Integer.valueOf(new SimpleDateFormat("yyyy", Locale.KOREA).format(new Date(System.currentTimeMillis())));
        month = Integer.valueOf(new SimpleDateFormat("MM", Locale.KOREA).format(new Date(System.currentTimeMillis())));
        day = Integer.valueOf(new SimpleDateFormat("dd", Locale.KOREA).format(new Date(System.currentTimeMillis())));
        hour = Integer.valueOf(new SimpleDateFormat("hh", Locale.KOREA).format(new Date(System.currentTimeMillis())));
        minute = Integer.valueOf(new SimpleDateFormat("mm", Locale.KOREA).format(new Date(System.currentTimeMillis())));

        if(title.length()>0)
        {
            SQLiteDAO.insertNewTraceInfo(title, address, year, month, day, hour, minute, latitude, longitude);

            MainActivity.dbCursor.requery();
            finish();

        }
        else
        {
            Toast.makeText(mContext, "title is too short", Toast.LENGTH_LONG).show();
        }

    }

    public void backButtonClicked(View v)
    {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(manager!=null)
        {
            manager.removeUpdates(listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyLocation();
    }

    public void showCurrentMap(Double latitude, Double longitude)
    {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
    }

    public void getAddress()
    {
        Locale.setDefault(Locale.KOREAN);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        String juso = "";

        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 3);

            for (Address address : addressList) {
                int index = address.getMaxAddressLineIndex();
                if (juso.equals("")) {
                    for (int i = 0; i <= index; i++) {
                        juso = juso + (address.getAddressLine(i));
                    }
                }

            }
            address = juso;
            juso = "";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMyLocation()
    {
        long minTime = 10000;
        float minDistance = 0;

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener);

        Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation!=null)
        {
            latitude = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
            getAddress();
        }
    }

    class MyLocationListener implements android.location.LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            showCurrentMap(latitude, longitude);
            getAddress();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    class SpinnerAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return spinnerItem.length;
        }

        @Override
        public Object getItem(int position) {
            return spinnerItem[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int index = position;
            TextView textView = new TextView(mContext);
            textView.setTextSize(20.0f);
            textView.setText(spinnerItem[position]);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(index == 0)
                            {
                                titleText.setText("");
                                titleText.setHint("input personally");
                                titleText.setEnabled(true);
                            }
                            else
                            {
                                titleText.setText(spinnerItem[index]);
                                titleText.setEnabled(false);
                            }
                        }
             });

            return textView;
        }
    }
}
