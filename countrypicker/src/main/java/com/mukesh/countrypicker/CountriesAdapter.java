package com.mukesh.countrypicker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukesh.countrypicker.listeners.OnItemClickListener;
import java.util.List;

public class CountriesAdapter extends
    RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

  // region Variables
  private OnItemClickListener listener;
  private List<Country> countries;
  private Context context;
  private int textColor;
  // endregion

  //region Constructor
  public CountriesAdapter(Context context, List<Country> countries,
      OnItemClickListener listener, int textColor) {
    this.context = context;
    this.countries = countries;
    this.listener = listener;
    this.textColor = textColor;
  }
  // endregion

  // region Adapter Methods
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_picker_dialog_item_country, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Country country = countries.get(position);
    holder.countryNameText.setText(country.getName());
    holder.countryNameText.setTextColor(textColor == 0 ? Color.BLACK : textColor);
    country.loadFlagByCode(context);
    if (country.getFlag() != -1) {
      holder.countryFlagImageView.setImageResource(country.getFlag());
    }
    holder.rootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        listener.onItemClicked(country);
      }
    });
  }

  @Override public int getItemCount() {
    return countries.size();
  }
  // endregion

  // region ViewHolder
  class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView countryFlagImageView;
    private TextView countryNameText;
    private LinearLayout rootView;

    ViewHolder(View itemView) {
      super(itemView);
      countryFlagImageView = itemView.findViewById(R.id.country_flag);
      countryNameText = itemView.findViewById(R.id.country_title);
      rootView = itemView.findViewById(R.id.rootView);
    }
  }
  // endregion
}
