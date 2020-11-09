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
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function


class graph_activity : AppCompatActivity() {
    lateinit var series: LineGraphSeries<DataPoint>
    lateinit var graph:GraphView
    lateinit var f:Function
    var d:Double = -0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_activity)
//        getInput()
         execGraph()
    }
    override fun onResume(){
        super.onResume()
//        graph.addSeries(series)
    }
    fun execGraph() {
        var bt:Button  = findViewById(R.id.Show)
        graph = findViewById(R.id.g)
        series = LineGraphSeries<DataPoint>()
        var bl:Bundle ?= intent.extras
        var str:String ?= intent.getStringExtra("Function")
        var first:String = "f(x) = "
        var fin:String = first+str
        Toast.makeText(this, fin + "get this", Toast.LENGTH_SHORT).show()
        f = Function(fin)
        bt.setOnClickListener {
            for(i in 0..150){
                var argVal:String = d.toString()
                var argFun:String = "x="
                argFun = argFun+argVal
                var x:Argument = Argument(argFun)
                var e1:Expression = Expression("f(x)",f,x)
                var res:Double = e1.calculate()
                var dp:DataPoint = DataPoint(d,res)
                series.appendData(dp,true,150)
                d+=0.08
            }
            Toast.makeText(this, "Graph Being Created", Toast.LENGTH_SHORT).show()
            graph.addSeries(series)
        }
    }
}