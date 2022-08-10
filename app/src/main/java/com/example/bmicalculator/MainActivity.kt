package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.weight_et)
        val heightInput = findViewById<EditText>(R.id.Height_et)
        val calculateBtn =findViewById<Button>(R.id.cal_btn)


        calculateBtn.setOnClickListener {
            val weight =weightInput.text.toString()
            val height = heightInput.text.toString()

            if(ValidateInput(weight,height)){
                val bmi=weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                //result with two decimal places
                val bmi2Digits = String.format("%.2f",bmi).toFloat()
                displayResul(bmi2Digits)
            }
        }
    }

        private  fun ValidateInput(weight:String?,height:String?):Boolean {
            return when {
                weight.isNullOrEmpty() -> {
                    Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                    return false
                }
                height.isNullOrEmpty() -> {
                    Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                    return false
                }
                else->{
                        return  true
                }

            }
        }


    private  fun displayResul(bmi:Float){
        val ResultIndex = findViewById<TextView>(R.id.tv_index)
        val Resultinfo= findViewById<TextView>(R.id.tv_result)
        val info= findViewById<TextView>(R.id.tv_info)


        ResultIndex.text=bmi.toString()
        info.text="(Normal range is 18.5 - 24.9)"
        var resultText= ""
        var color=0

        when{
            bmi<18.50 ->{
                resultText="Underweight"
                color=R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText="You are Healthy"
                color=R.color.normal
            }
            bmi in 24.99..29.99->{
                resultText="Overweight"
                color=R.color.over_wight
            }
            bmi >29.99->{
                resultText="You are Obese"
                color=R.color.obese
            }
        }

        Resultinfo.setTextColor(ContextCompat.getColor(this,color))
        Resultinfo.text= resultText
    }
}