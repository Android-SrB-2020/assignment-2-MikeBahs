package work.nbcc.mikebashkatovassignment1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_cheat.*
import work.nbcc.mikebashkatovassignment1.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class CheatFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    lateinit var question:String
    lateinit var answer: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cheat,
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        question = arguments!!.getString("question") ?: "None"
        answer = arguments!!.getString("answer") ?: "None"

        navController = view.findNavController()

        binding.message = question // NO IDEA WHY IT DOESNT SEE MY VARIABLE

        binding.apply {
            cancelBtn.setOnClickListener {
                activity!!.onBackPressed()
            }
            chectBtn.setOnClickListener {
                binding.answerQuestion = answer // NO IDEA WHY IT DOESNT SEE MY VARIABLE
            }
        }

    }


}
