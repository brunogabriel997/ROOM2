package ipvc.estg.room2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ipvc.estg.room2.api.*
import kotlinx.android.synthetic.main.add_acidente.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAcidente : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_acidente)

        // Obtem valores GPS e o nome do utilizador ////////////////////////////////////////////////
        val extras = intent.extras

        val lat = extras?.getString("lat")
        val lng = extras?.getString("lng")
        val id = extras?.getString("id")

        Toast.makeText(this@AddAcidente, lat +" | "+ lng+" | "+ id, Toast.LENGTH_SHORT).show()


        ////////////////////////////////////////////////////////////////////////////////////////////



        val spinner: Spinner = findViewById(R.id.tipo)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.tipo,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this






        /*
        // call the service and add markers
        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getIdByUser("admin")

        // Conseguir ID do User ////////////////////////////////////////////////////////////////////

        call.enqueue(object : Callback<List<iduser>>{
            override fun onResponse(call: Call<List<iduser>>, response: Response<List<iduser>>) {
                if (response.isSuccessful){
                    iduser = response.body()!!
                    for (id in iduser) {
                        //Toast.makeText(this@AddAcidente, id.id+ " | "+lng, Toast.LENGTH_SHORT).show()

                    }


                }
            }

            override fun onFailure(call: Call<List<iduser>>, t: Throwable) {
                //Toast.makeText(this@AddAcidente, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        */
        ////////////////////////////////////////////////////////////////////////////////////////////


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val descricao = descricao.text.toString()

            val request = ServiceBuilder.buildService(EndPoints::class.java)


            if (descricao.isEmpty()) {
                Toast.makeText(this@AddAcidente, "Preencher descrição", Toast.LENGTH_SHORT).show()
            }
            else {
                val call = request.add_acidente(descricao = descricao, lat = lat?.toDouble(), lng = lng?.toDouble(), id = id?.toInt(), tipo = 1)
                call.enqueue(object : Callback<OutputPost>{
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                        if (response.isSuccessful) {
                            if (response.body()?.error == false) {
                                Toast.makeText(
                                        this@AddAcidente,
                                        "Ocorreu um erro. Não foi possivel introduzir o acidente",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                            else {
                                Toast.makeText(
                                        this@AddAcidente,
                                        "Acidente introduzido com exito.",
                                        Toast.LENGTH_SHORT
                                ).show()
                                //val intent = Intent(this@AddAcidente, MapsActivity::class.java)
                                finish()
                                //startActivity(intent)

                            }
                        }
                    }
                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                        Toast.makeText(this@AddAcidente, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            //Toast.makeText(this@AddAcidente, descricao+ " | "+lng, Toast.LENGTH_SHORT).show()

        }


    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }



/*
    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        Toast.makeText(this@AddAcidente, "Selecionar tipo", Toast.LENGTH_SHORT).show()
    }
*/
    companion object {
        const val EXTRA_REPLY_ID = "com.example.android.id"
        const val EXTRA_REPLY_SCHOOL = "com.example.android.school"
        const val EXTRA_REPLY_DISTRIT = "com.example.android.distrit"
    }




}

