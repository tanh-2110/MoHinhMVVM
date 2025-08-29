package com.example.mohinhmvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.mohinhmvvm.R
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mohinhmvvm.data.Sach
import com.example.mohinhmvvm.data.SachViewModel
import com.example.mohinhmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SachViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Nút Lưu sách
        binding.btnLuu.setOnClickListener {
            val name = binding.edTieude.text.toString()
            val tacgia = binding.edTacgia.text.toString()
            val mota = binding.edMota.text.toString()
            val gia = binding.edGiasach.text.toString()
            val status = binding.ckYeuthich.isChecked

            if (name.isEmpty()) {
                binding.edTieude.error = "Không được bỏ trống"
                return@setOnClickListener
            }
            if (tacgia.isEmpty()) {
                binding.edTacgia.error = "Không được bỏ trống"
                return@setOnClickListener
            }
            if (gia.isEmpty()) {
                binding.edGiasach.error = "Không được bỏ trống"
                return@setOnClickListener
            }

            val sach = Sach(
                id = "",
                icon = R.drawable.hehe,
                name = name,
                mota = mota,
                gia = gia,
                status = status
            )

            viewModel.addSach(sach)
            Toast.makeText(this, "Đã lưu sách", Toast.LENGTH_SHORT).show()

            // Xóa input
            binding.edTieude.text.clear()
            binding.edTacgia.text.clear()
            binding.edMota.text.clear()
            binding.edGiasach.text.clear()
            binding.ckYeuthich.isChecked = false

            // Chuyển sang màn danh sách
            startActivity(Intent(this, ListSachActivity::class.java))
        }

        // 🔹 Nút Hủy
        binding.btnHuy.setOnClickListener {
            binding.edTieude.text.clear()
            binding.edTacgia.text.clear()
            binding.edMota.text.clear()
            binding.edGiasach.text.clear()
            binding.ckYeuthich.isChecked = false
        }

        // 🔹 Nút Next → sang List sách
        binding.btnTrangsau.setOnClickListener {
            startActivity(Intent(this, ListSachActivity::class.java))
        }
    }
}
