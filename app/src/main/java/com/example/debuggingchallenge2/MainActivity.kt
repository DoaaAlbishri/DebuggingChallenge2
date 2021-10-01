package com.example.debuggingchallenge2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var listsRecyclerView: RecyclerView
    lateinit var fabButton: FloatingActionButton
    lateinit var alertDialogSubmitBtn: Button
    private lateinit var sharedPreferences: SharedPreferences
    //ArrayList<CountryAndCapital>
    private var arrayListOfCountriesAndCapitals = arrayListOf(
    CountryAndCapital("Saudi Arabia", "Riyadh"),
    CountryAndCapital("Nigeria", "Abuja"),
    CountryAndCapital("Rwanda", "Kigali"),
    CountryAndCapital("USA", "Washington"),
    CountryAndCapital("China", "Beijing"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabButton = findViewById(R.id.fabBtn)

        setupRecyclerView()


        fabButton.setOnClickListener {

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)


            alertDialogSubmitBtn.setOnClickListener {
                //Store user's input text to variables
                val countryText = countryET.text.toString()
                val capitalText = capitalET.text.toString()

                when {
                    countryText.trim().isEmpty() -> {
                        Toast.makeText(this, "Please enter a country", Toast.LENGTH_LONG).show()
                    }
                    capitalText.trim().isEmpty() -> {
                        Toast.makeText(this, "Please enter a capital", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        //Add single entry list to Global list
                        arrayListOfCountriesAndCapitals.add(CountryAndCapital(countryText, capitalText))

                        listsRecyclerView.adapter?.notifyDataSetChanged()

                        alertDialog.dismiss()
                    }
                }

            }

        }
    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)

        val builder =
                AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }

    private fun setupRecyclerView() {
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(arrayListOfCountriesAndCapitals)
    }
}