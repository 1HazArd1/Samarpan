package com.hazard.samarpan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NgoSignup2Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ngo_signup2_fragment, container, false);

        String[] type = new String[]{"Trust", "Society", "Section-8 company", "Private limited", "Other"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.dropdown_menu_popup_item,
                        type);

        AutoCompleteTextView DropdownSelectTypeSignup =
                v.findViewById(R.id.ngosignup_select_orgtype);
        DropdownSelectTypeSignup.setAdapter(adapter);

        return v;
    }
}
