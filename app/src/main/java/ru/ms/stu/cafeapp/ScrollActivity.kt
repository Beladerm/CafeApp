package ru.ms.stu.cafeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ms.stu.cafeapp.databinding.ActivityScrollBinding

class ScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollBinding
    private lateinit var order: SecondActivity.Order
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollBinding.inflate(layoutInflater).also { setContentView(R.layout.activity_scroll) }
        order = intent.getParcelableExtra(KEY_STATE)!!


    }

    companion object {
        @JvmStatic
        private val KEY_STATE = "STATE"
        fun newIntent(context: Context, order: SecondActivity.Order ): Intent {
            val intent = Intent(context, ScrollActivity::class.java)
            intent.putExtra(KEY_STATE, order)
            return intent
        }
    }
}