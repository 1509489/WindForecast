package com.pixelart.windforecast.ui.detailscreen

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pixelart.windforecast.R

object DirectionsMapper {

    @SuppressLint("SetTextI18n")
    fun windDirectionsCurrent(speedTextVew: TextView, directionImageView: ImageView, direction: Double, speed: Double){
        when {
            //Cardinal directions
            //from s to n
            direction == 0.0 || direction == 360.0 -> {
                speedTextVew.text = "S at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_s_to_n)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from w to e
            direction == 90.0 -> {
                speedTextVew.text = "W at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_w_to_e)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from n to s
            direction == 180.0 -> {
                speedTextVew.text = "N at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_n_to_s)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from e to w
            direction == 270.0 -> {
                speedTextVew.text = "E at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_e_to_w)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }

            //InterCardinal directions
            //from sw to ne
            direction > 0.0 && direction < 90.0 -> {
                speedTextVew.text = "SW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_sw_to_ne)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from nw to se
            direction > 90.0 && direction < 180.0 -> {
                speedTextVew.text = "NW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_nw_to_se)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from ne to sw
            direction > 180.0 && direction < 270.0 -> {
                speedTextVew.text = "NE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_ne_to_sw)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from se to nw
            direction > 270.0 && direction < 360.0 -> {
                speedTextVew.text = "SE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_se_to_nw)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun windDirectionsForecast(speedTextVew: TextView, directionImageView: ImageView, direction: Double, speed: Double){
        when {
            //Cardinal directions
            //from s to n
            direction == 0.0 || direction == 360.0 -> {
                speedTextVew.text = "N at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_s_to_n)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from w to e
            direction == 90.0 -> {
                speedTextVew.text = "E at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_w_to_e)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from n to s
            direction == 180.0 -> {
                speedTextVew.text = "S at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_n_to_s)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from e to w
            direction == 270.0 -> {
                speedTextVew.text = "W at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_e_to_w)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }

            //InterCardinal directions
            //from sw to ne
            direction > 0.0 && direction < 90.0 -> {
                speedTextVew.text = "NE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_sw_to_ne)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from nw to se
            direction > 90.0 && direction < 180.0 -> {
                speedTextVew.text = "SE at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_nw_to_se)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from ne to sw
            direction > 180.0 && direction < 270.0 -> {
                speedTextVew.text = "SW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_ne_to_sw)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
            //from se to nw
            direction > 270.0 && direction < 360.0 -> {
                speedTextVew.text = "NW at $speed meter/sec"

                Glide.with(directionImageView.context)
                    .load(R.drawable.from_se_to_nw)
                    .placeholder(R.drawable.directional_placeholder)
                    .into(directionImageView)
            }
        }
    }
}