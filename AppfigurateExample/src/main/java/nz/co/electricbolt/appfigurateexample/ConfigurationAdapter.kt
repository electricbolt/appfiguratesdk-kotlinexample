// ConfigurationAdapter.kt
// AppfigurateExample Copyright © 2022; Electric Bolt Limited.

package nz.co.electricbolt.appfigurateexample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nz.co.electricbolt.appfiguratelibrary.Configuration
import java.util.*

internal class ConfigurationAdapter : RecyclerView.Adapter<ConfigurationAdapter.ViewHolder>() {
    private val config: ExampleConfiguration

    internal class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var label: TextView
        var value: TextView

        init {
            label = v.findViewById(R.id.label)
            value = v.findViewById(R.id.value)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.configuration_view, parent, false) as View
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.label.text = "bool"
                holder.value.text = "" + config.bool
            }
            1 -> {
                holder.label.text = "string_Textfield"
                holder.value.text = config.string_Textfield
            }
            2 -> {
                holder.label.text = "string_RegexTextfield"
                holder.value.text = config.string_RegexTextfield
            }
            3 -> {
                holder.label.text = "string_List"
                holder.value.text = config.string_List
            }
            4 -> {
                holder.label.text = "string_Textfield_List"
                holder.value.text = config.string_Textfield_List
            }
            5 -> {
                holder.label.text = "string_RegexTextfield_List"
                holder.value.text = config.string_RegexTextfield_List
            }
            6 -> {
                holder.label.text = "encrypted_string_Textfield_List"
                holder.value.text = config.encrypted_string_Textfield_List
            }
            7 -> {
                holder.label.text = "encrypted_string_RegexTextfield_List"
                holder.value.text = config.encrypted_string_RegexTextfield_List
            }
            8 -> {
                holder.label.text = "integer_Slider"
                holder.value.text = "" + config.integer_Slider
            }
            9 -> {
                holder.label.text = "integer_Textfield"
                holder.value.text = "" + config.integer_Textfield
            }
            10 -> {
                holder.label.text = "integer_RegexTextfield"
                holder.value.text = "" + config.integer_RegexTextfield
            }
            11 -> {
                holder.label.text = "integer_List"
                holder.value.text = "" + config.integer_List
            }
            12 -> {
                holder.label.text = "integer_Textfield_List"
                holder.value.text = "" + config.integer_Textfield_List
            }
            13 -> {
                holder.label.text = "integer_RegexTextfield_List"
                holder.value.text = "" + config.integer_RegexTextfield_List
            }
            14 -> {
                holder.label.text = "float_Slider"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_Slider)
            }
            15 -> {
                holder.label.text = "float_Textfield"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_Textfield)
            }
            16 -> {
                holder.label.text = "float_RegexTextfield"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_RegexTextfield)
            }
            17 -> {
                holder.label.text = "float_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_List)
            }
            18 -> {
                holder.label.text = "float_Textfield_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_Textfield_List)
            }
            19 -> {
                holder.label.text = "float_RegexTextfield_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.float_RegexTextfield_List)
            }
            20 -> {
                holder.label.text = "double_Slider"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_Slider)
            }
            21 -> {
                holder.label.text = "double_Textfield"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_Textfield)
            }
            22 -> {
                holder.label.text = "double_RegexTextfield"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_RegexTextfield)
            }
            23 -> {
                holder.label.text = "double_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_List)
            }
            24 -> {
                holder.label.text = "double_Textfield_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_Textfield_List)
            }
            25 -> {
                holder.label.text = "double_RegexTextfield_List"
                holder.value.text = String.format(Locale.US, "%.3f", config.double_RegexTextfield_List)
            }
        }
    }

    override fun getItemCount(): Int {
        return 26
    }

    init {
        config = Configuration.sharedConfiguration() as ExampleConfiguration
    }
}