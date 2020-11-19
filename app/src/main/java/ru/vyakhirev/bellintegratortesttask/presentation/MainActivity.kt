package ru.vyakhirev.bellintegratortesttask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.vyakhirev.bellintegratortesttask.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        CityListComponent.create(
//            (application as App).getAppComponent(), DatabaseComponent.create(
//                DatabaseModule(application), application
//            )
//        ).inject(this)
        openFragment(ListCityFragment())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment, fragment.tag)
        transaction.commit()
    }
}