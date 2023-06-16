package com.dicoding.nusatalaapp.domain.use_case.articles.get_article_by_label_id

import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result
import com.dicoding.nusatalaapp.domain.model.ScanResult

class GetArticleByLabelIduseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(token: String, labelId: Int): Flow<Result<ScanResult>> {
        return articleRepository.getArticleByLabelId(token, labelId)
    }
}