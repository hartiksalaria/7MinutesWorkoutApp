package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao = (application as WorkoutApp).db.historyDao()
        addDataToDatabase(dao)

    }

    private fun addDataToDatabase(historyDao: HistoryDao) {

        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:SS", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted date", "" + date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }

    }

}