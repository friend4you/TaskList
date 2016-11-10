package com.example.vladarsenyuk.tasklist;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private ListView taskListView;
    private Button addBtn;
    private ArrayAdapter<Task> taskArrayAdapter;

    public ArrayList<Task> taskList;
    final private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    int itemPosition;

    MenuItem search_menu_item;
    android.widget.SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**ListFragment listFragment = new ListFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment, listFragment);
        ft.commit();*/

        java.util.Date d = new Date();
        taskList = new ArrayList<Task>();
        taskListView = (ListView) findViewById(R.id.listView);


        for (int i = 1; i<= 3; i++){
            Task t = new Task("Title " + i, "Info about task # " + i, (float) 1.5,  dateFormat.format(d) , R.mipmap.ic_launcher);

            taskList.add(t);
        }

        taskArrayAdapter = new CustomAdapter(this, taskList);
        taskListView.setAdapter(taskArrayAdapter);
        registerForContextMenu(taskListView);

        ActionBar bar = getSupportActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Edit Task");
        menu.add(0, 2, 0, "Delete Task");
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        itemPosition = acmi.position;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Task current = taskList.get(itemPosition);
        switch (item.getItemId()){
            case(1):
                Intent myIntent = new Intent(this, AddOrEdit.class);
                myIntent.setAction("EDIT");
                myIntent.putExtra("position", itemPosition);
                myIntent.putExtra("title", current.getName());
                myIntent.putExtra("info", current.getInfo());
                myIntent.putExtra("date", current.getDateS());
                myIntent.putExtra("priority", current.getPriority());
                myIntent.putExtra("img", current.getImg());
                startActivityForResult(myIntent, 102);
                break;
            case(2):
                taskList.remove(itemPosition);
                taskArrayAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflator = getMenuInflater();
        menuInflator.inflate(R.menu.main, menu);
        search_menu_item = menu.findItem(R.id.searchView);
        search_view = (android.widget.SearchView) search_menu_item.getActionView();
        search_view.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                taskArrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null) {
            int position = data.getIntExtra("position", 0);
            Task task = new Task(data.getStringExtra("title"), data.getStringExtra("info"), data.getFloatExtra("priority", 0),
                    data.getStringExtra("date"), data.getIntExtra("img", R.mipmap.ic_launcher));
            if (requestCode == 101 && resultCode == RESULT_OK) {
                taskArrayAdapter.add(task);
                taskArrayAdapter.notifyDataSetChanged();
            }
            if (requestCode == 102 && resultCode == RESULT_OK) {
                taskList.set(position, task);
                taskArrayAdapter.notifyDataSetChanged();
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note_icon:
                Intent myIntent = new Intent(this,
                        AddOrEdit.class);
                myIntent.setAction("android.intent.action.CREATE");
                startActivityForResult(myIntent, 101);
        }
        return super.onOptionsItemSelected(item);
    }


    public void onAddClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddOrEdit.class);
        startActivity(intent);
    }

}
