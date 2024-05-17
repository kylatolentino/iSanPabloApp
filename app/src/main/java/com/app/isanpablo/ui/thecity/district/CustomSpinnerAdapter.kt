package com.app.isanpablo.ui.thecity.district

//noinspection SuspiciousImport
import android.R.*
import android.R.id.*
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.app.isanpablo.R

@Suppress("DEPRECATION")
class CustomSpinnerAdapter(
    context: Context,
    items: Array<String>
) : ArrayAdapter<String>(context, layout.simple_spinner_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(text1)
        textView.setTextColor(context.resources.getColor(R.color.white))
        textView.textSize=20f
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(text1)
        textView.setTextColor(context.resources.getColor(R.color.black))
        return view
    }
}
