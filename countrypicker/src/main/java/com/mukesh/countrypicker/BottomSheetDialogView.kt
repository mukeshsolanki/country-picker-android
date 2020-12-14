package com.mukesh.countrypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mukesh.countrypicker.listeners.BottomSheetInteractionListener

class BottomSheetDialogView : BottomSheetDialogFragment() {
    private var listener: BottomSheetInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            val theme = args.getInt(BUNDLE_KEY_THEME, 0)
            if (theme == CountryPicker.THEME_NEW) {
                setStyle(STYLE_NORMAL, R.style.MaterialDialogStyle)
            } else {
                setStyle(STYLE_NORMAL, R.style.DialogStyle)
            }
        } else {
            setStyle(STYLE_NORMAL, R.style.DialogStyle)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener!!.initiateUi(view)
        listener!!.setCustomStyle(view)
        listener!!.setSearchEditText()
        listener!!.setupRecyclerView(view)
    }

    fun setListener(listener: BottomSheetInteractionListener?) {
        this.listener = listener
    }

    companion object {
        private const val BUNDLE_KEY_THEME = "theme"
        fun newInstance(theme: Int): BottomSheetDialogView {
            val bottomSheetDialogView = BottomSheetDialogView()
            val args = Bundle()
            args.putInt(BUNDLE_KEY_THEME, theme)
            bottomSheetDialogView.arguments = args
            return bottomSheetDialogView
        }
    }
}