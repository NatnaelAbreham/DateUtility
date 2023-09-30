package com.example.dateutility;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    Button getname;
    EditText day,month,year;
    int d,m,y,leapcounter = 1, daycounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        getname = (Button) findViewById(R.id.getname);
        day = (EditText) findViewById(R.id.day);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);

        getname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                try{


                    d = Integer.parseInt(day.getText().toString());
                    m = Integer.parseInt(month.getText().toString());
                    y = Integer.parseInt(year.getText().toString());

                    int z= y+1;

                    if (y < 1) {
                        Toast.makeText(NameActivity.this,"invalid year",Toast.LENGTH_LONG).show();
                    } else if (m < 1 || m > 13) {
                        Toast.makeText(NameActivity.this,"invalid month",Toast.LENGTH_LONG).show();
                    } else if ((z % 400 == 0) || (z % 4 == 0 && z % 100 != 0)) {
                        if (m > 0 && m < 13) {
                            if (d > 0 && d < 31) {
                                getDate(d, m, y);
                            } else {
                                Toast.makeText(NameActivity.this,"invalid day",Toast.LENGTH_LONG).show();
                            }
                        } else if (m == 13) {
                            if (d > 0 && d < 7) {
                                getDate(d, m, y);
                            } else {
                                Toast.makeText(NameActivity.this,"invalid day",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(NameActivity.this,"invalid month",Toast.LENGTH_LONG).show();

                        }
                    } else {
                        if (m > 0 && m < 13) {
                            if (d > 0 && d < 31) {
                                getDate(d, m, y);
                            } else {
                                Toast.makeText(NameActivity.this,"invalid day",Toast.LENGTH_LONG).show();
                            }
                        } else if (m == 13) {
                            if (d > 0 && d < 6) {
                                getDate(d, m, y);
                            } else {
                                Toast.makeText(NameActivity.this,"invalid day",Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(NameActivity.this,"invalid month",Toast.LENGTH_LONG).show();
                        }
                    }

                }catch(NullPointerException e){
                    Toast.makeText(NameActivity.this,"All field required",Toast.LENGTH_LONG).show();

                }catch(NumberFormatException e){
                    if(day.getText().toString().equals("") || month.getText().toString().equals("") ||year.getText().toString().equals(""))
                        Toast.makeText(NameActivity.this,"All field required",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(NameActivity.this,"Integer number required",Toast.LENGTH_LONG).show();

                }catch(Exception e){
                    Toast.makeText(NameActivity.this,"invalid date",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void getDate(int d, int m, int y) {

        y = ((y-1)*365)+(y/4)+((m-1)*30)+d;

        show (y % 7);
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

            case 6:
                showmsg("selected data","The day is Monday");
                break;
            case 0:
                showmsg("selected data","The day is Tuesday");  break;
            case 1:
                showmsg("selected data","The day is Wednesday");   break;
            case 2:
                showmsg("selected data","The day is Thursday"); break;
            case 3:
                showmsg("selected data","The day is Friday");    break;
            case 4:
                showmsg("selected data","The day is Saturday");     break;
            case 5:
                showmsg("selected data","The day is sunday");  break;

        }
    }
}