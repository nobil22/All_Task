package com.example.task_addperson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    private lateinit var personAdapter: PersonAdapter
    private val persons = mutableListOf<Person>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_persons, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fabAddPerson = view.findViewById<FloatingActionButton>(R.id.fabAddPerson)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        personAdapter = PersonAdapter(persons) { position ->
            removePerson(position)
        }
        recyclerView.adapter = personAdapter

        // Add person when fab clicked
        fabAddPerson.setOnClickListener {
            showAddPersonDialog()
        }

        return view
    }

    private fun removePerson(position: Int) {
        persons.removeAt(position)
        personAdapter.notifyItemRemoved(position)
    }

    private fun showAddPersonDialog() {
        // Example dialog to input new person details
        val addPersonDialog = AddPersonDialogFragment()
        addPersonDialog.setOnPersonAddedListener { person ->
            persons.add(person)
            personAdapter.notifyItemInserted(persons.size - 1)
        }
        addPersonDialog.show(parentFragmentManager, "AddPersonDialog")
    }
}
