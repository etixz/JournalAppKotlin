package com.example.journalkotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.journalkotlinapp.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.createAccountBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInBtn.setOnClickListener {
            loginWithEmailPassword(
                binding.email.text.toString().trim(),
                binding.password.text.toString().trim()
            )
        }

        //Auth Ref
        auth = Firebase.auth
    }

    private fun loginWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                task ->
                //Sign in Success
                if(task.isSuccessful) {
                    var journal: JournalUser = JournalUser.instance!!
                    journal.userId = auth.currentUser?.uid
                    journal.username = auth.currentUser?.displayName

                    goToJournalList()
                }else{
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            goToJournalList()
        }
    }

    private fun goToJournalList() {
        var intent: Intent = Intent(this, JournalList::class.java)
        startActivity(intent)
    }
}