package idnull.znz.notes.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import idnull.znz.notes.utils.REPOSITORY

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {

    val allNotes = REPOSITORY.allNotes
    fun signOut(){

    }
}