package idnull.znz.notes.presentation.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application):AndroidViewModel(application) {
    fun insert (note:AppNote,onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }  fun edit(note:AppNote,onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.edit(note){
                onSuccess()
            }
        }
    }
}