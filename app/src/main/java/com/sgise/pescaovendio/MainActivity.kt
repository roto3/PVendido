package com.sgise.pescaovendio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val peces = arrayOf(0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,4,4,4,4,5,5,6,7,8,8,8,9,9,9,9,9,10,10)
        val tipopeces = arrayOf("morralla (cero)","sardinilla (uno)","salmonete (cinco)","mero (diez)","sardinillas x3 (tres)","sardinillas x7 (siete)","salmonetes x2 (diez)","atún (veintitrés)","jardinera (-cinco)","venenosa","eléctrica")
        val aparejos = arrayOf(0,0,1,1,1,2,2,2,2,3,3,3,3,3,3)
        val tipoapar = arrayOf("cambio red","guantes protección","bote huevas","caña ladrona")
        var boton = findViewById<Button>(R.id.btnrand)
        val texto = findViewById<TextView>(R.id.txtcosa)
        val txtsobrante = findViewById<TextView>(R.id.txtsobrante)
        var txtjugadores = findViewById<TextView>(R.id.txtjugadores)

        var cartauna= findViewById<ImageView>(R.id.imgcarta1)
        var cartados= findViewById<ImageView>(R.id.imgcarta2)
        var cartatres= findViewById<ImageView>(R.id.imgcarta3)
        var cartacuat= findViewById<ImageView>(R.id.imgcarta4)

        var aparejouno= findViewById<ImageView>(R.id.imgobj1)
        var aparejodos= findViewById<ImageView>(R.id.imgobj2)

        boton.setOnClickListener {
//            var nuevosval=(barajar(peces)).contentToString()
//            println(nuevosval)
//            texto.text=nuevosval
            //var descarte = arrayOf<Int>() //igual tiene que ser list o mutable list
            //var sobrante = arrayOf<Int>()

            var pecesbar = barajar(peces)
            var aparejosbar = barajar(aparejos)

            var mensaje = "tus cartas son: \n"

            var cosa = (txtjugadores.text).toString() //leemos num jugadores
            var jugadores = cosa.toIntOrNull()
            if (jugadores == null || jugadores <2 || jugadores >6){
                Toast.makeText(this,"Introduce un número válido de jugadores (2-6)",Toast.LENGTH_SHORT).show()
            }else{
                var sobrante = pecesbar.slice((jugadores*4)..45)
                var aparejosobrante = aparejosbar.slice((jugadores*2)..14)
                var cartasjugs = creamanos(jugadores,pecesbar)
                var aparejosjugs = creamanos(jugadores,aparejosbar)
                var cartasjug1 = (cartasjugs)[0]
                var aparejug1 = (aparejosjugs)[0]
                visualiza(cartasjug1,cartauna,cartados,cartatres,cartacuat)
                visuobj(aparejug1,aparejouno,aparejodos)
                var cartasjug2 = (cartasjugs)[1]
                var aparejug2 = (aparejosjugs)[1]
                var cartastring1 = astring(cartasjug1,tipopeces)
                var objstring1 = astring(aparejug1,tipoapar)
                var cartastring2 = astring(cartasjug2,tipopeces)
                var objstring2 = astring(aparejug2,tipoapar)

                mensaje+=" +$cartastring1 y $objstring1\n"

                var cartasjug3 = (cartasjugs)[2]
                var aparejug3 = (aparejosjugs)[2]
                var cartasjug4 = (cartasjugs)[3]
                var aparejug4 = (aparejosjugs)[3]
                var cartasjug5 = (cartasjugs)[4]
                var aparejug5 = (aparejosjugs)[4]
                var cartasjug6 = (cartasjugs)[5]
                var aparejug6 = (aparejosjugs)[5]
                var cartastring3 = astring(cartasjug3,tipopeces)
                var objstring3 = astring(aparejug3,tipoapar)
                var cartastring4 = astring(cartasjug4,tipopeces)
                var objstring4 = astring(aparejug4,tipoapar)
                var cartastring5 = astring(cartasjug5,tipopeces)
                var objstring5 = astring(aparejug5,tipoapar)
                var cartastring6 = astring(cartasjug6,tipopeces)
                var objstring6 = astring(aparejug6,tipoapar)

                when (jugadores){
                    3->
                        mensaje+="y las de tus rivales son: \n -$cartastring2 y $objstring2\n" +
                                " -$cartastring3 "
                    4->
                        mensaje+="y las de tus rivales son: \n -$cartastring2  y $objstring2\n" +
                                " -$cartastring3  y $objstring3 \n" +
                                " -$cartastring4  y $objstring4"
                    5->
                        mensaje+="y las de tus rivales son: \n -$cartastring2  y $objstring2\n" +
                                " -$cartastring3  y $objstring3 \n" +
                                " -$cartastring4  y $objstring4\n" +
                                " -$cartastring5  y $objstring5"
                    6->
                        mensaje+="y las de tus rivales son: \n -$cartastring2  y $objstring2\n" +
                                " -$cartastring3  y $objstring3\n" +
                                " -$cartastring4  y $objstring4\n" +
                                " -$cartastring5  y $objstring5\n" +
                                " -$cartastring6 y $objstring6"
                    else ->
                        mensaje+=" y las de tu rival son: \n -$cartastring2 y $objstring2"
                }
                var cosa = sobrante.size
                var cosa2= astring(sobrante.toTypedArray(),tipopeces)
                var cosa3= astring(aparejosobrante.toTypedArray(),tipoapar)
                mensaje+=" \n sobran $cosa cartas,  que son $sobrante"
                texto.text=mensaje
                txtsobrante.text="Montón: $cosa2 y $cosa3"
            }
        }
    }

    private fun visualiza(
        cartasjug1: Array<Int>,
        cartauna: ImageView,
        cartados: ImageView,
        cartatres: ImageView,
        cartacuat: ImageView
    ) {
        for(i in cartasjug1.indices){
            when (i){
                0-> cartauna.setImageResource(sacafoto(cartasjug1[i]))
                1-> cartados.setImageResource(sacafoto(cartasjug1[i]))
                2-> cartatres.setImageResource(sacafoto(cartasjug1[i]))
                else -> {
                    cartacuat.setImageResource(sacafoto(cartasjug1[i]))
                }
            }
        }
    }
    private fun visuobj(
        aparejug1: Array<Int>,
        aparejouno: ImageView,
        aparejodos: ImageView
    ){
        for(i in aparejug1.indices){
            when (i){
                0-> aparejouno.setImageResource(sacaobj(aparejug1[i]))
                1-> aparejodos.setImageResource(sacaobj(aparejug1[i]))
                else -> {
                    //aparejodos.setImageResource(sacaobj(aparejug1[i]))
                }
            }
        }
    }

    private fun sacafoto(cartajug: Int): Int{
        var carta = 0
        when (cartajug){
            1-> carta = R.drawable.uno
            2-> carta = R.drawable.cicno
            3-> carta = R.drawable.diez
            4-> carta = R.drawable.tres
            5-> carta = R.drawable.siete
            6-> carta = R.drawable.doblecinco
            7-> carta = R.drawable.ventitres
            8-> carta = R.drawable.jardinera
            9-> carta = R.drawable.normal
            10-> carta = R.drawable.electrica
            else ->{
                carta = R.drawable.cero
            }
        }
        return carta
    }
    private fun sacaobj(i: Int):Int {
        var carta = 0
        when (i){
            1-> carta = R.drawable.proteccion
            2-> carta = R.drawable.pordos
            3-> carta = R.drawable.cania
            else ->{
                carta = R.drawable.intercambio
            }            }
        return carta
    }

    private fun barajar(cartas: Array<Int>): Array<Int> {
        var barajados = cartas
        barajados.shuffle()
        return barajados
    }

    private fun creamanos(jugadores: Int, pecesuobjbar: Array<Int>): Array<Array<Int>> { //, aparejosbar : Array<Int>
        var posicion = 0
        //var posicionobj = 0
        var jug1 = Array(4) { 0 }
        var obj1 = Array(2) { 0 }
        var jug2 = Array(4) { 0 }
        var obj2 = Array(2) { 0 }
        var jug3 = Array(4) { 0 }
        var obj3 = Array(2) { 0 }
        var jug4 = Array(4) { 0 }
        var obj4 = Array(2) { 0 }
        var jug5 = Array(4) { 0 }
        var obj5 = Array(2) { 0 }
        var jug6 = Array(4) { 0 }
        var obj6 = Array(2) { 0 }
        var pecesjugadores = arrayOf(jug1,jug2,jug3,jug4,jug5,jug6)
        var objjugadores = arrayOf(obj1,obj2,obj3,obj4,obj5,obj6)

        var comprueba=objjugadores

        if (pecesuobjbar.size >16){
            comprueba=pecesjugadores
        }
        for (i in comprueba.indices){
            if (i<jugadores){ //dentro de cada posición, que sea el número de jugadores que hemos dicho
                for (j in comprueba[i].indices){
                        comprueba[i][j]=pecesuobjbar[posicion]
                        posicion++
                }
            }
        }
        return comprueba
    }

    private fun astring(cartasjug: Array<Int>, tipopeces: Array<String>): String {
        var cartastring=""

        for(i in cartasjug.indices){
            cartastring += tipopeces[cartasjug[i]]
            cartastring += ", "
        }
        return cartastring
    }

}