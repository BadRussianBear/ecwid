package com.xMitternachtx.ecwid.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.xMitternachtx.ecwid.R

class AddCustomDialog : DialogFragment() {
    internal lateinit var listener: NoticeDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            var inflater = requireActivity().layoutInflater
            builder.apply {
                setView(inflater.inflate(R.layout.new_product,null))
                setPositiveButton("Ok"
                ) { dialog, id ->
                    listener.okClick()
                }
                setNegativeButton("Cancel"
                ) { dialog, id ->
                    listener.cancelClick()
                }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface NoticeDialogListener {
        fun okClick()
        fun cancelClick()
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
