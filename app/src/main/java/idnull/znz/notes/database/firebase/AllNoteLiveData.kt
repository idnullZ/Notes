package idnull.znz.notes.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.REF_DATABASE

class AllNoteLiveData:LiveData<List<AppNote>>() {



    private val listener = object :ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            value = p0.children.map{
                it.getValue(AppNote ::class.java)?: AppNote()
            }
        }

    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()

    }

    override fun onActive() {

        REF_DATABASE.addValueEventListener(listener)
        super.onActive()

    }
}