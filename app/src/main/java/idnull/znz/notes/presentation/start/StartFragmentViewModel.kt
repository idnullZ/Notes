package idnull.znz.notes.presentation.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import idnull.znz.notes.database.firebase.AppFirebaseRepository
import idnull.znz.notes.database.room.AppRoomDatabase
import idnull.znz.notes.database.room.AppRoomRepository
import idnull.znz.notes.utils.REPOSITORY
import idnull.znz.notes.utils.TYPE_FIREBASE
import idnull.znz.notes.utils.TYPE_ROOM
import idnull.znz.notes.utils.showToast

class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext =application
    fun initDataBase(type:String,onSuccess:()->Unit){
        when(type){
            TYPE_ROOM->{
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE->{
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()},{ showToast(it)})


            }
        }
        
    }


}