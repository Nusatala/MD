package com.dicoding.nusatalaapp.domain.use_case.articles.get_articles_by_views

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class GetArticlesByViewsUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<List<Article>>> {
        return articleRepository.getArticlesByViews(token)
    }
}