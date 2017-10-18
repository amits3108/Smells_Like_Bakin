package com.keinix.smellslikebakin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class CheckBoxesFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_boxes, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.checkBoxesLayout);
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        boolean isIngredients = getArguments().getBoolean(ViewPagerFragment.KEY_IS_INGREDIENTS);

        String[] contents;
        if (isIngredients) {
            contents = Recipes.ingredients[index].split("`");
        } else {
            contents = Recipes.directions[index].split("`");
        }

        mCheckBoxes = new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }

        setUpCheckBoxes(contents, linearLayout, checkedBoxes);
        return view;
    }

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] isChecked) {
        int i = 0;
        for (String content : contents) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            mCheckBoxes[i].setChecked(isChecked[i]);
            container.addView(mCheckBoxes[i]);
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] checkBoxesAreChecked = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes) {
            checkBoxesAreChecked[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, checkBoxesAreChecked);
        super.onSaveInstanceState(outState);
    }
}
