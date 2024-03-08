package com.nazhiba.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?= null
    var lastNumerik: Boolean = false
    var lasttitik: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvInput = findViewById(R.id.tvInput)


    }
    fun onDigit(view: View){
//        Toast.makeText(this,"Button clicked", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastNumerik = true
        lasttitik = false
    }

    fun onClear(view: View){
        tvInput?.text = "0"
    }

    fun onDesimal(view: View){
        if (lastNumerik && !lasttitik){
            tvInput?.append(".")
            lastNumerik = false
            lasttitik = true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNumerik && !isoperatorditambahkan(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumerik = false
                lasttitik = false
            }

        }
    }

    fun onEqual(view: View){
        if (lastNumerik){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var jawaban = one.toDouble() - two.toDouble()
                    tvInput?.text = HapusNolSetelahTitik(jawaban.toString())
                }

                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var jawaban = one.toDouble() + two.toDouble()
                    tvInput?.text = HapusNolSetelahTitik(jawaban.toString())
                }

                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var jawaban = one.toDouble() / two.toDouble()
                    tvInput?.text = HapusNolSetelahTitik(jawaban.toString())
                }

                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    var jawaban = one.toDouble() * two.toDouble()
                    tvInput?.text = HapusNolSetelahTitik(jawaban.toString())
                }



            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun HapusNolSetelahTitik(jawaban:String):String{
        var value = jawaban
        if (jawaban.contains(".0"))
            value = jawaban.substring(0, jawaban.length - 2)

        return value
    }

    private fun isoperatorditambahkan(value: String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }

}