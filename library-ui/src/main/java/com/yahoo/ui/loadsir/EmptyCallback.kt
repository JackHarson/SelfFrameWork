package com.yahoo.ui.loadsir


import com.kingja.loadsir.callback.Callback
import com.yahoo.ui.R


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}