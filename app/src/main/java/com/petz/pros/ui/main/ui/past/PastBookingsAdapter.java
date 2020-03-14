package com.petz.pros.ui.main.ui.past;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ItemViewBookingsBinding;
import com.petz.pros.databinding.ItemViewPastBookingsBinding;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.bookings.BookingsMvpView;

import java.util.ArrayList;

public class PastBookingsAdapter
    extends RecyclerView.Adapter<PastBookingsAdapter.EmployeeViewHolder> {
 
  private ArrayList<BookingsModule> employees;
  private BookingsMvpView bookingsMvpView;
  private boolean isOwner;
 
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemViewPastBookingsBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_view_past_bookings, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }
 
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    BookingsModule currentStudent = employees.get(i);
    employeeViewHolder.employeeListItemBinding.setDetails(currentStudent);
    employeeViewHolder.employeeListItemBinding.setIsPetOwner(isOwner);


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
 
    private ItemViewPastBookingsBinding employeeListItemBinding;
 
    public EmployeeViewHolder(@NonNull ItemViewPastBookingsBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
 
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}