package com.example.imcapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.content.ContextCompat
import com.example.imcapp.databinding.ActivityResultImcactivityBinding

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultImcactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "RESULTADO IMC"
        initUI(IMCService.resultIMC)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRecalculate.setOnClickListener {
            showExitConfirmation()
        }
    }

    private fun showExitConfirmation() {
        val message = " ¿ Quieres recalcular tu IMC ?"
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmación de salida")
                .setMessage(message)
                .setPositiveButton("Sí"){_,_ -> finish()}
                .setNegativeButton("No",null).show()
    }

    private fun initUI(resultIMC: Float) {
        binding.tvIMC.text = resultIMC.toString()
        when(resultIMC){
            in 0.00..18.50 -> {
                binding.tvResult.text = getString(R.string.title_bajo_peso)
                binding.tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                binding.tvDescription.text = getString(R.string.bajoPesoDes, resultIMC.toString())
            }
            in 18.51..24.99 -> {
                binding.tvResult.text = getString(R.string.title_peso_normal)
                binding.tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                binding.tvDescription.text = getString(R.string.pesonormalDes, resultIMC.toString())
            }
            in 25.00..29.99 -> {
                binding.tvResult.text = getString(R.string.title_sobrepeso)
                binding.tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_sobrepeso))
                binding.tvDescription.text = getString(R.string.sobrepesoDes, resultIMC.toString())
            }
            in 30.00..99.00 -> {
                binding.tvResult.text = getString(R.string.title_obesidad)
                binding.tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                binding.tvDescription.text = getString(R.string.obesidadDes, resultIMC.toString())
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        showExitConfirmation()
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            showExitConfirmation()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}