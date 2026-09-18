package com.example.ltdd_t5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ltdd_t5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentStudent: Student? = Student(
        id = "2415053122238",
        name = "Nguyen Vo Duy Son",
        className = "126LTDD02",
        email = "nvdson@ute.udn.vn",
        phone = "0905123456",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        currentStudent?.let { bindStudentData(it) }

        // Cập nhật GPA
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.trimmedText()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "GPA không hợp lệ"
                toast("Vui lòng kiểm tra lại điểm GPA!")
                return@setOnClickListener
            }

            currentStudent = currentStudent?.copy(gpa = newGpa)?.also {
                bindStudentData(it)
                toast("Cập nhật điểm thành công!")
            }
        }

        // Yêu cầu 1: Nút Gọi điện mở ứng dụng cuộc gọi dùng Intent
        binding.btnCall.setOnClickListener {
            currentStudent?.let { student ->
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${student.phone}")
                }
                startActivity(intent)
            }
        }

        // Yêu cầu 2: Nút Xóa hồ sơ hiển thị AlertDialog xác nhận
        binding.btnDelete.setOnClickListener {
            showConfirmDialog(
                title = "Xác nhận xóa",
                message = "Bạn có chắc chắn muốn xóa hồ sơ sinh viên này?"
            ) {
                currentStudent = null
                binding.root.gone() // Ẩn giao diện sau khi xóa
                toast("Đã xóa hồ sơ thành công!")
            }
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}