package com.example.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.login.family.Constant
import com.example.login.family.Family
import com.example.login.family.FamilyDB
import kotlinx.android.synthetic.main.activity_edit_family.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFamily : AppCompatActivity() {
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val CHANNEL_ID_2 = "channel_notification_02"
    private val notificationId2 = 102
    val db by lazy { FamilyDB(this) }
    private var noteId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_family)
        setupView()
        setupListener()
//
        Toast.makeText(this, noteId.toString(),Toast.LENGTH_SHORT).show()
    }
    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getNote()
            }
        }
    }
    private fun setupListener() {
        createNotificationChannel()
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Family(0,edit_title.text.toString(),
                        edit_note.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().updateNote(
                    Family(noteId, edit_title.text.toString(),
                        edit_note.text.toString())
                )
                sendNotification2()
                finish()
            }
        }
    }
    fun getNote() {
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNote(noteId)[0]
            edit_title.setText(notes.name)
            edit_note.setText(notes.age)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
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
        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_supervised_user_circle_24)
            .setContentTitle("Family Changed!")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Data keluarga telah berhasil diganti harap pastikan bahwa data yang anda masukkan benar, jika salah harap diganti kembali karena berkaitan dengan tiket yang akan dicetak."))

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId2, builder.build())
        }
    }
}