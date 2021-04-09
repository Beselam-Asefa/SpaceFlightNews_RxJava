package com.example.spaceflightnews.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.network.NetworkArticleEntity
import com.example.spaceflightnews.network.NetworkMapper
import com.example.spaceflightnews.network.NewsApi
import com.example.spaceflightnews.network.ResponseHandler
import com.example.spaceflightnews.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io

/**
 * the view model class
 * I used constructor injection to access the default repository
 * I used two  article LiveData's , one is private  the other one is not
 * and I used it to observe the Api response in the main activity
 */

class NewsAppViewModel @ViewModelInject constructor(
    private val newsApi: NewsApi,
    private val networkMapper: NetworkMapper,
    private val responseHandler: ResponseHandler
) :
    ViewModel() {

    private lateinit var subscription: Disposable
    private val _articles = MutableLiveData<Resource<List<Article>>>()
    val articles: LiveData<Resource<List<Article>>> = _articles
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getNews() {
        subscription = newsApi.getNews()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveArticleListStart() }
            .subscribe(
                { result -> onRetrieveArticleListSuccess(result) },
                { e -> onRetrieveArticleListError(e) }
            )
    }

    private fun onRetrieveArticleListStart() {
        _articles.value = Resource.Loading()
    }

    private fun onRetrieveArticleListSuccess(response: List<NetworkArticleEntity>) {
        val domainArticles = networkMapper.mapFromNetworkArticleList(response)
        _articles.value = responseHandler.handleSuccess(domainArticles)
    }

    private fun onRetrieveArticleListError(e: Throwable) {
        _articles.value = responseHandler.handleException(e)
    }
}