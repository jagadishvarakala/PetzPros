package com.petz.pros.ui.main.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentContactBinding;
import com.petz.pros.ui.base.BaseFragment;
import com.petz.pros.ui.main.contact.ContactMvpPresenter;
import com.petz.pros.ui.main.contact.ContactMvpView;

import javax.inject.Inject;


public class ContactFragment extends BaseFragment implements ContactMvpView {

    @Inject
    ContactMvpPresenter<ContactMvpView> moreMvpPresenter;

    private FragmentContactBinding contactBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        contactBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact, container, false);
        getActivityComponent().inject(this);
        moreMvpPresenter.onAttach(this);
        return contactBinding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        contactBinding.setCallback(moreMvpPresenter);
    }

    @Override
    public String getName() {
        return contactBinding.editName.getText().toString();
    }

    @Override
    public String getEmail() {
        return contactBinding.edtEmail.getText().toString();
    }

    @Override
    public String getPhone() {
        return contactBinding.edtPhone.getText().toString();
    }

    @Override
    public String getSubject() {
        return contactBinding.edtSubject.getText().toString();
    }

    @Override
    public String getMessage() {
        return contactBinding.edtMessage.getText().toString();
    }

    @Override
    public void showErrorName(String message) {
        contactBinding.editName.setError(message);
    }

    @Override
    public void showErrorEmail(String message) {
        contactBinding.edtEmail.setError(message);
    }

    @Override
    public void showErrorPhone(String message) {
        contactBinding.edtPhone.setError(message);
    }

    @Override
    public void showErrorSubject(String message) {
        contactBinding.edtSubject.setError(message);
    }
}