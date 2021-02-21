package idnull.znz.notes.presentation.start
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import idnull.znz.notes.R
import idnull.znz.notes.databinding.FragmentStartBinding

import idnull.znz.notes.utils.*
import kotlinx.android.synthetic.main.fragment_start.*



class StartFragment : Fragment() {




    private var _binding:FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel
    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return mBinding.root

    }

    override fun onStart() {

        super.onStart()
        mViewModel= ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        if (AppPreference.getInitUser()){
            mViewModel.initDataBase(AppPreference.getTypeDB()){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment2_to_mainFragment2)

        }
        }
        else{
            initialization()

        }

    }

    private fun initialization() {

        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDataBase(TYPE_ROOM){
                AppPreference.setInitUser(true)
                AppPreference.setTypeDB(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment2_to_mainFragment2)

            }
        }
        mBinding.btnFirebase.setOnClickListener {
            mBinding.inputPassword.visibility=View.VISIBLE
            mBinding.inputEmail.visibility=View.VISIBLE
            mBinding.btnLogin.visibility=View.VISIBLE
            mBinding.btnLogin.setOnClickListener {


                val inputEmail = mBinding.inputEmail.text.toString()
                val inputPassword = mBinding.inputPassword.text.toString()
                if (inputEmail.isNotEmpty()&&inputPassword.isNotEmpty()){


                    EMAIL= inputEmail
                    PASSSWORD= inputPassword
                    mViewModel.initDataBase(TYPE_FIREBASE){
                        AppPreference.setInitUser(true)
                        AppPreference.setTypeDB(TYPE_FIREBASE)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment2_to_mainFragment2)
                    }

                }else{
                    showToast(getString(R.string.error_enter_password_login))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}