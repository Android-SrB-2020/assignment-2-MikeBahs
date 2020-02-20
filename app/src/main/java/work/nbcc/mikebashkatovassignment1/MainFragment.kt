package work.nbcc.mikebashkatovassignment1


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import work.nbcc.mikebashkatovassignment1.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment  : Fragment()  {

    private lateinit var binding:FragmentMainBinding
    private lateinit var navController: NavController

    private var questionIndex = 0;

    private lateinit var questionView: TextView

    private var answer : Boolean = false;

    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController)

                || return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()


        binding.apply {
            binding.questionView.setText(questionBank[questionIndex].description)

            binding.backward.setOnClickListener{
                this@MainFragment.moveBackward();
            }
            binding.forward.setOnClickListener{
                this@MainFragment.moveForward();
            }
            binding.trueButton.setOnClickListener{
                answer = true;
                this@MainFragment.checkAnswer();
            }
            binding.falseButton.setOnClickListener{
                this@MainFragment.answer = false;
                this@MainFragment.checkAnswer();
            }

            binding.cheatBtn.setOnClickListener{
                val bundle = bundleOf(
                    "question" to questionBank[questionIndex].description,
                    "answer" to questionBank[questionIndex].answer.toString())
                navController.navigate(R.id.action_main2_to_cheatFragment2, bundle)
            }
        }
    }

    private fun moveForward(){
        questionIndex = (questionIndex + 1) % 20
        questionView.setText(questionBank[questionIndex].description)
    }

    private fun moveBackward(){
        questionIndex = (questionIndex - 1)
        if(questionIndex == 0){
            questionIndex = questionBank.size - 1;
        }
        questionView.setText(questionBank[questionIndex].description)
    }

    private fun checkAnswer(){
        if(questionBank[questionIndex].answer == answer){
            Toast.makeText(getActivity(),
                "You are totally right !",
                Toast.LENGTH_SHORT)
                .show();
        }
        else{
            Toast.makeText(getActivity(),
                "Wrong answer!",
                Toast.LENGTH_SHORT)
                .show();
        }
    }
}
