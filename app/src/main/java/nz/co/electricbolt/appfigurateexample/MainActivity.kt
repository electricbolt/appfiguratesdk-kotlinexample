// MainActivity.kt
// AppfigurateExample Copyright © 2022-2025; Electric Bolt Limited.

package nz.co.electricbolt.appfigurateexample

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import nz.co.electricbolt.appfiguratelibrary.Appfigurate
import nz.co.electricbolt.appfiguratelibrary.Configuration
import nz.co.electricbolt.appfiguratelibrary.ConfigurationLabel
import nz.co.electricbolt.appfiguratelibrary.RemotePropertyType
import nz.co.electricbolt.appfiguratelibrary.RemotePropertyType.RemotePropertyTypeBoolean
import nz.co.electricbolt.appfiguratelibrary.RemotePropertyType.RemotePropertyTypeDouble
import nz.co.electricbolt.appfiguratelibrary.RemotePropertyType.RemotePropertyTypeInt
import nz.co.electricbolt.appfiguratelibrary.RemotePropertyType.RemotePropertyTypeString

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

        initFirebaseRemoteConfig()

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

    fun initFirebaseRemoteConfig() {
        FirebaseApp.initializeApp(this)
        val configSettings: FirebaseRemoteConfigSettings =
            FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettingsAsync(configSettings)

        Appfigurate.fetchRemoteConfiguration { propertyKey: String, propertyType: RemotePropertyType, defaultValue: Any? ->
            when (propertyType) {
                RemotePropertyTypeString -> remoteConfig.getString(propertyKey)
                RemotePropertyTypeBoolean -> remoteConfig.getBoolean(propertyKey)
                RemotePropertyTypeInt -> remoteConfig.getLong(propertyKey)
                RemotePropertyTypeDouble -> remoteConfig.getDouble(propertyKey)
            }
        }

        // Set default values for firebase remote config to those from the reset() method of the ExampleConfiguration class.
        remoteConfig.setDefaultsAsync(
            Configuration.sharedConfiguration().remoteDefaults()
        )

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful()) {
                    System.out.println("Loaded remote configuration")
                    Appfigurate.flushRemoteConfiguration()
                }
            }

        // Optional real time update listener.
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                System.out.println("Updated remote configuration: " + configUpdate.updatedKeys)
                remoteConfig.activate().addOnCompleteListener {
                    Appfigurate.flushRemoteConfiguration()
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                println("Error updating remote configuration: $error")
            }
        })
    }
}