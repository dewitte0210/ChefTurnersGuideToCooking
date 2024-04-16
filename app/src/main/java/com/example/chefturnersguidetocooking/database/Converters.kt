package com.example.chefturnersguidetocooking.database

import android.graphics.BitmapFactory
import android.media.Image
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun imgToBlob(img: Image){
       // BitmapFactory
    }
}