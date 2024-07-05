package com.example.imcapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.imcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var selectGender: Gender = Gender.NONE

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.title = "CALCULO IMC ADULTOS"
        setContentView(binding.root)
        initUI()
        initListeners()
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun setAge() {
        binding.tvAge.text = IMCService.currentAge.toString()
    }

    private fun setWeight() {
        binding.tvWeight.text = IMCService.currentWeight.toString()
    }

    private fun initListeners() {
        binding.viewMale.setOnClickListener {
            selectGender = Gender.MALE
            setGenderColor()
            IMCService.currentGender = selectGender
        }
        binding.viewFemale.setOnClickListener {
            selectGender = Gender.FEMALE
            setGenderColor()
            IMCService.currentGender = selectGender
        }
        binding.rsHeight.addOnChangeListener { _, value, _ ->
            binding.tvHeight.text = getString(R.string.cm, value.toInt().toString())
            IMCService.currentHeight = value/100
        }
        binding.btnUpWeight.setOnClickListener {
            IMCService.currentWeight = IMCService.currentWeight + 1
            setWeight()
        }
        binding.btnDownWeight.setOnClickListener {
            val currentWeight = IMCService.currentWeight
            if (currentWeight > 40) {
                IMCService.currentWeight = currentWeight - 1
                setWeight()
            } else {
                Toast.makeText(this, "Si pesas menos de 40 anda a un médico", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnUpAge.setOnClickListener {
            IMCService.currentAge = IMCService.currentAge + 1
            setAge()
        }
        binding.btnDownAge.setOnClickListener {
            val currentAge = IMCService.currentAge
            if (currentAge > 1) {
                IMCService.currentAge = currentAge - 1
                setAge()
            } else {
                Toast.makeText(this, "No puedes tener menos de 1 año xD", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCalculator.setOnClickListener {
            if(IMCService.currentGender != Gender.NONE){
                IMCService.calculateIMC()
                startActivity(Intent(this,ResultIMCActivity::class.java))
            }else{
                Toast.makeText(this, "Seleccione su género", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun setGenderColor() {
        binding.viewMale.setBackgroundResource(getBackground(selectGender == Gender.MALE))
        binding.viewFemale.setBackgroundResource(getBackground(selectGender == Gender.FEMALE))
    }

    private fun getBackground(isSelectedComponent: Boolean): Int {
        val backGroundReference = if (isSelectedComponent) {
            R.drawable.item_selected
        } else {
            R.drawable.item_noselected
        }
        return backGroundReference
    }

    private fun showExitConfirmation() {
        val message = " ¿ Quieres salir de la APP ?"
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación de salida")
            .setMessage(message)
            .setPositiveButton("Sí"){_,_ -> finish()}
            .setNegativeButton("No",null).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            showExitConfirmation()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}