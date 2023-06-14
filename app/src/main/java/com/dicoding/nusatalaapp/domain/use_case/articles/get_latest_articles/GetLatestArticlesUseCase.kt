package com.dicoding.nusatalaapp.domain.use_case.articles.get_latest_articles

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class GetLatestArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<Article>>> {
        return articleRepository.getLatestArticles(token)
    }
}