package com.example.tbc_course_27.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tbc_course_27.R
import com.example.tbc_course_27.databinding.FragmentMainBinding
import com.example.tbc_course_27.domain.model.NumberObj
import com.example.tbc_course_27.domain.model.Numbers
import com.example.tbc_course_27.ui.adapter.NumberAdapter
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        NumberAdapter(NumberObj.numbers)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterInit()
        listeners()

    }

    private fun textListener(){
        binding.recycler.adapter
    }

    private fun adapterInit(){
        binding.recycler.adapter = adapter
    }

    private fun setText(it:Numbers){
        if(binding.editText1.text?.isEmpty() == true){
            binding.editText1.setText("${it.number}")
        }
        else if (binding.editText2.text?.isEmpty() == true){
            binding.editText2.setText("${it.number}")
        }else if (binding.editText3.text?.isEmpty() == true){
            binding.editText3.setText("${it.number}")
        }else{
            binding.editText4.setText("${it.number}")
        }
    }
    private fun removeText(it:Numbers){
        if(binding.editText1.text?.isNotEmpty() == true){
            binding.editText1.setText("")
        }
        else if (binding.editText2.text?.isNotEmpty() == true){
            binding.editText2.setText("")
        }else if (binding.editText3.text?.isNotEmpty() == true){
            binding.editText3.setText("")
        }else{
            binding.editText4.setText("")
        }
    }

    private fun listeners(){
        adapter.onClick = {
            setText(it)
            validator()

        }
        adapter.onRemoveClick = {
            removeText(it)
        }
    }

    private fun clear(){
        val first = binding.editText1.text
        val second = binding.editText2.text
        val third = binding.editText3.text
        val fourth = binding.editText4.text

        first?.clear()
        second?.clear()
        third?.clear()
        fourth?.clear()
    }

    private fun validator() {
        val first = binding.editText1.text
        val second = binding.editText2.text
        val third = binding.editText3.text
        val fourth = binding.editText4.text

        if (first.toString() == "0" && second.toString() == "9" && third.toString() == "3" && fourth.toString() == "4"){
            Snackbar.make(requireView(),getString(R.string.success),Snackbar.LENGTH_SHORT).show()
            clear()

        }
        else if (first.toString().isNotEmpty() && second.toString().isNotEmpty() && third.toString().isNotEmpty() && fourth.toString().isNotEmpty()){
            Snackbar.make(requireView(),getString(R.string.wrong),Snackbar.LENGTH_SHORT).show()
            clear()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}