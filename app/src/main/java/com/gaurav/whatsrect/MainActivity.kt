package com.gaurav.whatsrect

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.gaurav.whatsrect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#22252D")))
        supportActionBar!!.title =
            Html.fromHtml("<font color='#ffa500'>" + "Whatsapp Direct" + "</font>")
        window.statusBarColor = resources.getColor(R.color.black_shade_3)

        Toast.makeText(this@MainActivity, "Desined and Implemented by Gaurav", Toast.LENGTH_LONG).show()



        binding.send.setOnClickListener(View.OnClickListener { view ->

            openWhatsapp()
        })


    }


    private fun openWhatsapp() {

        try {
            var cc = binding.countryCodePicker.selectedCountryCode
            val userNum = cc + binding.userNumber.text.toString()
            val message = binding.inputText.text.toString()

//            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/$userNum?text=$message")
            )
            intent.setPackage("com.whatsapp")
            startActivity(intent)

            /*
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.putExtra("jid", userNum + "@s.whatsapp.net")
            intent.setPackage("com.whatsapp")
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Whatsapp Not Installed", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
             */


        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(
                this,
                "Whatsapp app not installed in your phone",
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }

    }
}