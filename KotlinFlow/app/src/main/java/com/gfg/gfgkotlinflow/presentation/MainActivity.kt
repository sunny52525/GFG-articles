package com.gfg.gfgkotlinflow.presentation


import com.gfg.gfgkotlinflow.databinding.ActivityMainBinding
import com.gfg.gfgkotlinflow.network.Status
import com.gfg.gfgkotlinflow.viewmodel.CommentsViewModel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //create a CommentsViewModel  variable to initialize it later
    private lateinit var viewModel: CommentsViewModel

    //create a view binding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //instantiate view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize viewModel
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)


        //Listen for the button click event to search
        binding.button.setOnClickListener {

            //check to prevent api call with no parameters
            if (binding.searchEditText.text.isNullOrEmpty()) {
                Toast.makeText(this, "Query Can't be empty", Toast.LENGTH_SHORT).show()
            } else {

                //if Query isn't empty, make the api call
                viewModel.getNewComment(binding.searchEditText.text.toString().toInt())
            }
        }


        //Since flow run asynchronously, start listening on background thread
        lifecycleScope.launch {

            /**
             * Collect the data emitted in CommentApiState helper class,
             * It may contain error,success or loading state.
             * Update the UI according to the State received
             */
            viewModel.commentState.collect {

                //When state to check the state of received data
                when (it.status) {

                    //If its loading state then show the progress bar
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    //If api call was a success , Update the Ui with data and make progress bar invisible
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { comment ->

                            binding.commentIdTextview.text = comment.id.toString()
                            binding.nameTextview.text = comment.name
                            binding.emailTextview.text = comment.email
                            binding.commentTextview.text = comment.comment
                        }
                    }
                    //In case of error, show some data to user
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }
    }
}