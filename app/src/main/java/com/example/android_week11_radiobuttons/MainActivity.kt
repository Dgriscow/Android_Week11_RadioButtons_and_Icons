package com.example.android_week11_radiobuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    //Exchange rate Values
    val pesoToUsdRate:Double = 350.04

    var outputString:String = ""

    var lastPressedButtonID:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Todo create functions to handle different conversions




        /*
        When The conversion button is pressed its going to prefom a when switch,
        depending on the current selected radioButton it will convert the currency over to that price.
         */


        //On Screen Elements
        val currencyEntry:EditText = findViewById(R.id.currencyEntry)
        val calculateButton:Button = findViewById(R.id.calc)


        var currencyChoices:RadioGroup = findViewById(R.id.currencyChoices)

        val sel_usd_to_arg:RadioButton = findViewById(R.id.usd2arg)
        val sel_arg_to_usd:RadioButton = findViewById(R.id.arg2usd)











        //Todo create radio button activations to automatically set pressed button
        sel_usd_to_arg.setOnClickListener {
            lastPressedButtonID = 2131231227
        }

        sel_arg_to_usd.setOnClickListener {
            lastPressedButtonID = 2131231228

        }







        //Todo create the conversion button
        calculateButton.setOnClickListener {
            /*
            As soon as the button is clicked, first set a variable equal to the current toggled radioButton in the group
            then switch on whatever button is selected, whatever is selected call a relevant function to handle conversions seperately.
             */

            //set selected button to a variable
            var checkedButton:Int = currencyChoices.checkedRadioButtonId

            Log.d("RADIO?", "$checkedButton")
            //Due To checkRadiobyID returning an absurd number, double check prior to publish
            if (checkedButton != lastPressedButtonID){
                lastPressedButtonID = checkedButton
                //set the last ID to the current ID

                //Now switch on the Different IDs
                when(checkedButton){
                    //usd to argentine radio selected
                    2131231228 -> usdToPeso(currencyEntry.text.toString())
                    2131231227 -> pesoToUsd(currencyEntry.text.toString())


                }
                //set the output text
                currencyEntry.setText(outputString)
            }else{
                //give a error
                currencyEntry.error = "Error\nThis Option was Already Selected!"
            }






        }



    }


    fun usdToPeso(usd:String){
        //All Converts will function the same:
        /*
        when preparing to convert, just convert the number entered into whatever the user desires.
         */

        //get the currency text and set it to a variable
        //Use a regex to remove all alphanumeric values from the user entry, cause kotlin/java is goofy.

        val regex = Regex("[0-9]+(\\.[0-9]+)?")

        val matches = regex.findAll(usd)

        val convertedAmount:Double = matches.map{ it.value }.joinToString("").toDouble() * pesoToUsdRate
            //.toDouble()

        outputString = "$convertedAmount Argentine Peso"

    }

    fun pesoToUsd(usd:String){
        //All Converts will function the same:
        /*
        when preparing to convert, just convert the number entered into whatever the user desires.
         */

        //get the currency text and set it to a variable
        //Use a regex to remove all alphanumeric values from the user entry, cause kotlin/java is goofy.

        val regex = Regex("[0-9]+(\\.[0-9]+)?")

        val matches = regex.findAll(usd)

        val convertedAmount:Double = matches.map{ it.value }.joinToString("").toDouble() / pesoToUsdRate
        //.toDouble()

        outputString = "$$convertedAmount"

    }





}