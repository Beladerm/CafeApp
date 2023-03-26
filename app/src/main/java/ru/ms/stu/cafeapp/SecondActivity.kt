package ru.ms.stu.cafeapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
    }

    companion object{
        @JvmStatic
        private val KEY_STATE = "STATE"
        fun newIntent(context: Context, state:MainActivity.State) :Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_STATE, state)
            return intent
        }
    }

}