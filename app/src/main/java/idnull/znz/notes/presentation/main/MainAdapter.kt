package idnull.znz.notes.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import idnull.znz.notes.R
import idnull.znz.notes.models.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.NoteItem>() {
    private var mListNotes= emptyList<AppNote>()


    class NoteItem(view: View):RecyclerView.ViewHolder(view){

        val name:TextView=view.item_note_name

    }

    override fun onViewAttachedToWindow(holder: NoteItem) {
        holder.itemView.setOnClickListener{
            MainFragment.click(mListNotes[holder.adapterPosition])

        }
    }

    override fun onViewDetachedFromWindow(holder: NoteItem) {
        holder.itemView.setOnClickListener(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItem {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteItem(view)


    }

    override fun getItemCount(): Int {
       return mListNotes.size
    }

    override fun onBindViewHolder(holder: NoteItem, position: Int) {
        holder.name.text = mListNotes[position].name

    }

    fun setList(list:List<AppNote>){
        mListNotes= list
        notifyDataSetChanged()

    }
}
















