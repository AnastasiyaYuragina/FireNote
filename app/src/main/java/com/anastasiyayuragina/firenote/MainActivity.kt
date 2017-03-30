package com.anastasiyayuragina.firenote

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), NoteListFragment.OnListFragmentInteractionListener {
    lateinit private var fab: FloatingActionButton

    enum class FragmentType {
        NOTE_LIST,
        ADD_CHANGE_NOTE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            readChangeNote(true)
        }

        if (savedInstanceState == null) {
            replaceFragment(FragmentType.NOTE_LIST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(type: FragmentType) {
        var fragment = fragmentManager.findFragmentByTag(type.name)

        if (fragment == null)
        {
            fragment = NoteListFragment.newInstance(1)
        }

        if (type == FragmentType.NOTE_LIST) {
            fragmentManager.beginTransaction().replace(R.id.container, fragment,
                    NoteListFragment.toString()).commit()
        }
    }

    fun readChangeNote(noteText: String, readTextStatus: Boolean) {
        var fragment = fragmentManager.findFragmentByTag(FragmentType.ADD_CHANGE_NOTE.name)

        if (fragment == null)
        {
            fragment = AddChangeNoteFragment.newInstance(noteText, readTextStatus)
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment,
                AddChangeNoteFragment.toString()).addToBackStack(null).commit()
    }

    fun readChangeNote(readTextStatus: Boolean) {
        var fragment = fragmentManager.findFragmentByTag(FragmentType.ADD_CHANGE_NOTE.name)

        if (fragment == null)
        {
            fragment = AddChangeNoteFragment.newInstance(readTextStatus)
        }

        fragmentManager.beginTransaction().replace(R.id.container, fragment,
                AddChangeNoteFragment.toString()).addToBackStack(null).commit()
    }

    override fun onListFragmentInteraction(item: Note) {
        readChangeNote(item.getNoteText(), false)
        fab.setImageResource(R.mipmap.ic_mode_edit)
        fab.setOnClickListener {
            readChangeNote(item.getNoteText(),true)
        }
    }
}
