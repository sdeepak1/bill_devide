package com.example.billdevide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText total_unit,total_amount,total_person; //edit text variable
    Button next;//button variable
    RecyclerView person_view;
    adapter adapter;
   public static List<person_model> list=new ArrayList<>();
    Dialog dialog1;
    float per_unit_cost;
    TextView balance_unit,balance_amount;
    int i=0,check_btn_state=0;//i for dialog box looping and check_btn handle the next button or reset function
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edit text connect here
        total_unit=findViewById(R.id.TotalUnit);
        total_amount=findViewById(R.id.totalAmount);
        total_person=findViewById(R.id.totalPerson);

        //button connect here
        next=findViewById(R.id.Next);

        //text view connect here
        balance_amount=findViewById(R.id.balance_amount);
        balance_unit=findViewById(R.id.balance_unit);

        //recycler view connect here
        person_view=findViewById(R.id.person_view);

        MobileAds.initialize(this, initializationStatus -> {
        });


        bannerAds();
        next.setOnClickListener(view -> {
            if(check_btn_state==0){
                click_btn();
                check_btn_state=1;
                next.setText("Reset");
            }else{
                resetData();
            }

        });
    }

    public void click_btn(){
        if(total_unit.getText().toString().isEmpty()){
            total_unit.setError("This field Required");
            return;
        }
        if(total_amount.getText().toString().isEmpty()){
            total_amount.setError("This field Required");
            return;
        }
        if(total_person.getText().toString().isEmpty()){
            total_person.setError("This field Required");
            return;
        }

        per_unit_cost=(float) Integer.parseInt(total_amount.getText().toString())/Integer.parseInt(total_unit.getText().toString());
        getUserData();
    }
    @SuppressLint("SetTextI18n")

    //load data from list into recycler view
    public void showData(){
        int totalUnit=0;
        int totalAmount=0;
        adapter=new adapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        person_view.setLayoutManager(layoutManager);
        person_view.setAdapter(adapter);
        //call the adapter function
        for(int i=0;i<list.size();i++){
            totalUnit=totalUnit+list.get(i).getConsume_unit();
            totalAmount=totalAmount+list.get(i).getTotal_amount();
        }
        totalUnit=Integer.parseInt(total_unit.getText().toString())-totalUnit;
        totalAmount=Integer.parseInt(total_amount.getText().toString())-totalAmount;

        balance_unit.setText(Integer.toString(totalUnit));
        balance_amount.setText(Integer.toString(totalAmount));

    }


    //get user data using dialog box into list
    @SuppressLint("SetTextI18n")
    public void getUserData(){
        //for dialog box
        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog_box);
        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false);
        dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


        Button dialog_next = dialog1.findViewById(R.id.enter_person_data);
        EditText old=dialog1.findViewById(R.id.Enter_old_reading);
        EditText New=dialog1.findViewById(R.id.Enter_new_reading);
        EditText person_name=dialog1.findViewById(R.id.Edit_person_name);
        TextView msg=dialog1.findViewById(R.id.enter_dialog_text);
        msg.setText("Please Enter "+(i+1)+" Person Details Out Of "+Integer.parseInt(total_person.getText().toString())+" Person");
        dialog1.show();
        dialog_next.setOnClickListener(view -> {
           if(old.getText().toString().isEmpty()){
               old.setError("this field Required");
               return;
           }

           if(New.getText().toString().isEmpty()){
               New.setError("this field Required");
               return;
           }
           if(person_name.getText().toString().isEmpty()){
               person_name.setText("Person"+1);
           }

           int consumeUnit=Integer.parseInt(New.getText().toString())-Integer.parseInt(old.getText().toString());
            int Amount= (int) (consumeUnit*per_unit_cost);

            list.add(new person_model(person_name.getText().toString(),
                   Integer.parseInt(old.getText().toString()),
                   Integer.parseInt(New.getText().toString()),
                   consumeUnit,Amount,per_unit_cost));

            i++;
            dialog1.dismiss();
            if(i<Integer.parseInt(total_person.getText().toString())){
                getUserData();
            }else{
                showData();
            }
        });


    }



    //reset data in text,edittext,and recycler view
    @SuppressLint("SetTextI18n")
    public void resetData(){
        check_btn_state=0;
        next.setText("Next");
        list.clear();
        total_unit.setText("");
        total_person.setText("");
        total_amount.setText("");
        balance_unit.setText("");
        balance_amount.setText("");

        adapter=new adapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        person_view.setLayoutManager(layoutManager);
        person_view.setAdapter(adapter);

    }

    //load banner ads
    public void bannerAds(){
        AdView adView;
        adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


}