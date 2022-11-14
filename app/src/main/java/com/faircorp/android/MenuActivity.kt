package com.faircorp.android

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class MenuActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_windows -> startActivity(
                Intent(this, WindowActivity::class.java)
            )
            R.id.menu_rooms -> startActivity(
                Intent(this, RoomActivity::class.java)
            )
            R.id.menu_heaters -> startActivity(
                Intent(this, HeaterActivity::class.java)
            )
            R.id.menu_buildings -> startActivity(
                Intent(this, BuildingActivity::class.java)
            )
            R.id.menu_website -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://dev-mind.fr"))
            )
            R.id.menu_email -> startActivity(
                Intent(Intent.ACTION_SENDTO, Uri.parse("/*mailto://guillaume@dev-mind.fr*/"))
            )
        }
        return super.onContextItemSelected(item)
    }
}