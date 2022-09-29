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
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.login.databinding.ActivityMainBinding

class Register : AppCompatActivity() {
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val CHANNEL_ID_2 = "channel_notification_02"
    private val notificationId2 = 102

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
            createNotificationChannel()

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

            sendNotification2()

            startActivity(intent)
            }
        }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(CHANNEL_ID_1, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val channel2 = NotificationChannel(CHANNEL_ID_2, name, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }

    private fun sendNotification2() {
        val YesIntent = Intent(this, MainActivity::class.java)
        YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val YesPendingIntent = PendingIntent.getActivity(this, 0, YesIntent, PendingIntent.FLAG_MUTABLE)


        val picture = BitmapFactory.decodeResource(resources, R.drawable.login)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_supervised_user_circle_24)
            .setContentTitle(binding?.etUsername?.text.toString())
            .setContentText(binding?.etPassword?.text.toString())
            .setLargeIcon(picture)
            .addAction(R.drawable.ic_baseline_supervised_user_circle_24, "Yes", YesPendingIntent)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigLargeIcon(null)
                .bigPicture((picture)))
            .setPriority(NotificationCompat.PRIORITY_LOW)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId2, builder.build())
        }
    }
}