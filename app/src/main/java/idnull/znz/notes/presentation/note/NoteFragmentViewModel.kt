package idnull.znz.notes.presentation.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application):AndroidViewModel(application) {
    fun delete(note:AppNote,onSuccess:()->Unit)=
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note){
                onSuccess()
            }
        }
}