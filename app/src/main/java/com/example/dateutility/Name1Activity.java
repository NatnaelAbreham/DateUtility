package com.example.dateutility;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Name1Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button getname;
    EditText day, year;
    int month = 0;
    int d,m,y,leapcounter = 1, daycounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name1);

        getname = (Button) findViewById(R.id.getname);
        day = (EditText) findViewById(R.id.day);
        // month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);

        Spinner spinner = findViewById(R.id.months);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        getname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                try{



                    d = Integer.parseInt(day.getText().toString());
                    m = month;
                    y = Integer.parseInt(year.getText().toString());


                    if(y<1){
                        Toast.makeText(Name1Activity.this,"invalid year",Toast.LENGTH_LONG).show();
                    }else if((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)){
                        if(d >0 && d < 32){
                            getDate(d,m,y);
                        }else{
                            Toast.makeText(Name1Activity.this,"invalid day",Toast.LENGTH_LONG).show();
                        }
                    }else if(m == 4 || m == 6 || m == 9 || m == 11){
                        if(d > 0 && d < 31){
                            getDate(d,m,y);
                        }else{
                            Toast.makeText(Name1Activity.this,"invalid day",Toast.LENGTH_LONG).show();
                        }
                    }else if(m == 2 && (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0))){
                        if(d >0 && d < 30){
                            getDate(d,m,y);
                        }else{
                            Toast.makeText(Name1Activity.this,"invalid day",Toast.LENGTH_LONG).show();
                        }
                    }else if(m == 2){
                        if(d >0 && d < 29){
                            getDate(d,m,y);
                        }else{
                            Toast.makeText(Name1Activity.this,"invalid day",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(Name1Activity.this,"invalid month",Toast.LENGTH_LONG).show();
                    }

                }catch(NullPointerException e){
                    Toast.makeText(Name1Activity.this,"All field required",Toast.LENGTH_LONG).show();

                }catch(NumberFormatException e){
                    if(day.getText().toString().equals("")   ||year.getText().toString().equals(""))
                        Toast.makeText(Name1Activity.this,"All field required",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Name1Activity.this,"Integer number required",Toast.LENGTH_LONG).show();

                }catch(Exception e){
                    Toast.makeText(Name1Activity.this,"invalid date",Toast.LENGTH_LONG).show();

                }
            }
        });

    }


    public void getDate(int d, int m, int y) {

        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;

        if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
            for (int i = 0; i < m; i++) {
                sum += a1[i];
            }
        }else{
            for (int i = 0; i < m; i++) {
                sum += a2[i];
            }
        }
        y--;

        y = (y*365)+(y/4)+(sum);
        show (y % 7);

        day.setText("");
        year.setText("");
    }
    public  void  showmsg(String Title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getTitle());
        builder.setMessage(message);
        builder.show();
    }
    public void show(int c){
        switch(c){

            case 2:
                showmsg("selected data","The day is Monday");
                break;
            case 3:
                showmsg("selected data","The day is Tuesday");  break;
            case 4:
                showmsg("selected data","The day is Wednesday");   break;
            case 5:
                showmsg("selected data","The day is Thursday"); break;
            case 6:
                showmsg("selected data","The day is Friday");    break;
            case 0:
                showmsg("selected data","The day is Saturday");     break;
            case 1:
                showmsg("selected data","The day is sunday");  break;

        }
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
        month = indexOfMonth( text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}