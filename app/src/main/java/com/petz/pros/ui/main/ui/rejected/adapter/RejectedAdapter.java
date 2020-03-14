package com.petz.pros.ui.main.ui.rejected.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ItemViewRejectedBinding;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;

import java.util.ArrayList;

public class RejectedAdapter
    extends RecyclerView.Adapter<RejectedAdapter.EmployeeViewHolder> {
 
  private ArrayList<BookingsModule> employees;
 
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemViewRejectedBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_view_rejected, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }
 
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    BookingsModule currentStudent = employees.get(i);
    employeeViewHolder.employeeListItemBinding.setDetails(currentStudent);
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
 
  class EmployeeViewHolder extends RecyclerView.ViewHolder {
 
    private ItemViewRejectedBinding employeeListItemBinding;
 
    public EmployeeViewHolder(@NonNull ItemViewRejectedBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
 
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}