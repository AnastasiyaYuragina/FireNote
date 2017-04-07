package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NoteListFragment : Fragment(), NotesMvp.View {
    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var rvAdapter : ListNoteRecyclerViewAdapter
    private lateinit var presenter : NotesMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            columnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }

        presenter = NotesPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.content_main, container, false)
        val context = view.context

        presenter.loadData()

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
            rvAdapter = ListNoteRecyclerViewAdapter(listener)
            recyclerView.adapter = rvAdapter
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    override fun onStop() {
        super.onStop()
        listener = null
    }

    override fun addListNote(list: ArrayList<Note>) {
        rvAdapter.addListNote(list)
    }

    override fun setData(note: Note) {
        rvAdapter.addNoteToList(note)
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
