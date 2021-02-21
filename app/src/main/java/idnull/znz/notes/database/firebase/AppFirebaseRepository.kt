package idnull.znz.notes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import idnull.znz.notes.database.DatabaseRepository
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.*

class AppFirebaseRepository : DatabaseRepository {


    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun edit(note: AppNote, onSuccess: () -> Unit) {

        REF_DATABASE.child(note.idFirebase).child(NAME).setValue(note.name)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }

    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        if (AppPreference.getInitUser()) {
            initRefs()
            onSuccess()

        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess()
                }
                .addOnFailureListener() {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess()
                        }
                        .addOnFailureListener {
                            onFail(it.message.toString())
                        }
                }
        }
    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}