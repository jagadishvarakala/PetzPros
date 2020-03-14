package com.petz.pros.ui.main.ui.history.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.petz.pros.R;
import com.petz.pros.databinding.ItemViewHistoryBinding;
import com.petz.pros.ui.main.ui.bookings.BookingsModule;
import com.petz.pros.ui.main.ui.history.HistoryMvpView;

import java.util.ArrayList;

public class HistoryAdapter
    extends RecyclerView.Adapter<HistoryAdapter.EmployeeViewHolder> {
 
  private ArrayList<BookingsModule> employees;
  private HistoryMvpView historyMvpView;
 
  @NonNull
  @Override
  public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemViewHistoryBinding employeeListItemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_view_history, viewGroup, false);
    return new EmployeeViewHolder(employeeListItemBinding);
  }
 
  @Override
  public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
    BookingsModule currentStudent = employees.get(i);
    employeeViewHolder.employeeListItemBinding.setDetails(currentStudent);

    employeeViewHolder.employeeListItemBinding.serviceStartBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(historyMvpView != null){
          historyMvpView.onClickTrackBtn(currentStudent);
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

  public void setHistoryMvpView(HistoryMvpView historyMvpView){
    this.historyMvpView = historyMvpView;
  }
 
  class EmployeeViewHolder extends RecyclerView.ViewHolder {
 
    private ItemViewHistoryBinding employeeListItemBinding;
 
    public EmployeeViewHolder(@NonNull ItemViewHistoryBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
 
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}