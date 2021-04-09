package com.example.spaceflightnews.network

import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.util.EntityMapper
import javax.inject.Inject

/**
 * map the network article to the project article
 * the mapper class help us to  easily replace the network module in case needed
 *
 */
class NetworkMapper @Inject constructor() : EntityMapper<NetworkArticleEntity, Article> {

    override fun mapFromNetworkEntity(entity: NetworkArticleEntity): Article {
        return Article(
            id = entity.id,
            imageUrl = entity.imageUrl,
            newsSite = entity.newsSite,
            publishedAt = entity.publishedAt,
            summary = entity.summary,
            title = entity.title,
            url = entity.url
        )
    }

    override fun mapToNetworkEntity(domainModel: Article): NetworkArticleEntity {
        return NetworkArticleEntity(
            id = domainModel.id,
            imageUrl = domainModel.imageUrl,
            newsSite = domainModel.newsSite,
            publishedAt = domainModel.publishedAt,
            summary = domainModel.summary,
            title = domainModel.title,
            url = domainModel.url
        )
    }

    fun mapFromNetworkArticleList(entities: List<NetworkArticleEntity>): List<Article> {
        return entities.map {
            mapFromNetworkEntity(it)
        }
    }
}