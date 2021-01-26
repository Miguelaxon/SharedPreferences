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

    }

    fun save(){
        //binding.editNumber.setText(mSharedPreferences.getInt("numero_entero", 0).toString())
        var numEntero: Int = binding.editNumber.text.toString().toInt()
        mSharedPreferences.edit().putInt("numero_entero", numEntero).apply()
        var numEntero2 = mSharedPreferences.getInt("numero_entero", -1).toString()
        binding.textViewNumber.setText(getString(R.string.valorEntero, numEntero2.toInt()))

        //binding.editText.setText(mSharedPreferences.getString("texto", " "))
        var texto: String = binding.editText.text.toString()
        mSharedPreferences.edit().putString("texto", texto).apply()
        var texto2 = mSharedPreferences.getString("texto", " ").toString()
        binding.textViewText.setText(getString(R.string.texto_almacenado, texto2))

        //binding.editNumberDecimal.setText(mSharedPreferences.getFloat("numero_decimal", -1f).toString())
        var numDecimal: Float = binding.editNumberDecimal.text.toString().toFloat()
        mSharedPreferences.edit().putFloat("numero_decimal", numDecimal).apply()
        var numDecimal2 = mSharedPreferences.getFloat("numero_decimal", -1f).toString()
        binding.textViewNumberDecimal.setText(getString(R.string.valorDecimal, numDecimal2.toFloat()))
        Toast.makeText(activity, "$numDecimal2", Toast.LENGTH_LONG).show()
    }

}