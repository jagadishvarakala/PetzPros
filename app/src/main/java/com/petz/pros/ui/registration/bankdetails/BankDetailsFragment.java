package com.petz.pros.ui.registration.bankdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.petz.pros.R;
import com.petz.pros.databinding.FragmentBankDetailsBinding;
import com.petz.pros.ui.registration.caretacker.CareTackerRegistrationActivity;

public class BankDetailsFragment extends Fragment {

    private FragmentBankDetailsBinding bankDetailsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bankDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bank_details, container, false);
        return bankDetailsBinding.getRoot();
    }

    public static BankDetailsFragment newInstance() {
        BankDetailsFragment f = new BankDetailsFragment();
        return f;
    }

    public boolean toValidateBankDetailsDetails() {
        boolean isValid = false;
        if (bankDetailsBinding.edtAccountNumber.getText().toString().isEmpty()) {
            bankDetailsBinding.edtAccountNumber.setError("Enter Account Number");
            return isValid;
        }
        if (bankDetailsBinding.edtBankName.getText().toString().isEmpty()) {
            bankDetailsBinding.edtBankName.setError("Enter Bank Name");
            return isValid;
        }
        if (bankDetailsBinding.edtBranch.getText().toString().isEmpty()) {
            bankDetailsBinding.edtBranch.setError("Enter Branch");
            return isValid;
        }
        if (bankDetailsBinding.edtIFSCCode.getText().toString().isEmpty()) {
            bankDetailsBinding.edtIFSCCode.setError("Enter IFSC code");
            return isValid;
        }

        if (getActivity() != null) {
            if (getActivity() instanceof CareTackerRegistrationActivity) {
                ((CareTackerRegistrationActivity) getActivity()).registrationRequest.setAccountNumber(bankDetailsBinding.edtAccountNumber.getText().toString());
                ((CareTackerRegistrationActivity) getActivity()).registrationRequest.setBankName(bankDetailsBinding.edtBankName.getText().toString());
                ((CareTackerRegistrationActivity) getActivity()).registrationRequest.setBranch(bankDetailsBinding.edtBranch.getText().toString());
                ((CareTackerRegistrationActivity) getActivity()).registrationRequest.setIfscCode(bankDetailsBinding.edtIFSCCode.getText().toString());
            }
        }

        isValid = true;
        return isValid;
    }
}
