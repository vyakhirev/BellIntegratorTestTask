package ru.vyakhirev.bellintegratortesttask.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.vyakhirev.bellintegratortesttask.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.fragments.size > 0) {
            Log.d("ferz", "Fragment detail")
            supportFragmentManager
                .beginTransaction()
                .show(DetailCityFragment())
                .commit()
        } else {
            Log.d("ferz", "Fragment list")
            openFragment(ListCityFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment, fragment.tag)
        transaction.commit()
    }
}