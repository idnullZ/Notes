package idnull.znz.notes.database.room

import androidx.lifecycle.LiveData
import idnull.znz.notes.database.DatabaseRepository
import idnull.znz.notes.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao):DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
    appRoomDao.delete(note)
        onSuccess()
    }

    override suspend fun edit(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.edit(note)
    }

    override fun signOut() {
        super.signOut()
    }

}