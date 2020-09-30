package UI.ui.MainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.stockcourt.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.profile_picture_edit_menu_layout.*

class ProfileImageEditDrawerFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_picture_edit_menu_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        profile_image_edit_menu.setNavigationItemSelectedListener { menuItem ->
            when(menuItem!!.itemId) {
                R.id.profileImageMenuLibrary -> Toast.makeText(activity, "Pressed the library button", Toast.LENGTH_SHORT).show()
                R.id.profileImageMenuRemove -> Toast.makeText(activity, "Pressed the remove button", Toast.LENGTH_SHORT).show()
                R.id.profileImageMenuCancel -> Toast.makeText(activity, "Pressed the cancel button", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

}