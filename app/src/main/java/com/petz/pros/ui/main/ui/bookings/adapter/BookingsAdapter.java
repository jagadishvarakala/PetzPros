package com.petz.pros.ui.main.ui.bookings.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ItemViewBookingsBinding;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;

import java.util.ArrayList;
 
public class BookingsAdapter
    extends RecyclerView.Adapter<BookingsAdapter.EmployeeViewHolder> {
 
  private ArrayList<BookingsModule> employees;
  private BookingsMvpView bookingsMvpView;
  private boolean isOwner;
 
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemViewBookingsBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_view_bookings, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }
 
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    BookingsModule currentStudent = employees.get(i);
    employeeViewHolder.employeeListItemBinding.setDetails(currentStudent);
    employeeViewHolder.employeeListItemBinding.setIsPetOwner(isOwner);

    employeeViewHolder.employeeListItemBinding.paymentBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(bookingsMvpView != null){
          bookingsMvpView.onClickPayBtn(currentStudent);
        }
      }
    });

    employeeViewHolder.employeeListItemBinding.trackBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(bookingsMvpView != null){
          bookingsMvpView.onClickTrackBtn(currentStudent);
        }
      }
    });

    employeeViewHolder.employeeListItemBinding.serviceStartBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(bookingsMvpView != null){
          bookingsMvpView.onClickStartService(currentStudent);
        }
      }
    });

    employeeViewHolder.employeeListItemBinding.resumeServiceBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(bookingsMvpView != null){
          bookingsMvpView.onClickServiceStart(currentStudent);
        }
      }
    });

    employeeViewHolder.employeeListItemBinding.endServiceBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(bookingsMvpView != null){
          bookingsMvpView.onClickEndService(currentStudent);
        }
      }
    });


  }
 
  @Override
  public int getItemCount() {
    if (employees != null) {
      return employees.size();
    } else {
      return 0;
    }
  }
 
  public void setEmployeeList(ArrayList<BookingsModule> employees) {
    this.employees = employees;
    notifyDataSetChanged();
  }

  public void setClickListener(BookingsMvpView clickListener){
    this.bookingsMvpView = clickListener;
  }

  public void setOwner(boolean isOwner){
    this.isOwner = isOwner;
  }
 
  class EmployeeViewHolder extends RecyclerView.ViewHolder {
 
    private ItemViewBookingsBinding employeeListItemBinding;
 
    public EmployeeViewHolder(@NonNull ItemViewBookingsBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
 
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}