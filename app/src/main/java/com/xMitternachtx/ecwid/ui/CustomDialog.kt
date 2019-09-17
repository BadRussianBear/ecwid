package com.xMitternachtx.ecwid.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.xMitternachtx.ecwid.R

class CustomDialog : DialogFragment() {
    internal lateinit var listener: NoticeDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.act_like)
                    .setItems(R.array.actions
                    ) { _, which ->
                        when (which) {
                            0 -> listener.onEditClick(this)
                            1 -> listener.onRemoveClick(this)
                        }
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface NoticeDialogListener {
        fun onEditClick(dialog: DialogFragment)
        fun onRemoveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }
}
