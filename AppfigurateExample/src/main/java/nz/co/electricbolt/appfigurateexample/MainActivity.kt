// MainActivity.kt
// AppfigurateExample Copyright © 2022; Electric Bolt Limited.

package nz.co.electricbolt.appfigurateexample

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.electricbolt.appfiguratelibrary.Appfigurate
import nz.co.electricbolt.appfiguratelibrary.ConfigurationLabel

class MainActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<ConfigurationAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("Debug build? " + BuildConfig.DEBUG)

        setContentView(R.layout.activity_main)
        this.title = "Kotlin Example " + Appfigurate.version()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = ConfigurationAdapter()
        recyclerView.adapter = adapter

        Appfigurate.addConfigurationUpdatedListener { action: String? ->
            if (action != null) {
                println("Configuration updated by action $action")
            } else {
                println("Configuration updated")
            }
            (adapter as ConfigurationAdapter).notifyDataSetChanged()
        }
        val configurationLabel = findViewById<ConfigurationLabel>(R.id.configuration_label)
        configurationLabel.setOnClickListener { _: View? ->
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Kotlin Example").setMessage("Label clicked")
                    .setPositiveButton("OK") { _: DialogInterface?, _: Int -> }
            val dialog = builder.create()
            dialog.show()
        }
    }
}