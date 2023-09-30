package com.example.dateutility;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Month;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Button toeth,togre,currentdate;
    Button getname,getnameg;
    int gday,gmonth,gyear;
    Button ibutton;
    String dayname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibutton = (Button) findViewById(R.id.i);
        toeth = (Button) findViewById(R.id.toethiopia);
        getname = (Button) findViewById(R.id.dayname);
        getnameg = (Button) findViewById(R.id.daynameg);
        togre = (Button) findViewById(R.id.togregorian);
        currentdate = (Button) findViewById(R.id.currentdate);

        Format f = new SimpleDateFormat("EEEE");
        dayname = f.format(new Date());

        currentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDate currentdate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    currentdate = LocalDate.now();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    gday = currentdate.getDayOfMonth();
                }
                Month month1 = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    month1 = currentdate.getMonth();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    gyear = currentdate.getYear();
                }
                String month = month1.toString();

                getDate(gday,indexOfMonth(month),gyear,month);

            }
        });

        toeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChangeActivity.class);
                startActivity(intent);
            }
        });
        ibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IActivity.class);
                startActivity(intent);
            }
        });

        togre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Change1Activity.class);
                startActivity(intent);
            }
        });
        getname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),NameActivity.class);
                startActivity(intent);
            }
        });

        getnameg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),Name1Activity.class);
                startActivity(intent);
            }
        });
    }
    public   void getDate(int d,int m,int y,String mname){

        int ed, ey, em;//e stands for Ethiopia
        try{

            em = 5;
            ed = 0;
            int sum = 0;
            ey = y - 8;
            int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if ((y % 400 == 0) || ((y % 4 == 0) && !(y % 100 == 0))) {
                if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                        || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 30))) {
                    for (int i = 0; i < m; i++) {
                        sum = sum + a1[i];
                    }
                    for (int i = 1; i <= sum; i++) {

                        if (i < 10) {
                            ed = 21 + i;
                            if (i > 1) {
                                continue;
                            }
                            em--;
                        } else {
                            ed++;
                            if (ed == 31) {
                                ed = 1;
                                em++;
                            }
                            int eyc = ey;
                            eyc++;
                            if (em == 13) {
                                if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                    if (ed == 7) {
                                        ed = 1;
                                        em++;
                                    }
                                } else {
                                    if (ed == 6) {
                                        ed = 1;
                                        em++;
                                    }
                                }
                            }
                            if (em > 13) {
                                if (em == 14) {
                                    ey++;
                                }
                                em -= 13;
                            }
                        }
                    }
                    StringBuffer buffer=new StringBuffer();

                    buffer.append("         " +dayname.toUpperCase() +"\n");
                    buffer.append("\nGregorian Calendar" +"\n");
                    buffer.append(d +" - "+mname+" - "+y+"\n\n");
                    buffer.append("\nEthiopian Calendar" +"\n");
                    buffer.append(ed +" - "+em+" - "+ey+"\n");
                    //show selected data
                    showmsg("selected data",buffer.toString());

                } else {
                    Toast.makeText(MainActivity.this,"invalid date",Toast.LENGTH_LONG).show();
                }
            } else {
                if (((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && (d > 0 && d < 32))
                        || ((m == 4 || m == 6 || m == 9 || m == 11) && (d > 0 && d <= 30)) || (m == 2 && (d > 0 && d < 29))) {
                    for (int i = 0; i < m; i++) {
                        sum = sum + a2[i];
                    }
                    for (int i = 1; i <= sum; i++) {

                        if (i < 9) {
                            ed = 22 + i;
                            if (i > 1) {
                                continue;
                            }
                            em--;
                        } else {
                            ed++;
                            if (ed == 31) {
                                ed = 1;
                                em++;
                            }
                            int eyc = ey;
                            eyc++;
                            if (em == 13) {
                                if ((eyc % 400 == 0) || ((eyc % 4 == 0) && !(eyc % 100 == 0))) {
                                    if (ed == 7) {
                                        ed = 1;
                                        em++;
                                    }
                                } else {
                                    if (ed == 6) {
                                        ed = 1;
                                        em++;
                                    }
                                }
                            }
                            if (em > 13) {
                                if (em == 14) {
                                    ey++;
                                }
                                em -= 13;
                            }
                        }
                    }

                    StringBuffer buffer=new StringBuffer();

                    buffer.append("         " +dayname +"\n");
                    buffer.append("\nGregorian Calendar" +"\n");
                    buffer.append(d +" - "+mname+" - "+y+"\n");
                    buffer.append("\nEthiopian Calendar" +"\n");
                    buffer.append(ed +" - "+em+" - "+ey+"\n");

                    //show selected data
                    showmsg("selected data",buffer.toString());      } else {
                    Toast.makeText(MainActivity.this,"invalid date",Toast.LENGTH_LONG).show();
                }

            }


        }catch(NullPointerException e){
            Toast.makeText(MainActivity.this,"All field required",Toast.LENGTH_LONG).show();

        }catch(NumberFormatException e){
            Toast.makeText(MainActivity.this,"Integer number required",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            Toast.makeText(MainActivity.this,"invalid date",Toast.LENGTH_LONG).show();

        }
    }
    public  void  showmsg(String Title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getTitle());
        builder.setMessage(message);
        builder.show();
    }
    public static int indexOfMonth(String s) {
        int monthindex = 0;
        switch (s) {
            case "JANUARY":
                monthindex = 1;
                break;
            case "FEBRUARY":
                monthindex = 2;
                break;
            case "MARCH":
                monthindex = 3;
                break;
            case "APRIL":
                monthindex = 4;
                break;
            case "MAY":
                monthindex = 5;
                break;
            case "JUNE":
                monthindex = 6;
                break;
            case "JULY":
                monthindex = 7;
                break;
            case "AUGUST":
                monthindex = 8;
                break;
            case "SEPTEMBER":
                monthindex = 9;
                break;
            case "OCTOBER":
                monthindex = 10;
                break;
            case "NOVEMBER":
                monthindex = 11;
                break;
            case "DECEMBER":
                monthindex = 12;
                break;
        }
        return monthindex;
    }
}