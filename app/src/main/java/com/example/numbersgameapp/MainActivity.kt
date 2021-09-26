package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.random.Random
import kotlin.collections.ArrayList as ArrayList1

class MainActivity : AppCompatActivity() {
    lateinit var userGuess: EditText
    lateinit var guesses: ArrayList<String>
    lateinit var myRV :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guesses = arrayListOf<String>()
         myRV = findViewById<RecyclerView>(R.id.rvMain)
        userGuess = findViewById<EditText>(R.id.editText)
        val SendButton = findViewById<Button>(R.id.button)
        SendButton.setOnClickListener { guessCheck()}


    }
    var counter = 0
    fun guessCheck() {
        var random = Random.nextInt(10).toInt()
            if(counter<3){
            if (userGuess.text.toString().toInt() == random) {
                guesses.add("you guessed" + userGuess.text)
                guesses.add("Thats Right")
               counter=3
            }
            else{
                guesses.add("you guessed " + userGuess.text)
                counter++
                guesses.add("you have " + (3 - counter )+ " guesses left")

            }
}
        else{customAlert()}
        myRV.adapter = RecyclerViewAdapter(guesses)
        myRV.layoutManager = LinearLayoutManager(this)
    }
    fun customAlert(){

        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do You Want Play Again?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id ->this.recreate()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id ->dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("New Game")

        alert.show()
    }
}



