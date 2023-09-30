package com.example.dateutility;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Change1Activity extends AppCompatActivity {

    Button getdate,numofmonth;
    EditText day,month,year;
    int[] chake = new int[3];
    String day_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change1);

        getdate = (Button) findViewById(R.id.getname);
        numofmonth = (Button) findViewById(R.id.monthnumber);
        day = (EditText) findViewById(R.id.day);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);

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

        getdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int d,m,y,z;

                    d = Integer.parseInt(day.getText().toString());
                    m = Integer.parseInt(month.getText().toString());
                    y = Integer.parseInt(year.getText().toString());

                    z= y+1;
                    if(y<1){
                        Toast.makeText(Change1Activity.this,"invalid year",Toast.LENGTH_LONG).show();

                    }
                    else if ((z % 400 == 0) || (z % 4 == 0 && z % 100 != 0)) {
                        if (m > 0 && m < 13) {
                            if (d > 0 && d < 31) {
                                getGregorianDate(d, m, y);
                            } else {
                                Toast.makeText(Change1Activity.this,"invalid day",Toast.LENGTH_LONG).show();

                            }
                        } else if (m == 13) {
                            if (d > 0 && d < 7) {
                                getGregorianDate(d, m, y);
                            } else {
                                Toast.makeText(Change1Activity.this,"invalid day",Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(Change1Activity.this,"invalid month",Toast.LENGTH_LONG).show();

                        }
                    } else {
                        if (m > 0 && m < 13) {
                            if (d > 0 && d < 31) {
                                getGregorianDate(d, m, y);
                            } else {
                                Toast.makeText(Change1Activity.this,"invalid day",Toast.LENGTH_LONG).show();

                            }
                        } else if (m == 13) {
                            if (d > 0 && d < 6) {
                                getGregorianDate(d, m, y);
                            } else {
                                Toast.makeText(Change1Activity.this,"invalid day",Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(Change1Activity.this,"invalid month",Toast.LENGTH_LONG).show();

                        }
                    }
                }catch(NumberFormatException e){
                    if(day.getText().toString().equals("") || month.getText().toString().equals("") ||year.getText().toString().equals(""))
                        Toast.makeText(Change1Activity.this,"All field required",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Change1Activity.this,"Integer number required",Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(Change1Activity.this,"invalid date",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public  void  showmsg(String Title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getTitle());
        builder.setMessage(message);
        builder.show();
    }
    void getGregorianDate(int d,int m,int y){

        int gy;
        gy = y + 7;

        for(int i=gy;i<=(y+9);i++){
            for(int j=1;j<13;j++){
                for(int k=1;k<32;k++){

                    toGregorian(k,j,i);
                    if(y==chake[2]&& m== chake[1] && d==chake[0]){

                        getDate(k,j,i);

                        StringBuffer buffer=new StringBuffer();

                        buffer.append("Gregorian Calendar" +"\n");
                        //buffer.append("     "+ day_name  +"\n");
                        buffer.append(day_name +"," +k+" - "+indexOfMonth(j)+" - "+i);
                        //show selected data
                        showmsg("selected data",buffer.toString());
                        return;
                    }
                }
            }
        }

    }
    public void getDate(int d, int m, int y) {

        int a1[] = {d, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int a2[] = {d, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;

        if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
            for (int i = 0; i < m; i++) {
                sum += a1[i];
            }
        } else {
            for (int i = 0; i < m; i++) {
                sum += a2[i];
            }
        }
        y--;

        y = (y*365)+(y/4)+(sum);
        showDay(y % 7);


    }
    public String showDay(int c){

        switch(c){


            case 2:
                day_name = "Monday";
                break;
            case 3:
                day_name = "Tuesday";
                break;
            case 4:
                day_name = "Wednesday";
                break;
            case 5:
                day_name = "Thursday";
                break;
            case 6:
                day_name = "Friday";
                break;
            case 0:
                day_name = "Saturday";
                break;
            case 1:
                day_name = "Sunday";
                break;

        }
        return  day_name ;
    }

    public  void toGregorian(int d,int m,int y){
        int ed, ey, em;//e stands for Ethiopia
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

                //JOptionPane.showMessageDialog(null, "Day    Month   Year\n" + ed + "         " + em + "            " + ey, "ETHIOPIAN CALENDAR", JOptionPane.PLAIN_MESSAGE);
                chake[0] = ed;
                chake[1] = em;
                chake[2] = ey;

            } else {
                // Toast.makeText(Change1Activity.this,"invalid date",Toast.LENGTH_LONG).show();
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

                chake[0] = ed;
                chake[1] = em;
                chake[2] = ey;

            } else {
                // Toast.makeText(Change1Activity.this,"invalid date",Toast.LENGTH_LONG).show();
            }

        }
    }

    public static String indexOfMonth(int s) {
        String monthindex = "";
        switch (s) {
            case 1:
                monthindex = "JANUARY";
                break;
            case 2:
                monthindex = "FEBRUARY";
                break;
            case 3:
                monthindex = "MARCH";
                break;
            case 4:
                monthindex = "APRIL";
                break;
            case 5:
                monthindex = "MAY";
                break;
            case 6:
                monthindex ="JUNE";
                break;
            case 7:
                monthindex = "JULY";
                break;
            case 8:
                monthindex = "AUGUST";
                break;
            case 9:
                monthindex = "SEPTEMBER";
                break;
            case 10:
                monthindex = "OCTOBER";
                break;
            case 11:
                monthindex = "NOVEMBER";
                break;
            case 12:
                monthindex = "DECEMBER";
                break;
        }
        return monthindex;
    }

}