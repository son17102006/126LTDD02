package com.example.ltdd_t5

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

// Extension Functions cho UI
fun View.show() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun EditText.trimmedText(): String = text.toString().trim()

fun Double.toAcademicRanking(): String = when {
    this >= 3.6 -> "Xuất sắc!"
    this >= 3.2 -> "Giỏi"
    this >= 2.5 -> "Khá"
    else        -> "Trung bình"
}

// Extension hiển thị AlertDialog xác nhận
fun Context.showConfirmDialog(title: String, message: String, onConfirm: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Đồng ý") { _, _ -> onConfirm() }
        .setNegativeButton("Hủy", null)
        .show()
}