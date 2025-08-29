package com.example.mohinhmvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mohinhmvvm.data.TaiKhoanViewModel
import com.example.mohinhmvvm.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: TaiKhoanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            viewModel.register(email, pass)
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "Dang ky thanh cong ${user.email}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Dang ky that bai!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
