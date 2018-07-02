package com.witold.drawmeapp.fill_color_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.witold.drawmeapp.R;
import com.witold.drawmeapp.providers.model.FillColor;

import java.util.List;

/**
 * Created by Witold on 09.03.2018.
 */

public class FillColorListRecyclerViewAdapter extends RecyclerView.Adapter<FillColorListRecyclerViewAdapter.FillColorViewHolder> {
    private FillColorListHolder fillColorListHolder;
    private List<FillColor> fillColors;

    public FillColorListRecyclerViewAdapter(FillColorListHolder fillColorViewHolder, List<FillColor> fillColors) {
        this.fillColorListHolder = fillColorViewHolder;
        this.fillColors = fillColors;
    }

    class FillColorViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private FillColor fillColor;
        public FillColorViewHolder(View itemView) {
            super(itemView);
            this.frameLayout = itemView.findViewById(R.id.singleFillColor);
            this.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fillColorListHolder.onFillColorClick(fillColor);
                }
            });
        }

        public void setFillColor(FillColor fillColor) {
            this.frameLayout.setBackgroundColor(fillColor.getColor());
            this.fillColor = fillColor;
        }
    }

    @Override
    public FillColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_fill_color, parent, false);
        return new FillColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FillColorViewHolder holder, int position) {
        FillColor fillColor = this.fillColors.get(position);
        holder.setFillColor(fillColor);
    }

    @Override
    public int getItemCount() {
        return fillColors.size();
    }
}
