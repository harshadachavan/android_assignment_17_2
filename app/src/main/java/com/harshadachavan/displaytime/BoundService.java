package com.harshadachavan.displaytime;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Calendar;

public class BoundService extends Service {
    //Creating Reference of IBinder interface andd creating object of DemoBinder.
    IBinder iBinder = new DemoBinder();

    @Nullable
    @Override
    ///onBind method to return IBinder.
    public IBinder onBind(Intent intent)
    {
        Toast.makeText(getApplicationContext(),"Service Bind",Toast.LENGTH_SHORT).show();
        return iBinder;   //returning IBinder reference.
    }

    //Method to show textMsg as Toast.
    public void showMessage(String s)
    {
        Toast.makeText(getApplicationContext(),"Message : "+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    //Method to onUnbind Service when activity is Destroyed.
    public boolean onUnbind(Intent intent) {

        Toast.makeText(getApplicationContext(),"Service UnBind",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    //Method which will give the time and Date instance.
    public String getTimeDateInstance()
    {
        //Creating current instance of Calender from util library of Java.
        Calendar calendar = Calendar.getInstance();

        //Getting time instances seconds, minute and hour in String
        String seconds = String.valueOf(calendar.get(Calendar.SECOND));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String hour = String.valueOf(calendar.get(Calendar.HOUR));

        //Getting date instances date,month and year in String.
        String date = String.valueOf(calendar.get(Calendar.DATE));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        //returning String.
        return "Date : "+date+"-"+month+"-"+year+"\n"+
                "Time : "+hour+":"+minute+":"+seconds;
    }

    //Creating nested DemoBinder class which is extending the Binder class.
    class DemoBinder extends Binder
    {
        //Method which will reurn MyBounderService instance.
        public BoundService getServiceInstance()
        {
            return BoundService.this;  //returning MyBounderService instance.
        }
    }
}
