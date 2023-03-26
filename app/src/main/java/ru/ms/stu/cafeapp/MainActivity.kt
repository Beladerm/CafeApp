package ru.ms.stu.cafeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import kotlinx.android.parcel.Parcelize
import ru.ms.stu.cafeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init(savedInstanceState)
        logIn()
    }

    private fun init(savedInstanceState: Bundle?) {
        with(binding) {
            state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                name = username.toString().trim(),
                pass =  password.toString().trim()
            )
        }
    }

    private fun logIn() {
        with(binding) {
            entire.setOnClickListener {
                intent = SecondActivity.newIntent(this@MainActivity, state)
                if (checkInput()) {
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_fields_empty),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun checkInput(): Boolean {
        with(binding) {
            return username.text.toString().trim().isNotEmpty() &&
                    password.text.toString().trim().isNotEmpty()
        }
    }

    @Parcelize
    class State(
        val name: String,
        val pass: String
    ) : Parcelable

    companion object {
        @JvmStatic
        private val KEY_STATE = "STATE"
    }

}



