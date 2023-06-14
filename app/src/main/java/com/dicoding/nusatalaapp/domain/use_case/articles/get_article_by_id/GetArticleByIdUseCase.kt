package com.dicoding.nusatalaapp.domain.use_case.articles.get_article_by_id

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class GetArticleByIdUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(token: String, id: Int): Flow<Result<Article>> {
        return articleRepository.getArticleById(token, id)
    }
}