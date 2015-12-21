package com.example.mutairi.termproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

public class DoingInfoActivity extends AppCompatActivity {
    Context mContext;
    GoogleMap map;

    TextView titleText;
    TextView dateText;
    TextView addressText;

    ImageView imageView;

    int logId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_info);
        mContext = this;

        titleText = (TextView)findViewById(R.id.titleText);
        dateText = (TextView)findViewById(R.id.dateText);
        addressText = (TextView)findViewById(R.id.addressText);
        imageView = (ImageView)findViewById(R.id.titleImage);

        Intent intent = getIntent();
        logId = intent.getIntExtra("logId", -1);
        titleText.setText(intent.getStringExtra("title"));
        dateText.setText(
                intent.getIntExtra("year", 0) + "." +
                intent.getIntExtra("month", 0) + "." +
                intent.getIntExtra("day", 0) + " " +
                intent.getIntExtra("hour",0) + ":" +
                intent.getIntExtra("minute", 0)
        );
        addressText.setText(intent.getStringExtra("address"));
        imageView.setImageDrawable(getTitleImage(intent.getStringExtra("title")));


        Double latitude = intent.getDoubleExtra("latitude", 0.0);
        Double longitude = intent.getDoubleExtra("longitude", 0.0);

        if(latitude != 0.0 && longitude != 0.0)
        {
            SupportMapFragment fragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
            map = fragment.getMap();
            LatLng curPoint = new LatLng(latitude, longitude);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.setMyLocationEnabled(true);

        }

    }

    public void backButtonClicked(View v)
    {
        finish();
    }

    public void deleteButtonClicked(View v)
    {
        if(logId != -1)
        {
            if(SQLiteDAO.deleteTrace(logId))
            {
                Toast.makeText(mContext, "delete complete", Toast.LENGTH_LONG).show();
                MainActivity.dbCursor.requery();
                finish();
            }
            else
            {
                Toast.makeText(mContext, "delete fail", Toast.LENGTH_LONG).show();
            }
        }

    }

    public Drawable getTitleImage(String title)
    {
        Resources res = getResources();
        if(title.equals(DefineConstant.MEAL))
        {
            return res.getDrawable(R.drawable.meal);
        }
        else if(title.equals(DefineConstant.STUDY)) {
            return res.getDrawable(R.drawable.study);
        }
        else if(title.equals(DefineConstant.LECTURE)) {
            return res.getDrawable(R.drawable.lecture);
        }
        else if(title.equals(DefineConstant.REST)) {
            return res.getDrawable(R.drawable.rest);
        }
        else if(title.equals(DefineConstant.DRINK)) {
            return res.getDrawable(R.drawable.drink);
        }
        else if(title.equals(DefineConstant.SLEEP))
        {
            return res.getDrawable(R.drawable.sleep);
        }
        else if(title.equals(DefineConstant.HOBBY)) {
            return res.getDrawable(R.drawable.hobby);
        }
        else{
            return res.getDrawable(R.drawable.others);
        }
    }


}
