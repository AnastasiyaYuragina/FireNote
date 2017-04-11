package com.anastasiyayuragina.firenote

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.anastasiyayuragina.firenote.screen.notesList.NoteListFragment

class MainActivity : AppCompatActivity(), NoteListFragment.OnListFragmentInteractionListener {
    lateinit private var fab: FloatingActionButton
    private val ID_NOTE = "id_note"
    private val CHANGE_NOTE = "change_note"

    enum class FragmentType {
        NOTE_LIST
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, AddNewNoteActivity::class.java)
            intent.putExtra(ID_NOTE, 0)
            intent.putExtra(CHANGE_NOTE, true)
            startActivity(intent)
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

        if (fragment == null) {
            fragment = NoteListFragment.newInstance(1)
        }

        if (type == FragmentType.NOTE_LIST) {
            fragmentManager.beginTransaction().replace(R.id.container, fragment,
                    FragmentType.NOTE_LIST.name).commit()
        }
    }

    override fun onListFragmentInteraction(item: Note) {

        val intent = Intent(this, AddNewNoteActivity::class.java)
        intent.putExtra(ID_NOTE, item.id)
        intent.putExtra(CHANGE_NOTE, false)
        startActivity(intent)
    }
}
