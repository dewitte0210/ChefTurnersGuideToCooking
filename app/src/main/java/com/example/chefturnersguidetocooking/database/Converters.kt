package com.example.chefturnersguidetocooking.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {
    @TypeConverter
    fun imgToBlob(bitMap: Bitmap): ByteArray? {
        val byteOut: ByteArrayOutputStream = ByteArrayOutputStream()
        bitMap.compress(Bitmap.CompressFormat.PNG, 100, byteOut)
        return byteOut.toByteArray()
    }

    @TypeConverter
    fun blobToImg(byteArray: ByteArray): Bitmap? {
       return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}