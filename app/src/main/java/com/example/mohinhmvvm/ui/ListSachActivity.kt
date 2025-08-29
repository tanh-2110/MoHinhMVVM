package com.example.mohinhmvvm.ui
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohinhmvvm.data.Sach
import com.example.mohinhmvvm.data.SachAdapter
import com.example.mohinhmvvm.data.SachViewModel
import com.example.mohinhmvvm.databinding.ActivityListsachBinding
import androidx.activity.viewModels

class ListSachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListsachBinding
    private val viewModel: SachViewModel by viewModels()
    private lateinit var adapter: SachAdapter

    private lateinit var sachDangChon: Sach

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListsachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SachAdapter(
            listSach = mutableListOf(),
            onItemClick = { sach ->   // click item
                binding.edTensachh.setText(sach.name)
                binding.edMotaa.setText(sach.mota)
                binding.edGiaa.setText(sach.gia)
                binding.ckChonV.isChecked = sach.status
                sachDangChon = sach
            },
            onItemLongClick = { sach ->   // long click
                viewModel.deleteSach(sach.id)
            }
        )

        // 1️⃣ Setup RecyclerView
        binding.rcSach.layoutManager = LinearLayoutManager(this)
        binding.rcSach.adapter = adapter

        // 2️⃣ Quan sát LiveData
        viewModel.listSach.observe(this) { list ->
            adapter.updateList(list)
        }

        // 3️⃣ Load dữ liệu từ Firebase
        viewModel.loadData()

        // 4️⃣ Nút thêm
        binding.btnThem.setOnClickListener {
            val sach = Sach(
                id = "",
                name = binding.edTensachh.text.toString(),
                mota = binding.edMotaa.text.toString(),
                gia = binding.edGiaa.text.toString(),
                status = binding.ckChonV.isChecked
            )
            viewModel.addSach(sach)
        }

        // 5️⃣ Nút sửa
        binding.btnSua.setOnClickListener {
            sachDangChon?.let {
                it.name = binding.edTensachh.text.toString()
                it.mota = binding.edMotaa.text.toString()
                it.gia = binding.edGiaa.text.toString()
                it.status = binding.ckChonV.isChecked
                viewModel.updateSach(it)
            } ?: Toast.makeText(this, "Chưa chọn sách để sửa", Toast.LENGTH_SHORT).show()
        }

        // 6️⃣ Nút xóa
        binding.btnXoa.setOnClickListener {
            sachDangChon?.let {
                viewModel.deleteSach(it.id)
            } ?: Toast.makeText(this, "Chưa chọn sách để xóa", Toast.LENGTH_SHORT).show()
        }
    }
}