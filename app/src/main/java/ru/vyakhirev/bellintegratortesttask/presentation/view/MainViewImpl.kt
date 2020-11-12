package ru.vyakhirev.bellintegratortesttask.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class MainViewImpl
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), MainView {
    override fun populateCity(name: String, temper: Int) {
        TODO("Not yet implemented")
    }

    override fun showError(errorText: String) {
        TODO("Not yet implemented")
    }

}
