package voca.xvocaandroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordDetails extends AppCompatActivity {

    public static final int MY_GPS_PERMISSION = 0;
    private MyLocationService myLocationService;
    private double lng = 0.0;
    private double lat = 0.0;
    private ArrayList<String> sentences;
    private Integer[] mThumbIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Toolbar toolbar = findViewById(R.id.toolbarWordDetails);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        displaySentenceList();
        displayImages();



    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent locationIntent = new Intent(this, MyLocationService.class);
        bindService(locationIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_word_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sentence_origin:
                getLocation();

               // Toast.makeText(this,String.format("%f %f", lng, lat),Toast.LENGTH_SHORT).show();
                //TODO: send ArrayList<location> sentencesLocation
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("lng", lng);
                intent.putExtra("lat", lat);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayImages(){

        Integer[] mThumbIds = {
                R.drawable.tile1,
                R.drawable.tile2,
                R.drawable.tile3,
                R.drawable.tile4,
                R.drawable.tile5,
                R.drawable.tile6,
                R.drawable.tile7,
                R.drawable.tile8,
        };

        GridView gridViewImg = findViewById(R.id.gridViewImages);
        //gridViewImg.setNumColumns(3);
        gridViewImg.setAdapter(new ImageAdapter(this, mThumbIds));

    }

    public void displaySentenceList()
    {
        getSentences("");

        ListView listView = findViewById(R.id.listViewSentences);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, sentences);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //TODO: Like?
            Toast.makeText(this,"Like",Toast.LENGTH_SHORT).show();
        });
    }

    public void getSentences(String word){
        sentences = new ArrayList<>(10);
        sentences.add("TEST Sentence");
        sentences.add("TEST Sentence1");
        sentences.add("TEST Sentence2");
        sentences.add("TEST Sentence3");

    }

    //TODO: add new sentence
    public void newSentence(){

    }



    //Find Location:

    private void getLocation() {
        Location location = null;
        if(myLocationService != null) {
            location = myLocationService.getCurrentLocation();
        }
        if (location != null) {
            lng = location.getLongitude();
            lat = location.getLatitude();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof MyLocationService.MyLocationBinder) {
                MyLocationService.MyLocationBinder binder = (MyLocationService.MyLocationBinder) service;
                myLocationService = binder.getService();
            }
            requestGPSPermission();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    private void requestGPSPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{ android.Manifest.permission.ACCESS_FINE_LOCATION },
                MY_GPS_PERMISSION);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_GPS_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(myLocationService != null) {
                        myLocationService.startUpdating();
                    }
                }
        }
    }


}
