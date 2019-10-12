package com.mukesh.countrypicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.mukesh.countrypicker.listeners.BottomSheetInteractionListener;

import static com.mukesh.countrypicker.CountryPicker.THEME_NEW;

public class BottomSheetDialogView extends BottomSheetDialogFragment {
  private static final String BUNDLE_KEY_THEME = "theme";
  private BottomSheetInteractionListener listener;

  public static BottomSheetDialogView newInstance(int theme) {
    BottomSheetDialogView bottomSheetDialogView = new BottomSheetDialogView();
    Bundle args = new Bundle();
    args.putInt(BUNDLE_KEY_THEME, theme);
    bottomSheetDialogView.setArguments(args);
    return bottomSheetDialogView;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      int theme = args.getInt(BUNDLE_KEY_THEME, 0);
      if (theme == THEME_NEW) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MaterialDialogStyle);
      } else {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
      }
    } else {
      setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
          @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.country_picker, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    listener.initiateUi(view);
    listener.setCustomStyle(view);
    listener.setSearchEditText();
    listener.setupRecyclerView(view);

    // This fix collapsed behavior into landscape forcing it into STATE_HALF_EXPANDED
    view.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
              @Override
              public void onGlobalLayout() {
                if (getDialog() != null) {
                  BottomSheetDialog bsd = (BottomSheetDialog) getDialog();
                  FrameLayout fl = bsd.findViewById(android.support.design.R.id.design_bottom_sheet);
                  if (fl != null) {
                    BottomSheetBehavior bsb = BottomSheetBehavior.from(fl);
                    bsb.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                  }
                }
              }
            });
  }

  public void setListener(BottomSheetInteractionListener listener) {
    this.listener = listener;
  }
}
