package com.chernybro.wb52.presentation.hero_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chernybro.wb52.data.repository.HeroesListRepository
import com.chernybro.wb52.domain.models.HeroDetailsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(
    private val heroesRepository: HeroesListRepository
) : ViewModel() {
    private val _heroDetails: MutableLiveData<HeroDetailsItem> = MutableLiveData()
    val heroDetails: LiveData<HeroDetailsItem> = _heroDetails


    fun getHeroDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _heroDetails.postValue(heroesRepository.getHero(id.toString()))
        }
    }
}