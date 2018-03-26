package com.mukesh.countrypicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class CountriesAdapter extends
    RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

  // region Variables
  private OnItemClickListener listener;
  private List<Country> countries;
  private Context context;
  // endregion

  //region Constructor
  public CountriesAdapter(Context context, List<Country> countries,
      OnItemClickListener listener) {
    this.context = context;
    this.countries = countries;
    this.listener = listener;
  }
  // endregion

  // region Adapter Methods
  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Country country = countries.get(position);
    holder.countryNameText.setText(country.getName());
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
