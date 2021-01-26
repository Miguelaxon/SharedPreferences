package com.example.sharedpreferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sharedpreferences.databinding.ActivityMainBinding
import com.example.sharedpreferences.databinding.FragmentFirstBinding
import java.util.prefs.Preferences

private const val mFileSharedPreferences = "com.example.sharedpreferences"

class FirstFragment : Fragment() {
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        mSharedPreferences = requireActivity().getSharedPreferences(mFileSharedPreferences
            , Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGuardar.setOnClickListener {
            save()
        }

        binding.buttonBorrar.setOnClickListener {
            clear()
        }
    }

    fun save(){
        if (binding.editNumber.text.length == 0){
            Toast.makeText(activity, "Debe ingresar un número entero", Toast.LENGTH_LONG).show()
        } else {
            var numEntero: Int = binding.editNumber.text.toString().toInt()
            mSharedPreferences.edit().putInt("numeroEntero", numEntero).apply()
            var numEntero2 = mSharedPreferences.getInt("numeroEntero", -1).toString()
            binding.textViewNumber.setText(getString(R.string.valorEntero, numEntero2.toInt()))
        }

        if (binding.editText.text.length == 0){
            Toast.makeText(activity, "Debe ingresar un texto", Toast.LENGTH_LONG).show()
        } else {
            var texto: String = binding.editText.text.toString()
            mSharedPreferences.edit().putString("texto", texto).apply()
            var texto2 = mSharedPreferences.getString("texto", " ").toString()
            binding.textViewText.setText(getString(R.string.texto_almacenado, texto2))
        }

        if (binding.editNumberDecimal.text.length == 0){
            Toast.makeText(activity, "Debe ingresar un número decimal", Toast.LENGTH_LONG).show()
        } else {
            var numDecimal: Float = binding.editNumberDecimal.text.toString().toFloat()
            mSharedPreferences.edit().putFloat("numeroDecimal", numDecimal).apply()
            var numDecimal2 = mSharedPreferences.getFloat("numeroDecimal", 1.0f).toString()
            binding.textViewNumberDecimal.setText(getString(R.string.valorDecimal, numDecimal2))
        }
    }

    fun clear(){
        mSharedPreferences.edit().remove("numeroEntero").apply()
        mSharedPreferences.edit().remove("texto").apply()
        mSharedPreferences.edit().remove("numeroDecimal").apply()

        binding.editNumber.text.clear()
        binding.textViewNumber.setText("")

        binding.editText.text.clear()
        binding.textViewText.setText("")

        binding.editNumberDecimal.text.clear()
        binding.textViewNumberDecimal.setText("")
    }

}