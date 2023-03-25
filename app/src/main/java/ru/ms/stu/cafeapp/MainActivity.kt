package ru.ms.stu.cafeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import kotlinx.android.parcel.Parcelize
import ru.ms.stu.cafeapp.databinding.ActivityMainBinding
import ru.ms.stu.cafeapp.MainActivity.State as State1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editText: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: Button
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
            editText = username
            editTextPassword = password
            buttonSignIn = entire

            state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                name = editText.toString().trim(),
                pass = editTextPassword.toString().trim()
            )

        }
    }

    private fun logIn() {
        with(binding) {
            entire.setOnClickListener {
                intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(KEY_STATE, state)
                if (checkInput())  startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun checkInput(): Boolean {
        with(binding) {
            return username.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()
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



