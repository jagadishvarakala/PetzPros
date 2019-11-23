package com.petz.pros.ui.registration.personaldetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentPersonalDetailsBinding;

public class PersonalDetailsFragment extends Fragment {

    private FragmentPersonalDetailsBinding personalDetailsBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        personalDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_details, container, false);
        return personalDetailsBinding.getRoot();
    }

    public static PersonalDetailsFragment newInstance(String text) {

        PersonalDetailsFragment f = new PersonalDetailsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    private void loadView(){
    }
}
