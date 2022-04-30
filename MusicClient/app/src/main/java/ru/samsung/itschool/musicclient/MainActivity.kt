package ru.samsung.itschool.musicclient

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // здесь нужно указать URL проекта на Heroku
    val HEROKU_URL: String = "https://kyserv.herokuapp.com/"

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

        read.setOnClickListener {
            service.read().enqueue(callback { list ->
                result.text = list.joinToString { it.toString() }
            })
        }

        readEntry.setOnClickListener {
            service.readEntry(id.text.toString().toIntOrNull() ?: 0).enqueue(callback {
                result.text = it.toString()
            })
        }

        create.setOnClickListener {
            service.create(MusicEntry(0, name.text.toString(), phone.text.toString()))
                .enqueue(callback {
                    result.text = it.toString()
                })
        }

        delete.setOnClickListener {
            service.delete(id.text.toString().toIntOrNull() ?: 0).enqueue(callback {
                result.text = it.toString()
            })
        }

        update.setOnClickListener {
            val entry = MusicEntry(
                id.text.toString().toIntOrNull() ?: 0,
                name.text.toString(),
                phone.text.toString()
            )
            service.update(entry).enqueue(callback {
                result.text = it.toString()
            })
        }

    }

    private fun <T> callback(onSuccess: (response: T) -> Unit): Callback<T> {
        return object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {}
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let { onSuccess(it) }
            }
        }
    }
}
