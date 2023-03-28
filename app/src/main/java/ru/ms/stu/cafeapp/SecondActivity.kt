package ru.ms.stu.cafeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize
import ru.ms.stu.cafeapp.databinding.ActivitySecondScreenBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var state: MainActivity.State
    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivitySecondScreenBinding.inflate(layoutInflater).also { setContentView(it.root) }
        state = intent.getParcelableExtra(KEY_STATE)!!

        setupUsername()
        binding.radioTea.isChecked = true
        binding.order.setOnClickListener{
            onOrder()
            intent = ScrollActivity.newIntent(this@SecondActivity, order)
            startActivity(intent)
        }
    }

    private fun setupUsername() {
        binding.apply {
            hello.text = String.format(hello.text.toString(), state.name)

            radioGroupDrinks.setOnCheckedChangeListener { _, _ ->
                when (radioGroupDrinks.checkedRadioButtonId) {
                    R.id.radioCoffee -> onUserChooseCoffee()
                    R.id.radioTea -> onUserChooseTea()

                    else -> throw IllegalAccessException("Unexpected system behavior")
                }
            }
        }
    }

    private fun onUserChooseCoffee() {
        binding.apply {
            additives.text =
                String.format(getString(R.string.what_s_else), radioCoffee.text)
            lemon.visibility = View.INVISIBLE
            spinnerCoffee.visibility = View.VISIBLE
            spinnerTea.visibility = View.INVISIBLE
            lemon.isChecked=false

        }
    }

    private fun onOrder() {
        val adv =  arrayListOf<String>()
        var drinkType:String=""
        with(binding) {
            if (sugar.isChecked) adv.add(sugar.text.toString())
            if (milk.isChecked) adv.add(milk.text.toString())
            if (lemon.isChecked) adv.add(lemon.text.toString())
            if(radioTea.isChecked) {
                drinkType=spinnerTea.selectedItem.toString()
            } else if (radioCoffee.isChecked) {
                drinkType=spinnerCoffee.selectedItem.toString()
            }
        }
        order = Order(state.name,adv,drinkType)
    }

    private fun onUserChooseTea() {
        binding.apply {
            additives.text =
                String.format(getString(R.string.what_s_else), radioTea.text)
            lemon.visibility = View.VISIBLE
            spinnerCoffee.visibility = View.INVISIBLE
            spinnerTea.visibility = View.VISIBLE

        }
    }
    @Parcelize
    class Order(
        val username:String,
        val additives:ArrayList<String>,
        val drink:String
    ):Parcelable

    companion object {
        @JvmStatic
        private val KEY_STATE = "STATE"
        fun newIntent(context: Context, state: MainActivity.State): Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra(KEY_STATE, state)
            return intent
        }
    }
}

/* Ok, but why code didnt work if we change "getString(R.string.additives_format) " at "binding.additives.text.toString()" ?
.ru
Если вы замените getString(R.string.additives_format) на binding.additives.text.toString(), вы получите исключение java.util.IllegalFormatConversionException во время выполнения, потому что binding.additives.text является CharSequence, а не String.

Метод String.format() ожидает форматную строку в качестве первого аргумента и список аргументов, которые должны быть вставлены в форматную строку. Когда вы передаете binding.additives.text.toString() в качестве форматной строки, метод ожидает вставки одного аргумента в форматную строку. Однако вы передаете два аргумента в метод, что приводит к возникновению исключения.

Поэтому вам следует использовать строку формата ресурса (т.е. getString(R.string.additives_format)) для правильного форматирования текста и избежания ошибок времени выполнения.
.en
If you change getString(R.string.additives_format) to binding.additives.text.toString(), you would get a java.util.IllegalFormatConversionException at runtime because binding.additives.text is a CharSequence and not a String.

The String.format() method expects a format string as the first argument and a list of arguments to be inserted into the format string. When you pass binding.additives.text.toString() as the format string, the method expects a single argument to be inserted into the format string. However, you pass two arguments to the method, which causes the exception to be thrown.

Therefore, you should use a format string resource (i.e., getString(R.string.additives_format)) to format the text properly and avoid runtime errors.
 */