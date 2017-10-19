package com.keinix.smellslikebakin;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class GridAdapter extends RecyclerAdapter {
    private final GridFragment.OnRecipeSelectedInterface mListener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    public int getLayoutId() {
        return R.layout.grid_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        mListener.onGridRecipeSelected(index);
    }
}
