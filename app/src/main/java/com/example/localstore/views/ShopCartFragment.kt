package com.example.localstore.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import com.example.localstore.R
import com.example.localstore.adapter.ProductAdapter
import com.example.localstore.adapter.ShopCartAdapter
import com.example.localstore.model.Product
import com.example.localstore.model.ShopCart
import kotlinx.android.synthetic.main.fragment_bill_detail.*
import kotlinx.android.synthetic.main.fragment_shop_cart.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ShopCartFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ShopCartFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ShopCartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onStart() {
        super.onStart()
        var mDividerItemDecoration = DividerItemDecoration(
            rv_shop_cart.getContext(),
            LinearLayout.VERTICAL
        )
        rv_shop_cart.addItemDecoration(mDividerItemDecoration)
        iniciarRecyclerVirew(ShopCart.shoppingCart, this, rv_shop_cart)
        updatePrice()
        btn_buy.setOnClickListener {
            ShopCart.buy()
            //Toast.makeText(this.context, "Compra realizada con 'exito", Toast.LENGTH_LONG).show()
            //activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, ProductsFragment())?.addToBackStack( "tag" )?.commit()
        }
    }

    fun updatePrice(){
        tv_shop_cart_total.text = "Total $ ${ShopCart.calculatePrice()}"
    }

    fun iniciarRecyclerVirew (lista : ArrayList<Product>, actividad: ShopCartFragment, recyclerView: RecyclerView){
        val adaptadorProducto = ShopCartAdapter(
            lista,
            actividad,
            recyclerView
        )
        recyclerView.adapter = adaptadorProducto
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(actividad.context)

        adaptadorProducto.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_cart, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShopCartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShopCartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
