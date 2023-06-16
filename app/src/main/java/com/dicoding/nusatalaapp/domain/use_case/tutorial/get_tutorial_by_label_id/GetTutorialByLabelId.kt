package com.dicoding.nusatalaapp.domain.use_case.tutorial.get_tutorial_by_label_id

import com.dicoding.nusatalaapp.domain.model.Tutorial
import com.dicoding.nusatalaapp.domain.repository.TutorialRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.dicoding.nusatalaapp.common.Result

class GetTutorialByLabelId @Inject constructor(
    private val tutorialRepository: TutorialRepository
) {
    suspend operator fun invoke(token: String, labelId: Int): Flow<Result<Tutorial>>  {
        return tutorialRepository.getTutorialByLabel(token, labelId)
    }
}