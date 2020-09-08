package UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.stockcourt.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.trendsFragment, R.id.stocksFragment, R.id.indicesFragment, R.id.commoditiesFragment, R.id.currenciesFragment))

        bottomNavigationView.setupWithNavController(navController)

    }


    fun loginEmailBtnClicked(view: View) {
        val loginEmailIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginEmailIntent)
    }

    fun loginGoogleBtnClicked(view: View) {

    }


}