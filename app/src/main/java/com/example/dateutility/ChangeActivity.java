package com.example.dateutility;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;


public class ChangeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button getdate,numofmonth;
    EditText day, year;
    int month = 0;
    String day_name ="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        getdate = (Button) findViewById(R.id.getname);
        numofmonth = (Button) findViewById(R.id.monthnumber);
        day = (EditText) findViewById(R.id.day);
        //month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);
        Spinner spinner = findViewById(R.id.months);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        numofmonth.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer buffer=new StringBuffer();

                        buffer.append("Gregorian Calendar" +"\n");
                        buffer.append("No : "+"Month"+"\n");
                        buffer.append("01 : "+"January"+"\n");
                        buffer.append("02 : "+"February"+"\n");
                        buffer.append("03 : "+"March"+"\n");
                        buffer.append("04 : "+"April"+"\n");

                        buffer.append("05 : "+"May"+"\n");
                        buffer.append("06 : "+"June"+"\n");
                        buffer.append("07 : "+"Julay"+"\n");
                        buffer.append("08 : "+"August"+"\n");

                        buffer.append("09 : "+"September"+"\n");
                        buffer.append("10 : "+"October"+"\n");
                        buffer.append("11 : "+"November"+"\n");
                        buffer.append("12 : "+"december"+"\n");

                        //show selected data
                        showmsg("selected data",buffer.toString());
                    }
                }
        );
        getdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int d, m, y;
                        int ed, ey, em;//e stands for Ethiopia
                        try{

                            d = Integer.parseInt(day.getText().toString());
                            m = month;
                            y = Integer.parseInt(year.getText().toString());


                            em = 5;
                            ed = 0;
                            int sum = 0;
                            ey = y - 8;
                            int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                            int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                            if(y<1){
                                Toast.makeText(ChangeActivity.this,"invalid year",Toast.LENGTH_LONG).show();

                            }
                            else if ((y % 400 == 0) || ((y % 4 == 0) && !(y % 100 == 0))) {
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
                                    getDate(ed,em,ey);
                                    StringBuffer buffer=new StringBuffer();

                                    buffer.append("Ethiopian Calendar" +"\n");
                                    buffer.append("    "+day_name+"\n");
                                    buffer.append("Day : "+ed+"\n");
                                    buffer.append("Month : "+em+"\n");
                                    buffer.append("Year : "+ey+"\n");
                                    //show selected data
                                    showmsg("selected data",buffer.toString());

                                } else {
                                    Toast.makeText(ChangeActivity.this,"invalid date",Toast.LENGTH_LONG).show();
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
                                    getDate(ed,em,ey);
                                    StringBuffer buffer=new StringBuffer();

                                    buffer.append("Ethiopian Calendar" +"\n");
                                    buffer.append("     "+day_name+"\n");
                                    buffer.append("Day : "+ed+"\n");
                                    buffer.append("Month : "+em+"\n");
                                    buffer.append("Year : "+ey+"\n");
                                    //show selected data
                                    showmsg("selected data",buffer.toString());      } else {
                                    Toast.makeText(ChangeActivity.this,"invalid date",Toast.LENGTH_LONG).show();
                                }

                            }


                        }catch(NullPointerException e){
                            Toast.makeText(ChangeActivity.this,"All field required",Toast.LENGTH_LONG).show();

                        }catch(NumberFormatException e){
                            if(day.getText().toString().equals("")   ||year.getText().toString().equals(""))
                                Toast.makeText(ChangeActivity.this,"All field required",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(ChangeActivity.this,"Integer number required",Toast.LENGTH_LONG).show();

                        }catch(Exception e){
                            Toast.makeText(ChangeActivity.this,"invalid date",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );

    }

    public void getDate(int d, int m, int y) {

        y = ((y-1)*365)+(y/4)+((m-1)*30)+d;

        show (y % 7);
    }

    public String show(int c){

        switch(c){


            case 6:
                day_name = "Monday";
                break;
            case 0:
                day_name = "Tuesday";
                break;
            case 1:
                day_name = "Wednesday";
                break;
            case 2:
                day_name = "Thursday";
                break;
            case 3:
                day_name = "Friday";
                break;
            case 4:
                day_name = "Saturday";
                break;
            case 5:
                day_name = "Sunday";
                break;

        }
        return day_name ;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        month = indexOfMonth(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}