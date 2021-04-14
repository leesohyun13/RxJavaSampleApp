package m.collaborate.rxjavasampleapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import m.collaborate.data.remote.model.KakaoImageResponse
import m.collaborate.rxjavasampleapp.R
import m.collaborate.rxjavasampleapp.databinding.ImageSearchLisitItemBinding
import m.collaborate.rxjavasampleapp.ui.base.BaseRecyclerViewAdapter
import m.collaborate.rxjavasampleapp.ui.base.BaseViewHolder

class ImageSearchAdapter(
    private val context: Context,
): BaseRecyclerViewAdapter<KakaoImageResponse, ImageSearchAdapter.ImageSearchItemHolder>(
    diffCallback = DiffCallback()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageSearchItemHolder {
        return ImageSearchItemHolder(
            ImageSearchLisitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ImageSearchItemHolder(private val binding: ImageSearchLisitItemBinding): BaseViewHolder<KakaoImageResponse>(binding){
        override fun onBindViewHolder(item: KakaoImageResponse) {

        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<KakaoImageResponse>() {
    override fun areItemsTheSame(oldItem: KakaoImageResponse, newItem: KakaoImageResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: KakaoImageResponse, newItem: KakaoImageResponse): Boolean {
        return oldItem == newItem
    }
}