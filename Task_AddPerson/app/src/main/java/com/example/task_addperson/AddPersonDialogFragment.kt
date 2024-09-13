package com.example.task_addperson

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddPersonDialogFragment : DialogFragment() {

    private var onPersonAddedListener: ((Person) -> Unit)? = null

    fun setOnPersonAddedListener(listener: (Person) -> Unit) {
        onPersonAddedListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_person, container, false)

        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val mailEditText = view.findViewById<EditText>(R.id.editTextMail)
        val phoneEditText = view.findViewById<EditText>(R.id.editTextPhone)
        val addButton = view.findViewById<Button>(R.id.btnAddPerson)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val mail = mailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (name.isNotEmpty() && mail.isNotEmpty() && phone.isNotEmpty()) {
                val newPerson = Person(name, mail, phone)
                onPersonAddedListener?.invoke(newPerson)
                dismiss()
            }
        }

        return view
    }
}
