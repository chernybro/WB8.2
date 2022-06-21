package com.chernybro.wb52.presentation.hero_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chernybro.wb52.data.repository.HeroesListRepository
import com.chernybro.wb52.domain.models.HeroItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository
) : ViewModel() {

    private val _heroes: MutableLiveData<List<HeroItem>> = MutableLiveData()
    val heroes: LiveData<List<HeroItem>> = _heroes

    private val _isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _heroes.postValue(repository.getHeroes())
            _isRefreshing.postValue(false)
        }
    }

}