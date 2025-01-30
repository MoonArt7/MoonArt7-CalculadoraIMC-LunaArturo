package luna.arturo.asignacion4_calculadoraimc_luna

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Main2Activity : AppCompatActivity() {

    private lateinit var editTxtPeso: EditText
    private lateinit var editTxtEstatura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var txtIcm: TextView
    private lateinit var txtRange: TextView

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        editTxtPeso = findViewById(R.id.editTxtNPeso)
        editTxtEstatura = findViewById(R.id.editNEstatura)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtIcm = findViewById(R.id.txtImc)
        txtRange = findViewById(R.id.txtRage)

        btnCalcular.setOnClickListener {
            calcularIMC()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularIMC() {
        val peso = editTxtPeso.text.toString().toFloatOrNull()
        val estatura = editTxtEstatura.text.toString().toFloatOrNull()

        if (peso != null && estatura != null && estatura > 0) {
            val imc = peso / (estatura * estatura)
            val resultadoIMC = "Tu IMC es: %.2f".format(imc)
            txtIcm.text = resultadoIMC


            val rango = when {
                imc < 18.5 -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorBrown))
                    "Bajo peso"
                }
                imc in 18.5..24.9 -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorGreen))
                    "Normal"
                }
                imc in 25.0..29.9 -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorOrange))
                    "Sobrepeso"
                }
                imc in 30.0..34.9 -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorRed))
                    "Obesidad grado 1"
                }
                imc in 35.0..39.9 -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorRed))
                    "Obesidad grado 2"
                }
                else -> {
                    txtRange.setBackgroundColor(resources.getColor(R.color.colorRed))
                    "Obesidad grado 3"
                }
            }

            txtRange.text = rango

            Toast.makeText(this, resultadoIMC, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Ingresa valores válidos", Toast.LENGTH_SHORT).show()
        }
    }
}