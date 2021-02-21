package idnull.znz.notes.presentation.add_new_note

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import idnull.znz.notes.R
import idnull.znz.notes.databinding.FragmentAddNewNoteBinding
import idnull.znz.notes.models.AppNote
import idnull.znz.notes.utils.APP_ACTIVITY
import idnull.znz.notes.utils.showToast


class AddNewNoteFragment : Fragment() {
    private var _binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteViewModel

    private var mCurrentNote: AppNote? = null
    private var EDbool = false
    var iid = 0
    var fid = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)

        if (arguments != null) {
            mCurrentNote = arguments?.getSerializable("note") as AppNote
            EDbool = true
            mBinding.inputNoteName.setText(mCurrentNote!!.name)
            iid = mCurrentNote!!.id
            fid = mCurrentNote!!.idFirebase
        }
        return mBinding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_menu -> {
                add()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun add() {
        val name = mBinding.inputNoteName.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.toast_note_name))
        } else {


            val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

            if (EDbool) {
                mViewModel.edit(
                    AppNote(
                        id = iid, name = name,
                        idFirebase = fid
                    )
                ) {
                }
            } else {
                mViewModel.insert(
                    AppNote(
                        name = name
                    )) {
                } }
            APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment2_to_mainFragment2)
        }
    }
}



