package com.raghu.stackex.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.raghu.stackex.R
import com.raghu.stackex.models.User
import kotlinx.android.synthetic.main.activity_user_detail.*
import java.text.SimpleDateFormat
import java.util.*


class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        getSupportActionBar()!!.setTitle(R.string.user)

        val user: User = getIntent().getParcelableExtra("userDetailObj")!!
        loadUser(user)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadUser(user: User) {

        Glide.with(this)
            .load(user.profile_image)
            .placeholder(R.drawable.loading)
            .error(R.drawable.not_found)
            .override(128, 128)
            .into(avatar_iv);

        name_tv.text = user.display_name.trimStart(' ').trimEnd(' ')
        reputation_tv.text = "${getString(R.string.reputation)}: ${user.reputation}"
        badges_tv.text = "${getString(R.string.badges)}: ${user.badge_counts.gold} ${getString(R.string.gold)}, ${user.badge_counts.silver} ${getString(R.string.silver)}, ${user.badge_counts.bronze} ${getString(R.string.bronze)}"

        var locationStr = "Not available"
        if(user.location != null)
            locationStr = user.location
        location_tv.text = "${getString(R.string.location)}: $locationStr"

        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val creationDtStr = simpleDateFormat.format(user.creation_date * 1000L)
        creation_date_tv.text = "${getString(R.string.creation_date)}: $creationDtStr"
    }
}