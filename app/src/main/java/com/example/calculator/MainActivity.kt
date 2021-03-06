package com.example.calculator

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    var number =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun Graphevent(view :View) {
        var bt: Button = findViewById(R.id.GRAPH)
        var i: Intent = Intent(this,graph_activity::class.java)
        i.putExtra("Function",number)
        startActivity(i)
    }
    fun numberEvent(view : View){
       var buclick:String = ""
       var buselect: Button = view as Button
        when(buselect.id){
            one.id->{buclick += "1"}
            two.id->{buclick += "2"}
            three.id->{buclick += "3"}
            four.id->{buclick += "4"}
            five.id->{buclick += "5"}
            six.id->{buclick += "6"}
            seven.id->{buclick += "7"}
            eight.id->{buclick += "8"}
            nine.id->{buclick += "9"}
            zero.id->{buclick += "0"}
            dot.id->{buclick += "."}
        }
        number = number.plus(buclick)
        valuein.setText(number)
    }
    fun operatorEvent(view: View){
       number = valuein.text.toString()
       var buSelect:Button = view as Button
       when(buSelect.id){
           multiply.id->{number += "*"}
           divide.id->{number += "/"}
           add.id->{number += "+"}
           subtract.id->{number += "-"}
           bracketclose.id->{number += ")"}
           bracketopen.id->{number += "("}
       }
        valuein.setText(number)
    }
    fun equalEvent(view : View){
        var userexp:String = valuein.getText().toString()
        var exp: Expression = Expression(userexp)
        var result:String = exp.calculate().toString()
        valuein.setText(result)
    }
    fun CEevent(view : View){
        valuein.setText("0")
        number =""
    }

    fun deleteevent(view: View) {
        var newnum:String = ""
//        var c:Char = number.toCharArray().get(number.length-1)
        var ein:TextView = findViewById(R.id.valuein)
        var string:String = ein.text.toString()
        if(string == ""){
            newnum = "0"
        }else if(string == "0"){
            newnum = "0"
        }
        else {
            newnum = number.substring(0, number.length - 1)
            number = newnum
        }
        valuein.setText(newnum)
    }
    fun complexevent(view :View){
        var complex:String = ""
        var buselect:Button = view as Button
        when(buselect.id){
            cos.id->{complex += "cos("}
            expo.id->{complex += "e^x"}
            sin.id->{complex += "sin("}
            tan.id->{complex += "tan("}
            xraisetoa.id->{complex += "^("}
            logbaseE.id-> {complex += "ln("}
            ex.id->{complex += "x"}
            exponential.id->{complex += "x^("}
//            mod.id->{complex += "|"}
        }
        number = number.plus(complex)
        valuein.setText(number)
    }
}