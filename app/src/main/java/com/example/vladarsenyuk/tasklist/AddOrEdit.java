package com.example.vladarsenyuk.tasklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.action;

/**
 * Created by VladArsnyuk on 24.10.2016.
 */
public class AddOrEdit extends AppCompatActivity {

    private EditText taskTitle;
    private TextView taskInfo;
    private ImageView taskImage;
    private RatingBar taskPriority;
    Intent currentIntent;
    final private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_edit);

        taskTitle = (EditText) findViewById(R.id.titleEditText);
        taskInfo = (EditText) findViewById(R.id.infoEditText);
        taskImage = (ImageView) findViewById(R.id.imageView);
        taskPriority = (RatingBar) findViewById(R.id.ratingBar);

        ActionBar bar = getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);

        currentIntent = getIntent();

        if(currentIntent.getAction() == "EDIT"){
            bar.setTitle("Edit");
            taskTitle.setText(currentIntent.getStringExtra("title"));
            taskInfo.setText(currentIntent.getStringExtra("info"));
            taskImage.setImageResource(currentIntent.getIntExtra("img", R.mipmap.ic_launcher));
            taskPriority.setRating(currentIntent.getFloatExtra("priority", (float) 0));
        }
        if (currentIntent.getAction() == "CREATE"){
            bar.setTitle("Create");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater menuInflator = getMenuInflater();
            menuInflator.inflate(R.menu.add_edit_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                if(taskTitle.getText().length() > 0 && taskInfo.getText().length() > 0){
                    Intent intent = new Intent();
                    Date d = new Date();
                    intent.putExtra("title", taskTitle.getText().toString());
                    intent.putExtra("info", taskInfo.getText().toString());
                    intent.putExtra("date", dateFormat.format(d));
                    intent.putExtra("priority", taskPriority.getRating());
                    intent.putExtra("img", R.mipmap.ic_launcher);
                    intent.putExtra("position", currentIntent.getIntExtra("position", 0));
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(this, "Fill empty fields", Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }


}
