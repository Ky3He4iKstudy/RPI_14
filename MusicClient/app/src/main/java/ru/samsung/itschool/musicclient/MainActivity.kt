package ru.samsung.itschool.musicclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // здесь нужно указать URL проекта на Heroku
    val HEROKU_URL: String = "http://192.168.49.102:5000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(HEROKU_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
        val service: MusicController = retrofit.create(MusicController::class.java);
        val id: EditText = findViewById(R.id.id)
        val name: EditText = findViewById(R.id.name)
        val phone: EditText = findViewById(R.id.album)
        val result: TextView = findViewById(R.id.result)
        val create: Button = findViewById(R.id.create)
        val update: Button = findViewById(R.id.update)
        val delete: Button = findViewById(R.id.delete)
        val readEntry: Button = findViewById(R.id.readEntry)
        val read: Button = findViewById(R.id.read)

        read.setOnClickListener({
            TODO("вывести в поле result список всех треков")
        })

        readEntry.setOnClickListener({
            TODO("вывести в поле result запись о треке с идентификатором по полю ввода id")
        })

        create.setOnClickListener({
            TODO("создать запись с треком на сервере и показать возвращенный " +
                    "идентификатор добавленной записи")
        })

        delete.setOnClickListener({
            TODO("удалить на сервере запись с треком по указанному в поле ввода id")
        })

        update.setOnClickListener({
            TODO("изменить запись на сервере с треком по указанному в поле ввода id и с " +
                    "измененными значениями в полях ввода названия трека и альбома")
        })

    }
}