package com.pixelart.windforecast.ui.detailscreen

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pixelart.windforecast.R

object DirectionsMapper {

    /**
     * More conditions can be check to get an accurate direction
     * The other 8 directions like (WNW, NNW, WSW, SSW, ENE, NNE, etc)
     * can be added to the conditions
     * */

    @SuppressLint("SetTextI18n")
    fun windDirections(speedTextVew: TextView, directionImageView: ImageView, direction: Double, speed: Double){
        when {
            //Cardinal directions
            //from n to s
            direction == 0.0 || direction == 360.0 -> {
                speedTextVew.text = "N at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.south)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from e to w
            direction == 90.0 -> {
                speedTextVew.text = "E at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.west)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from s to n
            direction == 180.0 -> {
                speedTextVew.text = "S at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.north)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from w to e
            direction == 270.0 -> {
                speedTextVew.text = "W at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.east)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }

            //InterCardinal directions
            //from ne to sw
            direction > 0.0 && direction < 90.0 -> {
                speedTextVew.text = "NE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.southwest)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from se to nw
            direction > 90.0 && direction < 180.0 -> {
                speedTextVew.text = "SE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.northwest)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from sw to ne
            direction > 180.0 && direction < 270.0 -> {
                speedTextVew.text = "SW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.northeast)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from nw to se
            direction > 270.0 && direction < 360.0 -> {
                speedTextVew.text = "NW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.southeast)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
        }
    }
}