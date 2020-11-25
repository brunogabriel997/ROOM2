

package ipvc.estg.room2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.room2.adapters.SchoolAdapter
import ipvc.estg.room2.entities.Escola
import ipvc.estg.room2.viewModel.SchoolViewModel

class MainActivity : AppCompatActivity() , SchoolAdapter.ItemClicked {


    private lateinit var schoolViewModel: SchoolViewModel
    private val newWordActivityRequestCode = 1
    private val editWordActivityRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = SchoolAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // view model
        schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        schoolViewModel.allSchools.observe(this, Observer { schools ->
            // Update the cached copy of the words in the adapter.
            schools?.let { adapter.setSchools(it) }
        })

        //Fab
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddSchool::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val pschool = data?.getStringExtra(AddSchool.EXTRA_REPLY_SCHOOL)
            val pdistrit = data?.getStringExtra(AddSchool.EXTRA_REPLY_DISTRIT)


            if (pschool!= null && pdistrit != null) {
                val school = Escola(school = pschool, distrit = pdistrit)
                schoolViewModel.insert(school)
            }

        }else if (requestCode == editWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val delete = data?.getStringExtra(EditSchool.EXTRA_REPLY_DELETE)
            val pschool = data?.getStringExtra(AddSchool.EXTRA_REPLY_SCHOOL)
            val pdistrit = data?.getStringExtra(AddSchool.EXTRA_REPLY_DISTRIT)
            val pid = data?.getIntExtra(AddSchool.EXTRA_REPLY_ID, -10)

            if (delete == "delete") {
                val pid = data?.getIntExtra(AddSchool.EXTRA_REPLY_ID, -10)
                if (pid != -10 && pschool != null && pdistrit != null) {
                    val school = Escola(id = pid, school = pschool, distrit = pdistrit)
                    schoolViewModel.deleteId(school)
                }
            } else {
                if (pid != -10 && pschool != null && pdistrit != null) {
                    val school = Escola(id = pid, school = pschool, distrit = pdistrit)
                    Toast.makeText(this, pid.toString(), Toast.LENGTH_SHORT).show()
                    schoolViewModel.updateSchool(school)
                }
            }
        }
        else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.apagartudo -> {
                schoolViewModel.deleteAll()
                true
            }

            R.id.escolasViana -> {

                // recycler view
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = SchoolAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // view model
                schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
                schoolViewModel.getSchoolsByDistrit("Viana do Castelo").observe(this, Observer { schools ->
                    // Update the cached copy of the words in the adapter.
                    schools?.let { adapter.setSchools(it) }
                })

                true
            }

            R.id.todasEscolas -> {

                // recycler view
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
                val adapter = SchoolAdapter(this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // view model
                schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
                schoolViewModel.allSchools.observe(this, Observer { schools ->
                    // Update the cached copy of the words in the adapter.
                    schools?.let { adapter.setSchools(it) }
                })


                true
            }

            R.id.getDistritFromFEUP -> {
                schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
                schoolViewModel.getDistritFromSchools("FEUP").observe(this, Observer { school ->
                    Toast.makeText(this, school.distrit, Toast.LENGTH_SHORT).show()
                })
                true
            }

            R.id.apagarFEUP -> {
                schoolViewModel.deleteBySchool("FEUP")
                true
            }

            R.id.alterar -> {
                val school = Escola(id = 1, school = "xxx", distrit = "xxx")
                schoolViewModel.updateSchool(school)
                true
            }

            R.id.alterarFEUP -> {
                schoolViewModel.updateDistritFromSchool("FEUP", "Porto")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onClickListener(school: Escola) {
        Toast.makeText(applicationContext, "Editar Escola", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, EditSchool::class.java)
        intent.putExtra("Id", school.id)
        intent.putExtra("Escola", school.school)
        intent.putExtra("Distrito", school.distrit)
        startActivityForResult(intent, editWordActivityRequestCode)
        //setContentView(R.layout.activity_add_school)
    }

}