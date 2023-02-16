package com.example.rosmontis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val logintext: TextView = findViewById(R.id.textView_login_now)
        logintext.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton : Button = findViewById(R.id.button_register)
        registerButton.setOnClickListener {
            performSignUp()
        }


    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.editText_email_register)
        val passwod = findViewById<EditText>(R.id.editText_password_register)

        if (email.text.isEmpty() || passwod.text.isEmpty()){
            Toast.makeText(this,"Pleass fill all  fields",Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputpassword = passwod.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)


                } else {

                    Toast.makeText(
                        baseContext, "Success.",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error Occurred ${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }
    }

}
