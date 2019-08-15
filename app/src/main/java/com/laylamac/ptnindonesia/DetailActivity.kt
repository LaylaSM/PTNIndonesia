package com.laylamac.ptnindonesia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvCity: TextView
    private lateinit var imgPhoto: ImageView
    private lateinit var imgLogo: ImageView
    private lateinit var imgWeb: ImageView
    private lateinit var imgPhone: ImageView
    private lateinit var imgEmail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        setActionBarTitle("")

        tvName = findViewById(R.id.tv_item_name_detail)
        tvCity = findViewById(R.id.tv_item_overview_detail)
        imgPhoto = findViewById(R.id.img_item_photo_detail)
        imgLogo = findViewById(R.id.img_item_logo_detail)
        imgWeb = findViewById(R.id.btn_website_detail)
        imgPhone = findViewById(R.id.btn_phone_detail)
        imgEmail = findViewById(R.id.btn_email_detail)

        val name = intent.getStringExtra(EXTRA_NAME)
        val city = intent.getStringExtra(EXTRA_CITY)
        val photo = intent.getStringExtra(EXTRA_IMAGE)
        val logo = intent.getStringExtra(EXTRA_LOGO)
        val web = intent.getStringExtra(EXTRA_WEB)
        val phone = intent.getStringExtra(EXTRA_TELP)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        tvName.text = name
        tvCity.text = city


        Glide.with(this)
            .load(photo)
            .apply(RequestOptions())
            .into(imgPhoto)

        Glide.with(this)
            .load(logo)
            .apply(RequestOptions())
            .into(imgLogo)

        imgWeb.setOnClickListener {
            val goToWeb = Intent(Intent.ACTION_VIEW, Uri.parse(web))
            startActivity(goToWeb)
        }

        imgPhone.setOnClickListener {
            val dialPhone = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(dialPhone)
        }
        imgEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            if (email == "Not Available"){
                Toast.makeText(this,getString(R.string.text_not_available), Toast.LENGTH_SHORT).show()
            } else {
                emailIntent.data = Uri.parse("mailto:$email")
                startActivity(emailIntent)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }


    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CITY = "extra_city"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_LOGO = "extra_logo"
        const val EXTRA_WEB = "extra_web"
        const val EXTRA_TELP = "extra_telp"
        const val EXTRA_EMAIL = "extra_email"
    }

}