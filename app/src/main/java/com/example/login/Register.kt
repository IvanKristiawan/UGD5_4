package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import android.view.View
import android.widget.Toast
import com.example.login.databinding.ActivityRegisterBinding
import com.example.login.user.Constant
import com.example.login.user.User
import com.example.login.user.UserDB
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val db by lazy { UserDB(this) }
    private lateinit var username : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var btnRegister : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("")

        username = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            intent.putExtra("register", mBundle)

            if (username.text.toString().length == 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().addNote(
                        User(
                            1, "a",
                            "a"
                        )
                    )
                    finish()
                }

                startActivity(intent)
            }

            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    User(
                        (Math.random() * (10000 - 3 + 1)).toInt(), username.text.toString(),
                        password.text.toString()
                    )
                )
                finish()
            }

            startActivity(intent)
        }
        }

//        btnRegister.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            val mBundle = Bundle()
//            mBundle.putString("username",username.text.toString())
//            mBundle.putString("password",password.text.toString())
//            intent.putExtra("register", mBundle)
//
//            if(username.text.toString().length == 0){
//                CoroutineScope(Dispatchers.IO).launch {
//                    db.noteDao().addNote(
//                        User(1, "a",
//                            "a")
//                    )
//                    finish()
//                }
//
//                startActivity(intent)
//            }
//
//            CoroutineScope(Dispatchers.IO).launch {
//                db.noteDao().addNote(
//                    User((Math.random() * (10000 - 3 + 1)).toInt(), username.text.toString(),
//                        password.text.toString())
//                )
//                finish()
//            }
//
//            startActivity(intent)
//        }
//        }

//        btnRegister.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            val mBundle = Bundle()
//            mBundle.putString("username",username.text.toString())
//            mBundle.putString("password",password.text.toString())
//            intent.putExtra("register", mBundle)
//
//            if(username.text.toString().length == 0){
//                CoroutineScope(Dispatchers.IO).launch {
//                    db.noteDao().addNote(
//                        User(1, "a",
//                            "a")
//                    )
//                    finish()
//                }
//
//                startActivity(intent)
//            }
//
//            CoroutineScope(Dispatchers.IO).launch {
//                db.noteDao().addNote(
//                    User((Math.random() * (10000 - 3 + 1)).toInt(), username.text.toString(),
//                        password.text.toString())
//                )
//                finish()
//            }
//
//            startActivity(intent)
//        }
//
//    }
}