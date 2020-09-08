package UI

import UI.ui.MainFragments.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.stockcourt.R
import kotlinx.android.synthetic.main.activity_trends.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }




    fun loginEmailBtnClicked(view: View) {
        val loginEmailIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginEmailIntent)
    }

    fun loginGoogleBtnClicked(view: View) {

    }


}