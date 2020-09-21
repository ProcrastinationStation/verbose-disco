package UI.ui.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stockcourt.Models.Controller.TrendsActivity
import com.example.stockcourt.Models.Services.GetPosts
import com.example.stockcourt.Models.Services.MainAdapter
import com.example.stockcourt.Models.Services.MainAdapterFeatured
import com.example.stockcourt.R
import com.example.stockcourt.R.layout.fragment_home
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RecyclerViewFeatured.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        RecyclerViewMain.layoutManager = LinearLayoutManager(context)

        GetPosts.fetchJsonFeatured()

        GetPosts.fetchJsonPost()

     //   RecyclerViewMain.adapter = MainAdapter(GetPosts.BodyResponseParsed)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_home, container, false)
    }

    fun imageViewFeaturedClicked(view: View) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }
}