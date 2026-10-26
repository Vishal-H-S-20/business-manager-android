package com.example.nandivishal;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment
{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar c= Calendar.getInstance();
        int year= c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new android.app.DatePickerDialog(getActivity() ,(android.app.DatePickerDialog.OnDateSetListener) getActivity() ,year ,month ,day);
    }
}
