package com.example.cryptoapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.MonedaViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentDetalleBinding
import com.example.cryptoapp.model.Moneda
import com.squareup.picasso.Picasso


class DetalleFragment : Fragment() {

    private var b: FragmentDetalleBinding? = null
    private val binding get() = b!!
    private lateinit var vmodel: MonedaViewModel

       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentDetalleBinding.inflate(inflater, container, false)

           vmodel = ViewModelProvider(this).get(MonedaViewModel::class.java)

           //recibir lo que llega de la lista de las noticias

           var moneda = arguments?.getSerializable("moneda") as Moneda

           //asignar lo que recibo con el binding a cada vista

           with(binding) {
               tvNameMoneda.text = moneda.name
               tvSymbolDetalle.text=moneda.symbol
               rank.text = moneda.rank
               status.text = moneda.status
               price.text = moneda.price
               date.text = moneda.price_date

               Picasso.get().load(moneda.logo_url).fit().centerCrop()
                   .placeholder(R.drawable.user_placeholder)
                   .error(R.drawable.user_placeholder_error)
                   .into(binding.ivDetalle)
           }

           // De imagen a navegador para ver la noticia completa

           binding.ivDetalle.setOnClickListener(View.OnClickListener {
               val webIntent: Intent = Uri.parse("${moneda.url}").let { webpage ->
                   Intent(Intent.ACTION_VIEW, webpage)

               }

               startActivity(webIntent)

           })
               binding.btnGetInfo.setOnClickListener() {
               val compartirIntent = Intent()
                   compartirIntent.type = "text/plain"
                   compartirIntent.action = Intent.ACTION_SEND
                   compartirIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("jan@example.com")) // recipients
                   compartirIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                   compartirIntent.putExtra(Intent.EXTRA_TEXT,"Hola " +
                           "Quisiera pedir información sobre esta moneda " + "\n ${moneda.url},"
                           + "me gustaría que me contactaran a este correo o al " +
                           "siguiente número _________, Quedo atento."
               )
                   compartirIntent.type = "text/plain"
               startActivity(compartirIntent)

           }
       return binding.root
    }
}