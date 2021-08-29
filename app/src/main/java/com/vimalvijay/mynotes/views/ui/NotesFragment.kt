package com.vimalvijay.mynotes.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.databinding.FragmentNotesBinding
import com.vimalvijay.mynotes.utils.Constants
import com.vimalvijay.mynotes.utils.Constants.ACT_DELETE
import com.vimalvijay.mynotes.utils.Constants.ACT_UPDATE
import com.vimalvijay.mynotes.utils.Constants.KEY_ACTION_TYPE
import com.vimalvijay.mynotes.utils.Constants.KEY_NOTE_MODEL
import com.vimalvijay.mynotes.utils.gone
import com.vimalvijay.mynotes.utils.visible
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel
import com.vimalvijay.mynotes.views.ui.MainActivity.Companion.getMainInstance
import com.vimalvijay.mynotes.views.viewmodel.NotesViewModel
import java.io.Serializable

class NotesFragment : Fragment(), OnNotesClick {
    lateinit var binding: FragmentNotesBinding
    lateinit var notesViewModel: NotesViewModel
    private var notesAdapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAllNotes.setHasFixedSize(false)
        binding.rvAllNotes.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        notes()
        binding.fabAdd.setOnClickListener {
            addNewNotes()
        }
        binding.vEmptyNotes.btnAdd.setOnClickListener {
            addNewNotes()
        }
    }


    private fun addNewNotes() {
        val bundle = Bundle()
        bundle.putString(KEY_ACTION_TYPE, Constants.ACT_ADD)
        getMainInstance().onAddNewNotesClick(bundle)
    }

    private fun notes() {
        notesViewModel.getAllNotes().observe(requireActivity(), Observer { notes ->
            if (notes.isEmpty()) {
                binding.rvAllNotes.gone()
                binding.vEmptyNotes.root.visible()
                getMainInstance().hideHeader()
                binding.fabAdd.gone()
            } else {
                setNotes(notes)
                binding.rvAllNotes.visible()
                binding.vEmptyNotes.root.gone()
                getMainInstance().showHeader(resources.getString(R.string.notes), false)
                binding.fabAdd.visible()
            }
        })
    }

    private fun setNotes(notes: List<NotesModel>) {
        /*if (notesAdapter == null) {
            notesAdapter = NotesAdapter(requireActivity(), notes, this)
            binding.rvAllNotes.adapter = notesAdapter
        } else {
            notesAdapter?.updateNotes(notes)
        }*/

        notesAdapter = NotesAdapter(requireActivity(), notes, this)
        binding.rvAllNotes.adapter = notesAdapter
    }

    override fun onNotesClicked(actionType: String, notesModel: NotesModel) {
        if (actionType == ACT_DELETE) {
            notesViewModel.delete(notesModel)
        } else if (actionType == ACT_UPDATE) {
            val noteBundle = Bundle()
            noteBundle.putSerializable(KEY_NOTE_MODEL, notesModel as Serializable)
            noteBundle.putString(KEY_ACTION_TYPE, actionType)
            getMainInstance().onEditNotesClick(noteBundle)
        }
    }
}