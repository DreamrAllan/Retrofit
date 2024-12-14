package com.example.sharedpreferences

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var prefManager: PrefManager
    private val chanelId = "TEST_NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)

        checkLoginStatus()
        with(binding) {
            btnLogin.setOnClickListener {

                val usernameLogin = "admin"
                val passwordLogin = "123"

                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Username dan password tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }  else if (username.equals(usernameLogin) && password.equals(passwordLogin)) {
                    prefManager.saveUsername(username)
                    checkLoginStatus()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnLogout.setOnClickListener {
                prefManager.saveUsername("")
                checkLoginStatus()
            }

            btnClear.setOnClickListener{
                prefManager.clear()
                checkLoginStatus()
            }
            btnNotif.setOnClickListener {
                val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    PendingIntent.FLAG_IMMUTABLE
                }else{
                    0
                }

                val intent = Intent(this@MainActivity, NotifReceiver::class.java).putExtra("Message", "Baca Selengkapnya . . .")

                val pendingIntent = PendingIntent.getBroadcast(
                    this@MainActivity,
                    0,
                    intent,
                    flag
                )

                val builder = NotificationCompat.Builder(this@MainActivity, chanelId)
                    .setSmallIcon(R.drawable.baseline_notifications_24)
                    .setContentText("Apa iya dek")
                    .setContentTitle("Notifikasi PPPB")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)

                val  notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    val notificationChannel = NotificationChannel(
                        chanelId,
                        "Notif PPPAB",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    with(notificationManager) {
                        createNotificationChannel(notificationChannel)
                        notify(0, builder.build())
                    }
                }else {
                    notificationManager.notify(0, builder.build())
                }
            }
        }

    }
    fun checkLoginStatus() {
        val isLoggedIn = prefManager.getUsername()
        if (isLoggedIn.isEmpty()) {
            binding.llLogin.visibility = View.VISIBLE
            binding.llLogged.visibility = View.GONE
        } else {
            binding.llLogin.visibility = View.GONE
            binding.llLogged.visibility = View.VISIBLE
        }
    }
}