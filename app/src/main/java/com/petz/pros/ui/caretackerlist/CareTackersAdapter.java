package com.petz.pros.ui.caretackerlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ViewItemCareTackerBinding;

import java.util.List;

public class CareTackersAdapter extends RecyclerView.Adapter<CareTackersAdapter.MyViewHolder> {
 
    private List<CareTackersModel> moviesList;
    private ViewItemCareTackerBinding careTackerBinding;
    private CareTackerMvpView careTackerMvpView;
 
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }
 
 
    public CareTackersAdapter(List<CareTackersModel> moviesList, CareTackerMvpView careTackerMvpView) {
        this.moviesList = moviesList;
        this.careTackerMvpView = careTackerMvpView;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        careTackerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_item_care_tacker, parent, false);
 
        return new MyViewHolder(careTackerBinding.getRoot());
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CareTackersModel movie = moviesList.get(position);
        careTackerBinding.takerName.setText(movie.getmFirstName() +" "+ movie.getmLastName());
        careTackerBinding.takerEmail.setText("Email : "+movie.getmEmailId());
        careTackerBinding.takerMobile.setText("Mobile : "+movie.getmPhone());
        careTackerBinding.takerAddress.setText("Address : "+movie.getmAddress());
        careTackerBinding.takerCharge.setText("Charge per hour\n$"+String.valueOf(movie.getmChargePerHour()));

        careTackerBinding.itemBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(careTackerMvpView != null){
                    careTackerMvpView.onClickBookBtn(movie);
                }
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}