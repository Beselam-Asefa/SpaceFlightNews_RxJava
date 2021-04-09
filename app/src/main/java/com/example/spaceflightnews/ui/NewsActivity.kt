package com.example.spaceflightnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflightnews.adapter.NewsAppAdapter
import com.example.spaceflightnews.databinding.ActivityMainBinding
import com.example.spaceflightnews.util.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * this class represent the screen the app will display the news
 * I used view binding to reference the views
 */

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private val newsAppViewModel: NewsAppViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding
    lateinit var newsAppAdapter: NewsAppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // trigger the network call to get the news
        binding.getNewsFabButton.setOnClickListener {
            binding.userGuidTextView.visibility = View.GONE
            newsAppViewModel.getNews()
        }

        newsAppViewModel.articles.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAppAdapter.differ.submitList(newsResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    displayError(response.message)
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAppAdapter = NewsAppAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAppAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }


}