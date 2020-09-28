package UI.ui.MainFragments

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.stockcourt.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.bottom_nav_layout.*

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_nav_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_navigation_view.setNavigationItemSelectedListener { menuItem ->
            when(menuItem!!.itemId) {
                R.id.profileMenuEdit -> Toast.makeText(activity, "Pressed the edit button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuPackages -> Toast.makeText(activity, "Pressed the packages button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuHelp -> Toast.makeText(activity, "Pressed the help button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuTerms  -> Toast.makeText(activity, "Pressed the terms button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuPrivacy -> Toast.makeText(activity, "Pressed the privacy button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuLogout -> Toast.makeText(activity, "Pressed the logout button", Toast.LENGTH_SHORT).show()
                R.id.profileMenuCancel -> Toast.makeText(activity, "Pressed the cancel button", Toast.LENGTH_SHORT).show()
            }
            true
        }




    }
    }

