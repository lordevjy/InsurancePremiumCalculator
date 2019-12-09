package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        btnCalculate.setOnClickListener(){
            myData.premiumAmount = CalculatePremium()
        }

        btnReset.setOnClickListener(){
            spinAge.setSelection(0)
            rbMale.setChecked(false)
            rbFemale.setChecked(false)
            cbSmoker.setChecked(false)
            myData.premiumAmount = 0.0
            txtTotal.text = "0.0"
        }


    }

    fun display(){
        if(myData.premiumAmount != 0.0) {
            txtTotal.text = myData.premiumAmount.toString()
        }
    }

    fun CalculatePremium(): Double {
        var premium:Double = 0.0

        if (spinAge.selectedItem.toString() == "Less than 17"){
            premium = 60.0
        }else if (spinAge.selectedItem.toString() == "17 - 25"){
            premium = 70.0
            if (rbMale.isChecked == true){
                premium += 50.0
            }
            if (cbSmoker.isChecked == true){
                premium += 100.0
            }
        }else if (spinAge.selectedItem.toString() == "26 - 30"){
            premium = 90.0
            if (rbMale.isChecked == true){
                premium += 100.0
            }
            if (cbSmoker.isChecked == true){
                premium += 150.0
            }
        }else if (spinAge.selectedItem.toString() == "31 - 40") {
            premium = 120.0
            if (rbMale.isChecked == true) {
                premium += 150.0
            }
            if (cbSmoker.isChecked == true) {
                premium += 200.0
            }
        }else if (spinAge.selectedItem.toString() == "41 - 55") {
            premium = 150.0
            if (rbMale.isChecked == true) {
                premium += 200.0
            }
            if (cbSmoker.isChecked == true) {
                premium += 250.0
            }
        }else{
            premium = 150.0
            if (rbMale.isChecked == true) {
                premium += 200.0
            }
            if (cbSmoker.isChecked == true) {
                premium += 300.0
            }
        }

        txtTotal.text = premium.toString()

        return premium
    }
}
