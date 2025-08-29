package com.example.mohinhmvvm.data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mohinhmvvm.databinding.ActivityItemBinding

class SachAdapter(
    private var listSach: MutableList<Sach>,
    private val onItemClick: (Sach) -> Unit,
    private val onItemLongClick: (Sach) -> Unit
) : RecyclerView.Adapter<SachAdapter.SachViewHolder>() {

    inner class SachViewHolder(val binding: ActivityItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SachViewHolder {
        val binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SachViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SachViewHolder, position: Int) {
        val s = listSach[position]

        with(holder.binding) {
            ivitem.setImageResource(s.icon)
            tvitensach.text = s.name
            tvmotasach.text = s.mota
            tvnhapgiasach.text = s.gia
            ckchonV.isChecked = s.status

            // click item
            root.setOnClickListener { onItemClick(s) }

            // long click để xóa
            root.setOnLongClickListener {
                onItemLongClick(s)
                true
            }

            // check thay đổi trạng thái
            ckchonV.setOnCheckedChangeListener { _, checked ->
                s.status = checked
            }
        }
    }

    override fun getItemCount(): Int = listSach.size

    fun updateList(newList: List<Sach>) {
        listSach.clear()
        listSach.addAll(newList)
        notifyDataSetChanged()
    }
}
