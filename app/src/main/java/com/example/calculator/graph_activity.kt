package com.example.calculator

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.collectedpoints
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_graph_activity.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.ArrayList

class graph_activity : AppCompatActivity() {
//    var x:Double = 0.0
    lateinit var myhelper:myHelper
    lateinit var sqLiteDatabase: SQLiteDatabase
    lateinit var rateinput:View
    lateinit var datainput:View
    lateinit var series: LineGraphSeries<DataPoint>
    lateinit var graph:GraphView
//    lateinit var creating: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_activity)
        myhelper = myHelper(this);
        sqLiteDatabase = myhelper.getWritableDatabase();

        execGraph()
    }
    private fun execGraph() {
        var bt:Button  = findViewById(R.id.Show)
        bt.setOnClickListener {
            rateinput = findViewById(R.id.rateofgraph)
            val rt: Double = rateinput.toString().toDouble()
            datainput = findViewById(R.id.pointsongraph)
            val points: Int = datainput.toString().toInt()
            var x:Double = 0.0
            for (i in 0..points) {
                val y = Math.sin(x) + Math.cos(x)
                myhelper.insert(x, y)
                x += rt
            }
            series = LineGraphSeries<DataPoint>(getData())
            graph.addSeries(series)
        }
    }

    private fun getData(): Array<DataPoint>? {
        var st = arrayOf<String>("xvalues","yvalues")
        var crsr: Cursor = sqLiteDatabase.query("Mytable",st,null,null,null,null,null)
        var d:DataPoint = DataPoint(0.0,0.0)
        var dp=Array<DataPoint>(crsr.getCount()){d}
        for(i in 0..crsr.getCount()){
            dp[i] = DataPoint(crsr.getDouble(0).toDouble(),crsr.getInt(1).toDouble())
            crsr.moveToNext()
        }
        return dp
    }
}