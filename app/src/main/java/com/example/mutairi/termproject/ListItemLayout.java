package com.example.mutairi.termproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by june on 15. 12. 19.
 */
public class ListItemLayout extends LinearLayout {
    ImageView imageView;
    TextView titleText;
    TextView addressText;
    TextView dateText;
    LinearLayout itemLayout;

    public ListItemLayout(Context context) {
        super(context);
        init(context);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_layout, this, true);

        itemLayout = (LinearLayout)findViewById(R.id.itemLayout);
        imageView = (ImageView)findViewById(R.id.imageView);
        titleText = (TextView)findViewById(R.id.titleText);
        addressText = (TextView)findViewById(R.id.addressText);
        dateText = (TextView)findViewById(R.id.dateText);
    }


}
