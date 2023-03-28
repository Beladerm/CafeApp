package ru.ms.stu.cafeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.ms.stu.cafeapp.databinding.ActivitySecondScreenBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var state: MainActivity.State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater).also { setContentView(it.root) }

        state = intent.getParcelableExtra(KEY_STATE)!!
        initView()
    }

    private fun initView() {
        with(binding) {
            hello.text = String.format(hello.text. toString(), state.name)

        }
    }

    companion object{
        @JvmStatic private val KEY_STATE = "STATE"
        fun newIntent(context: Context, state:MainActivity.State) :Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_STATE, state)
            return intent
        }
    }


}
