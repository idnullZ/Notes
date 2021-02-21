package idnull.znz.notes.presentation.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import idnull.znz.notes.R
import idnull.znz.notes.databinding.FragmentNoteBinding
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.APP_ACTIVITY


class NoteFragment : Fragment() {



    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote:AppNote



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNoteBinding.inflate(layoutInflater,container,false)
        mCurrentNote = arguments?.getSerializable("note")as AppNote
        return mBinding.root
    }


    override fun onStart() {
        super.onStart()
        initStart()
    }

    private fun initStart() {
        setHasOptionsMenu(true)
        mBinding.noteName.text = mCurrentNote.name
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete->{
                mViewModel.delete(mCurrentNote){
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment2_to_mainFragment2)
                }
            }


            R.id.btn_edit->{
                    val bundle =Bundle()
                    bundle.putSerializable("note", mCurrentNote)

                APP_ACTIVITY.navController.navigate(R.id.action_noteFragment2_to_addNewNoteFragment2,bundle)


            }


        }
       return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

}