package com.example.spaceflightnews.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spaceflightnews.databinding.ArticlePreviewBinding
import com.example.spaceflightnews.model.Article

/**
 * adapter class for the recycler view
 * it uses diffUtil
 * view binding for referencing the view
 */
class NewsAppAdapter : RecyclerView.Adapter<NewsAppAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(val binding: ArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(
            ArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(articleImageView.context).load(article.imageUrl).into(articleImageView)
            tvSource.text = article.newsSite
            tvTitle.text = article.title
            tvDescription.text = article.summary
            tvPublishedAt.text = article.publishedAt

        }


    }

}