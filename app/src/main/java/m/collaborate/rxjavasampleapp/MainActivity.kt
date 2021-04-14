package m.collaborate.rxjavasampleapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import m.collaborate.rxjavasampleapp.databinding.MainActivityBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_activity) {
    private lateinit var binding : MainActivityBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputSearchText.hint = "검색어를 입력하세요."
    }
}
