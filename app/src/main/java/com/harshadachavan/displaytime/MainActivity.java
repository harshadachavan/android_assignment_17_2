package com.harshadachavan.displaytime;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Creating reference of classes whose elements are used in the layout.
    Button startBtn;
    TextView dateTimeShowTV;

    //Creating reference of MyBounderService.
    BoundService BoundService;
    boolean isBind;   //boolean variable which will save weather Service is Bind or not.

    //Creating object of Anonymous class ServiceConnection.
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        //Method to perform operation when Service is Bind.
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Typecasting IBinder reference passed in the method.
            BoundService.DemoBinder demoBinder = (BoundService.DemoBinder)service;
            BoundService = demoBinder.getServiceInstance();  //Getting the instance of MyBoundService class.
            BoundService.showMessage("Welcome to the Binder Service");   //Showing Toast.
            isBind = true;  //Setting isBind as true.

            //Setting the time and date to the TextView.
            dateTimeShowTV.setText(BoundService.getTimeDateInstance());
        }

        @Override
        //Method to perform operation when Service is Unbind.
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;    //Setting isBind as true.
        }
    };

    @Override
    //onCreate method.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //Setting Content view.

        //Setting references with their IDs.
        startBtn=(Button)findViewById(R.id.start_btn);
        dateTimeShowTV=(TextView)findViewById(R.id.date_time_tv);

        //Setting onClick listener to the Button.
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating Service intent.
                Intent boundService = new Intent(MainActivity.this,BoundService.class);

                //bind Service by follwing Method.
                bindService(boundService,serviceConnection,BIND_AUTO_CREATE);

                //dateTimeShowTV.setText(myBoundService.getTimeDateInstance());
            }
        });
    }
}