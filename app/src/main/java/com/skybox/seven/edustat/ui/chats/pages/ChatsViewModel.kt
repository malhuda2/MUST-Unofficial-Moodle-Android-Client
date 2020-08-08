package com.skybox.seven.edustat.ui.chats.pages

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skybox.seven.edustat.api.MoodleService
import com.skybox.seven.edustat.model.Conversation
import com.skybox.seven.edustat.repository.PrefRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatsViewModel @ViewModelInject constructor(private val moodleService: MoodleService,
                                                  private val  prefRepository: PrefRepository): ViewModel() {
    val chatList:MutableLiveData<List<Conversation>> = MutableLiveData()

    fun getConversations() {
        moodleService.getAllChats(prefRepository.getUserID(), 0, 20, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it != null)
                        chatList.value = it.conversations
                    else
                        chatList.value = ArrayList()
                },
                {
                    it.printStackTrace()
                })
    }
}