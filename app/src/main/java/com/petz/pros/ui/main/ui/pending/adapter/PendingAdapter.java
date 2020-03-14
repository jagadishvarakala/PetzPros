package com.petz.pros.ui.main.ui.pending.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ItemViewBookingsBinding;
import com.petz.pros.databinding.ItemViewNotificationsBinding;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.pending.PendingServicesMvpView;

import java.util.ArrayList;

public class PendingAdapter
    extends RecyclerView.Adapter<PendingAdapter.EmployeeViewHolder> {
 
  private ArrayList<BookingsModule> employees;
  private PendingServicesMvpView servicesMvpView;
 
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemViewNotificationsBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_view_notifications, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }
 
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    BookingsModule currentStudent = employees.get(i);
    employeeViewHolder.employeeListItemBinding.setDetails(currentStudent);
    employeeViewHolder.employeeListItemBinding.setPosition(i);
    employeeViewHolder.employeeListItemBinding.setCallback(servicesMvpView);
  }
 
  @Override
  public int getItemCount() {
    if (employees != null) {
      return employees.size();
    } else {
      return 0;
    }
  }

  public void setClickListener(PendingServicesMvpView clickListener){
    this.servicesMvpView = clickListener;
  }
 
  public void setEmployeeList(ArrayList<BookingsModule> employees) {
    this.employees = employees;
    notifyDataSetChanged();
  }
 
  class EmployeeViewHolder extends RecyclerView.ViewHolder {
 
    private ItemViewNotificationsBinding employeeListItemBinding;
 
    public EmployeeViewHolder(@NonNull ItemViewNotificationsBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
 
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}