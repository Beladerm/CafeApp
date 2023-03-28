package ru.ms.stu.cafeapp

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.parcel.Parcelize
import ru.ms.stu.cafeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init(savedInstanceState)
        logIn(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        with(binding) {
            state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                name = username.text.toString().trim(),
            )
        }
    }

    private fun logIn(savedInstanceState: Bundle?) {
        with(binding) {
            entire.setOnClickListener {
                init(savedInstanceState)
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
        val name: String
    ) : Parcelable

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
    }

}



