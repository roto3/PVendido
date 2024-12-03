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

        boton.setOnClickListener {
//            var nuevosval=(barajar(peces)).contentToString()
//            println(nuevosval)
//            texto.text=nuevosval
            //var descarte = arrayOf<Int>() //igual tiene que ser list o mutable list
            //var sobrante = arrayOf<Int>()

            var pecesbar = barajar(peces)
            var aparejosbar = barajar(aparejos)

            var mensaje = "tus cartas son: \n"

            var cosa = (txtjugadores.text).toString()
            var jugadores = cosa.toIntOrNull()
            if (jugadores == null || jugadores <2 || jugadores >6){
                Toast.makeText(this,"Introduce un número válido de jugadores (2-6)",Toast.LENGTH_SHORT).show()
            }else{
                var sobrante = pecesbar.slice((jugadores*4)..45)
                var cartasjugs = creamanos(jugadores,pecesbar)
                var cartasjug1 = (cartasjugs)[0]
                visualiza(cartasjug1,cartauna,cartados,cartatres,cartacuat)
                var cartasjug2 = (cartasjugs)[1]
                var cartastring1 = astring(cartasjug1,tipopeces)
                var cartastring2 = astring(cartasjug2,tipopeces)

                mensaje+=" +$cartastring1 \n"

                var cartasjug3 = (cartasjugs)[2]
                var cartasjug4 = (cartasjugs)[3]
                var cartasjug5 = (cartasjugs)[4]
                var cartasjug6 = (cartasjugs)[5]
                var cartastring3 = astring(cartasjug3,tipopeces)
                var cartastring4 = astring(cartasjug4,tipopeces)
                var cartastring5 = astring(cartasjug5,tipopeces)
                var cartastring6 = astring(cartasjug6,tipopeces)

                when (jugadores){
                    3->
                        mensaje+="y las de tus rivales son: \n -$cartastring2 \n" +
                                " -$cartastring3 "
                    4->
                        mensaje+="y las de tus rivales son: \n -$cartastring2 \n" +
                                " -$cartastring3 \n" +
                                " -$cartastring4 "
                    5->
                        mensaje+="y las de tus rivales son: \n -$cartastring2 \n" +
                                " -$cartastring3 \n" +
                                " -$cartastring4 \n" +
                                " -$cartastring5 "
                    6->
                        mensaje+="y las de tus rivales son: \n -$cartastring2 \n" +
                                " -$cartastring3 \n" +
                                " -$cartastring4 \n" +
                                " -$cartastring5 \n" +
                                " -$cartastring6"
                    else ->
                        mensaje+=" y las de tu rival son: \n -$cartastring2"
                }
                var cosa = sobrante.size
                var cosa2= astring(sobrante.toTypedArray(),tipopeces)
                mensaje+=" \n sobran $cosa cartas,  que son $sobrante"
                texto.text=mensaje
                txtsobrante.text="Montón: $cosa2"
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

    private fun barajar(cartas: Array<Int>): Array<Int> {
        var barajados = cartas
        barajados.shuffle()
        return barajados
    }

    private fun creamanos(jugadores: Int, pecesbar: Array<Int>): Array<Array<Int>> {
        var posicion = 0
        var jug1 = Array(4) { 0 }
        var jug2 = Array(4) { 0 }
        var jug3 = Array(4) { 0 }
        var jug4 = Array(4) { 0 }
        var jug5 = Array(4) { 0 }
        var jug6 = Array(4) { 0 }
        var pecesjugadores = arrayOf(jug1,jug2,jug3,jug4,jug5,jug6)
        for (i in pecesjugadores.indices){
            if (i<jugadores){ //dentro de cada posición, que sea el número de jugadores que hemos dicho
                for (j in pecesjugadores[i].indices){
                        pecesjugadores[i][j]=pecesbar[posicion]
                        posicion++
                }
            }
        }
        return pecesjugadores
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