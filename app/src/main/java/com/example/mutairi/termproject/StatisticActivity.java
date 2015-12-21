package com.example.mutairi.termproject;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StatisticActivity extends Activity {
    public Cursor dbCursor;
    int mealCount = 0;
    int studyCount = 0;
    int lectureCount = 0;
    int restCount = 0;
    int drinkCount = 0;
    int sleepCount = 0;
    int hobbyCount = 0;
    int othersCount = 0;
    int total = 0;

    ProgressBar mealProgress;
    ProgressBar studyProgress;
    ProgressBar lectureProgress;
    ProgressBar restProgress;
    ProgressBar drinkProgress;
    ProgressBar sleepProgress;
    ProgressBar hobbyProgress;
    ProgressBar othersProgress;
    TextView mealText;
    TextView studyText;
    TextView lectureText;
    TextView restText;
    TextView drinkText;
    TextView sleepText;
    TextView hobbyText;
    TextView othersText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        dbCursor = SQLiteDAO.getTraceInfoCursor();
        Log.d("Count", ""+dbCursor.getCount());
        for(int i=0; i<dbCursor.getCount(); i++)
        {
            dbCursor.moveToPosition(i);
            String title = dbCursor.getString(dbCursor.getColumnIndex(SQLiteDB.TraceInfo.TITLE));

            Log.d("currentTitle:" , title);
            if(title.equals(DefineConstant.MEAL))
            {
                mealCount++;
            }
            else if(title.equals(DefineConstant.STUDY)) {
                studyCount++;
            }
            else if(title.equals(DefineConstant.LECTURE)) {
                lectureCount++;
            }
            else if(title.equals(DefineConstant.REST)) {
                restCount++;
            }
            else if(title.equals(DefineConstant.DRINK)) {
                drinkCount++;
            }
            else if(title.equals(DefineConstant.SLEEP))
            {
                sleepCount++;
            }
            else if(title.equals(DefineConstant.HOBBY)) {
            hobbyCount++;
            }
            else{
            othersCount++;
            }
        }


        total = mealCount + studyCount + lectureCount + restCount + drinkCount + sleepCount + hobbyCount + othersCount;

        if(total != 0)
        {
            int mealP = mealCount* 100/total ;
            int studyP = studyCount*100 /total ;
            int lectureP = lectureCount* 100 /total ;
            int restP = restCount* 100 /total ;
            int drinkP = drinkCount* 100 /total ;
            int sleepP = sleepCount* 100 / total;
            int hobbyP = hobbyCount* 100 / total;
            int othersP = othersCount* 100 / total;

            mealProgress = (ProgressBar)findViewById(R.id.mealProgress);
            mealText = (TextView)findViewById(R.id.mealText);
            mealProgress.setProgress(mealP);
            mealText.setText(mealP + "%");

            studyProgress = (ProgressBar)findViewById(R.id.studyProgress);
            studyText = (TextView)findViewById(R.id.studytext);
            studyProgress.setProgress(studyP);
            studyText.setText(studyP + "%");

            lectureProgress = (ProgressBar)findViewById(R.id.lectureProgress);
            lectureText = (TextView)findViewById(R.id.lectureText);
            lectureProgress.setProgress(lectureP);
            lectureText.setText(lectureP+"%");


            restProgress = (ProgressBar)findViewById(R.id.restProgress);
            restText = (TextView)findViewById(R.id.restText);
            restProgress.setProgress(restP);
            restText.setText(restP+"%");

            drinkProgress = (ProgressBar)findViewById(R.id.drinkProgress);
            drinkText = (TextView)findViewById(R.id.drinkText);
            drinkProgress.setProgress(drinkP);
            drinkText.setText(drinkP + "%");

            sleepProgress = (ProgressBar)findViewById(R.id.sleepProgress);
            sleepText = (TextView)findViewById(R.id.sleepText);
            sleepProgress.setProgress(sleepP);
            sleepText.setText(sleepP + "%");

            hobbyProgress = (ProgressBar)findViewById(R.id.hobbyProgress);
            hobbyText = (TextView) findViewById(R.id.hobbyText);
            hobbyProgress.setProgress(hobbyP);
            hobbyText.setText(hobbyP + "%");

            othersProgress = (ProgressBar)findViewById(R.id.othersProgress);
            othersText = (TextView)findViewById(R.id.othersText);
            othersProgress.setProgress(othersP);
            othersText.setText(othersP + "%");


        }

    }

    public void backButtonClicked(View v)
    {
        finish();
    }
}
