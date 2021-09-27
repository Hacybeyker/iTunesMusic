package com.hacybeyker.itunesmusic.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

typealias BottomSheetFragmentBlock = ((BottomSheetFragment) -> Unit)?

class BottomSheetFragment(
    private val themeParameter: Int,
    private val layoutParameter: Int,
    private val cancelableParameter: Boolean = true,
    private val block: BottomSheetFragmentBlock = null
) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, themeParameter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return layoutInflater.inflate(layoutParameter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = cancelableParameter
        block?.invoke(this)
    }
}