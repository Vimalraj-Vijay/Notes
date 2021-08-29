package com.vimalvijay.mynotes.views.ui

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vimalvijay.mynotes.R
import com.vimalvijay.mynotes.databinding.ViewNotesBinding
import com.vimalvijay.mynotes.utils.Constants.ACT_DELETE
import com.vimalvijay.mynotes.utils.Constants.ACT_UPDATE
import com.vimalvijay.mynotes.utils.DateConvertor.dateToText
import com.vimalvijay.mynotes.utils.gone
import com.vimalvijay.mynotes.utils.visible
import com.vimalvijay.mynotes.views.dbhelpers.model.NotesModel

class NotesAdapter(
    private val context: Context,
    private var noteList: List<NotesModel>,
    private val onNotesClick: OnNotesClick
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val notesAdpterUi: ViewNotesBinding) :
        RecyclerView.ViewHolder(notesAdpterUi.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val binding = ViewNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        holder.notesAdpterUi.tvNotesTitle.text = noteList[position].title
        holder.notesAdpterUi.tvNotesDescription.text = noteList[position].description
        if (noteList[position].updateTime == null) {
            holder.notesAdpterUi.tvNotesTime.gone()
        } else {
            holder.notesAdpterUi.tvNotesTime.visible()
            holder.notesAdpterUi.tvNotesTime.text = dateToText(noteList[position].updateTime)
        }
        if (noteList[position].priority) {
            holder.notesAdpterUi.ivPriorityMarker.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.color_primary_dark
                ), PorterDuff.Mode.SRC_IN
            )
        } else {
            holder.notesAdpterUi.ivPriorityMarker.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.app_line
                ), PorterDuff.Mode.SRC_IN
            )
        }
        holder.notesAdpterUi.ivDeleteNotes.setOnClickListener {
            onNotesClick.onNotesClicked(ACT_DELETE, noteList[position])
        }
        holder.itemView.setOnClickListener {
            onNotesClick.onNotesClicked(ACT_UPDATE, noteList[position])
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}