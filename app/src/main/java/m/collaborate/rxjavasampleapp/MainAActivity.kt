package m.collaborate.rxjavasampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import m.collaborate.rxjavasampleapp.databinding.ActivityMainABinding

class MainAActivity : AppCompatActivity(R.layout.activity_main_a) {
    private lateinit var binding :ActivityMainABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainABinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}