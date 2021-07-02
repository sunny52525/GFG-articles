package com.gfg.gfgkotlinflow.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gfg.gfgkotlinflow.databinding.ActivityMainBinding
import com.gfg.gfgkotlinflow.network.Status
import com.gfg.gfgkotlinflow.viewmodel.CommentsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CommentsViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)


        binding.button.setOnClickListener {
            viewModel.getNewComment(binding.searchEditText.text.toString().toInt())
        }


        lifecycleScope.launch {
            viewModel.commentState.collect {

                if (it.status == Status.LOADING) {
                    binding.progressBar.isVisible = true
                } else if (it.status == Status.SUCCESS) {

                    binding.progressBar.isVisible = false
                    it.data?.let { comment ->

                        binding.commentIdTextview.text = comment.id.toString()
                        binding.nameTextview.text = comment.name
                        binding.emailTextview.text = comment.email
                        binding.commentTextview.text = comment.comment
                    }
                } else {

                    binding.progressBar.isVisible = false
                    Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
                }


            }

        }
    }
}