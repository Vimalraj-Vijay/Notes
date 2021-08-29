package com.vimalvijay.mynotes.views.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.databinding.FragmentUpdateNotesBinding
import com.vimalvijay.mynotes.utils.Constants.ACT_ADD
import com.vimalvijay.mynotes.utils.Constants.ACT_UPDATE
import com.vimalvijay.mynotes.utils.Constants.KEY_ACTION_TYPE
import com.vimalvijay.mynotes.utils.Constants.KEY_NOTE_MODEL
import com.vimalvijay.mynotes.utils.Constants.commonDateFormat
import com.vimalvijay.mynotes.utils.showSmallToast
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel
import com.vimalvijay.mynotes.views.ui.MainActivity.Companion.getMainInstance
import com.vimalvijay.mynotes.views.viewmodel.NotesViewModel
import java.util.*

class UpdateNotesFragment : Fragment() {
    lateinit var updateNoteUi: FragmentUpdateNotesBinding
    lateinit var notesViewModel: NotesViewModel
    private var actionType = ""
    private lateinit var editNotesModel: NotesModel
    private var headerTitle = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        updateNoteUi = FragmentUpdateNotesBinding.inflate(inflater, container, false)
        return updateNoteUi.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        arguments?.let {
            actionType = it.getString(KEY_ACTION_TYPE).toString()
            if (actionType == ACT_UPDATE) {
                headerTitle = resources.getString(R.string.update_notes)
                editNotesModel = it.getSerializable(KEY_NOTE_MODEL) as NotesModel
                updateNoteUi.edtTitle.setText(editNotesModel.title)
                updateNoteUi.edtDescription.setText(editNotesModel.description)
                updateNoteUi.cbPrority.isChecked = editNotesModel.priority
            } else if (actionType == ACT_ADD) {
                headerTitle = resources.getString(R.string.add_notes)
            }
        }
        getMainInstance().showHeader(headerTitle, true)
        updateNoteUi.btnAddOrUpdate.setOnClickListener {
            if (updateNoteUi.edtTitle.text.isNotEmpty()) {
                val notesModel = NotesModel(
                    updateNoteUi.edtTitle.text.toString(),
                    updateNoteUi.edtDescription.text.toString(),
                    updateNoteUi.cbPrority.isChecked,
                    getCurrentDateAndTime()
                )
                if (actionType == ACT_ADD) {
                    notesViewModel.insert(notesModel)
                } else if (actionType == ACT_UPDATE) {
                    notesModel.id = editNotesModel.id
                    notesViewModel.update(notesModel)
                }
                getMainInstance().onBackPressed()
            } else {
                requireContext().showSmallToast(resources.getString(R.string.req_title))
            }
        }
    }

    private fun getCurrentDateAndTime(): Date? {
        return try {
            commonDateFormat.parse(commonDateFormat.format(Calendar.getInstance().time))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}