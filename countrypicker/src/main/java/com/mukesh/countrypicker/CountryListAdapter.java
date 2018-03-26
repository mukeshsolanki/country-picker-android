package com.mukesh.countrypicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryListAdapter extends BaseAdapter {

  // region Variable
  private Context context;
  private List<Country> countries;
  private LayoutInflater inflater;
  // endregion

  // region Constructor
  public CountryListAdapter(Context context, List<Country> countries) {
    super();
    this.context = context;
    this.countries = countries;
    inflater = LayoutInflater.from(context);
  }
  //endregion

  // region AdapterMethods
  @Override
  public int getCount() {
    return countries.size();
  }

  @Override
  public Object getItem(int arg0) {
    return null;
  }

  @Override
  public long getItemId(int arg0) {
    return 0;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    Country country = countries.get(position);

    if (view == null) {
      view = inflater.inflate(R.layout.row, null);
    }

    ViewHolder viewHolder = ViewHolder.from(view);
    viewHolder.textView.setText(country.getName());

    country.loadFlagByCode(context);
    if (country.getFlag() != -1) {
      viewHolder.imageView.setImageResource(country.getFlag());
    }
    return view;
  }
  // endregion

  // region ViewHolder
  static class ViewHolder {
    TextView textView;
    ImageView imageView;

    static ViewHolder from(View view) {
      if (view == null) {
        return null;
      }

      if (view.getTag() == null) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textView = view.findViewById(R.id.row_title);
        viewHolder.imageView = view.findViewById(R.id.row_icon);
        view.setTag(viewHolder);
        return viewHolder;
      } else {
        return (ViewHolder) view.getTag();
      }
    }
  }
  // endregion
}