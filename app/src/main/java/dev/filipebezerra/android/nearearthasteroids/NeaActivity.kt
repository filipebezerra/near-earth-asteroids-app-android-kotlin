package dev.filipebezerra.android.nearearthasteroids

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import dev.filipebezerra.android.nearearthasteroids.databinding.NeaActivityBinding

class NeaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<NeaActivityBinding>(this, R.layout.nea_activity)
    }
}