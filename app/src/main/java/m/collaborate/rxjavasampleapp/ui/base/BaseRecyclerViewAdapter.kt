package m.collaborate.rxjavasampleapp.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<ITEM: Any?, H: BaseViewHolder<ITEM>> (
    diffCallback: DiffUtil.ItemCallback<ITEM>
): ListAdapter<ITEM, H>(diffCallback) {
    private val items = mutableListOf<ITEM>()

    fun replaceAll(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
    }

    fun addItems(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                addAll(it)
            }
        }
    }

    fun getScaleSizeHeight(
        width: Int,
        height: Int,
        scaleWidth: Int
    ): Int {
        return height * scaleWidth / width
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.onBindViewHolder(items[position])
    }

    override fun getItemCount(): Int = items.size
}

abstract class BaseViewHolder<ITEM>(view: ViewBinding): RecyclerView.ViewHolder(view.root) {
    abstract fun onBindViewHolder(item: ITEM)
}