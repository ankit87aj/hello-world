package com.ramation.foldingcell.examples.collapsing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    //ui control
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    CardAdapter adapter;
    List<Flower> flowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);

        collapsingToolbarLayout.setTitle("Demo");
        setSupportActionBar(toolbar);

        //recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    initializeData();
                    Message msgObj = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("message", "load");
                    msgObj.setData(b);
                    handler.sendMessage(msgObj);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "You clicked on the fab", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void initializeData() {
        flowers = new ArrayList<>();
        flowers.add(new Flower("Flower 1", R.drawable.image2));
        flowers.add(new Flower("Flower 2", R.drawable.images3));
        flowers.add(new Flower("Flower 3", R.drawable.images4));
        flowers.add(new Flower("Flower 4", R.drawable.images6));
        flowers.add(new Flower("Flower 5", R.drawable.images7));
        flowers.add(new Flower("Flower 6", R.drawable.images10));
        flowers.add(new Flower("Flower 7", R.drawable.images11));
        flowers.add(new Flower("Flower 8", R.drawable.images12));
        flowers.add(new Flower("Flower 9", R.drawable.images14));
        flowers.add(new Flower("Flower 10", R.drawable.images17));
        flowers.add(new Flower("Flower 11", R.drawable.images18));
        flowers.add(new Flower("Flower 12", R.drawable.index));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Define the Handler that receives messages from the thread and update the progress
    private final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            String aResponse = msg.getData().getString("message");

            if ((null != aResponse)) {

                if (aResponse.equalsIgnoreCase(aResponse)) {
                    displayData();
                } else {

                }
            } else {

                // ALERT MESSAGE
                Toast.makeText(
                        getBaseContext(),
                        "Not Got Response From Server.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void displayData() {
        adapter = new CardAdapter(flowers);

        //set adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}