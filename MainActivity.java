package com.ramation.foldingcell.examples.celstream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    List<String> dataSet = null;
    Handler handlingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataSet = getImage(MainActivity.this);
                    Message msgObj = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("message", "load");
                    msgObj.setData(b);
                    handler.sendMessage(msgObj);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
            }
        });
    }


    private List<String> getImage(Context context) throws IOException
    {
        AssetManager assetManager = context.getAssets();
        String[] files = assetManager.list("");
        List<String> it= Arrays.asList(files);
        return it;
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


    public void displayData() {
        PhotoAdapter adapter = new PhotoAdapter(dataSet, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

}
