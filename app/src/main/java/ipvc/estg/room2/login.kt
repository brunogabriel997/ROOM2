package ipvc.estg.room2

import kotlinx.android.synthetic.main.login.*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import ipvc.estg.room2.api.EndPoints
import ipvc.estg.room2.api.OutputPost
import ipvc.estg.room2.api.ServiceBuilder
import ipvc.estg.room2.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.login.*


class login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)



        val button = findViewById<Button>(R.id.activity_main_loginButton)
        button.setOnClickListener {
            val nome_user = nome_user.text.toString().trim()
            val pass = pass.text.toString().trim()

            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val call = request.login(nome_user, pass)

            if(nome_user.isEmpty() || pass.isEmpty())
            {
                Toast.makeText(this@login, "Introduza o username e a password", Toast.LENGTH_SHORT).show()

            }
            else {

                call.enqueue(object : Callback<OutputPost> {
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {

                        if (response.isSuccessful) {   //Se a resposta for positiva na camada de serviços significa que o utilizador existe
                            if (response.body()?.error == false) {
                                Toast.makeText(
                                        this@login,
                                        "Username ou palavra passe incorreta",
                                        Toast.LENGTH_SHORT
                                ).show()
                            } else {          // Se der tudo certo

                                val check1: CheckBox = findViewById(R.id.checkbox)
                                if (check1.isChecked){

                                    //Toast.makeText(this@Login, "Toogle On", Toast.LENGTH_SHORT).show()

                                    var token = getSharedPreferences("utilizador", Context.MODE_PRIVATE)
                                    intent.putExtra("utilizador", nome_user)
                                    var editor = token.edit()
                                    editor.putString("loginutilizador", nome_user)
                                    editor.commit()
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }


                                Toast.makeText(this@login, "Bem vindo " + nome_user, Toast.LENGTH_LONG).show()

                                val intent = Intent(this@login, MapsActivity::class.java)       // Abrir a main do maps
                                startActivity(intent)

                            }
                        }
                    }
                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                        Toast.makeText(this@login, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }



    override fun onStart() {
        super.onStart()
        var token = getSharedPreferences("utilizador", Context.MODE_PRIVATE)
        if (token.getString("loginutilizador", " ") != " ") {
            val intent = Intent(applicationContext, MapsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


    }


}