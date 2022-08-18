package com.example.billdevide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
  public   List<person_model> list;
    public adapter(List<person_model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.setData(list.get(position).getName(),
                list.get(position).getOld_reading(),
                list.get(position).getNew_reading(),
                list.get(position).getConsume_unit(),
                list.get(position).getUnit_cost(),
                list.get(position).getTotal_amount(),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView person_name,old_reading,new_reading,total_consume_unit,unit_per_cost,Gross_amount;
        Button shareBill;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            person_name=itemView.findViewById(R.id.person_name);
            old_reading=itemView.findViewById(R.id.old_reading);
            new_reading=itemView.findViewById(R.id.new_reading);
            total_consume_unit=itemView.findViewById(R.id.total_consume_unit);
            unit_per_cost=itemView.findViewById(R.id.unit_per_cost);
            Gross_amount=itemView.findViewById(R.id.Gross_amount);
            shareBill=itemView.findViewById(R.id.shareBill);
        }

        @SuppressLint("SetTextI18n")
        private void setData(String name,int old,int New ,int TotalConsume,float unit,int Amount,int position){
            person_name.setText(name);
            old_reading.setText(Integer.toString(old));
            new_reading.setText(Integer.toString(New));
            total_consume_unit.setText(Integer.toString(TotalConsume));
            unit_per_cost.setText(Float.toString(unit));
            Gross_amount.setText(Integer.toString(Amount));

            //here we share the bill by other app
            shareBill.setOnClickListener(view -> {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String testToShare="Consumer Name = "+MainActivity.list.get(position).getName()+"\n" +
                                   "Old Reading   = "+MainActivity.list.get(position).getOld_reading()+"\n" +
                                   "New Reading   = "+MainActivity.list.get(position).getNew_reading()+"\n" +
                                   "Consume Unit  = "+MainActivity.list.get(position).getConsume_unit()+"\n"+
                                   "Unit per cost = "+MainActivity.list.get(position).getUnit_cost()+"\n"+
                                   "Total Amount  = "+MainActivity.list.get(position).getTotal_amount()+"\n";

                intent.putExtra(Intent.EXTRA_SUBJECT,"Bill Divide");
                intent.putExtra(Intent.EXTRA_TEXT,testToShare);
                itemView.getContext().startActivity(Intent.createChooser(intent,"Share Bill"));
            });
        }

    }
}
