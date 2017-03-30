package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NoteListFragment : Fragment() {
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    private var listNote : ArrayList<Note> = ArrayList()
    private lateinit var rVadapter : ListNoteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            columnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.content_main, container, false)
        val context = view.context

        if (context is OnListFragmentInteractionListener) {
            listener = context as OnListFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }

        // Set the adapter
        if (view is RecyclerView) {
            val recyclerView = view
            if (columnCount <= 1) {
                recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, columnCount)
            }
            rVadapter = ListNoteRecyclerViewAdapter(listener)
            recyclerView.adapter = rVadapter
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        fillingListNote()
    }

    override fun onStop() {
        super.onStop()
        listener = null
    }

    fun addListNote(list: ArrayList<Note>) {
        rVadapter.addListNote(list)
    }

    fun fillingListNote() {
        listNote.add(Note(Math.random().toInt(), "note 1 \n this is long text", System.currentTimeMillis()))
        listNote.add(Note(Math.random().toInt(), "note 2", System.currentTimeMillis() + 182745))
        addListNote(listNote)
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Note)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): NoteListFragment {
            val fragment = NoteListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
