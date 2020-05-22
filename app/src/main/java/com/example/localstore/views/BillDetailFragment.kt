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

import com.example.localstore.R
import com.example.localstore.adapter.BillDetailAdapter
import com.example.localstore.adapter.ShopCartAdapter
import com.example.localstore.model.Bill
import com.example.localstore.model.Client
import com.example.localstore.model.Product
import kotlinx.android.synthetic.main.fragment_bill_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BillDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BillDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BillDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var idBill : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idBill = it.getInt("id")
        }
        Log.i("fragmentosxd","$idBill")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null){
            idBill = arguments!!.getInt("id")
        }
    }

    override fun onStart() {
        super.onStart()
        Bill.handleProducts(idBill)
        var mDividerItemDecoration = DividerItemDecoration(
            rv_bill_detail.getContext(),
            LinearLayout.VERTICAL
        )
        rv_bill_detail.addItemDecoration(mDividerItemDecoration)
        var actualBill = Bill.getBill(idBill)
        tv_bill_id.text = "${tv_bill_id.text} ${actualBill.id}"
        tv_client_name.text = "${tv_client_name.text}: ${Client.currentClient!!.name}"
        tv_client_last_name.text = "${tv_client_last_name.text}: ${Client.currentClient!!.lastName}"
        tv_client_phone.text = "${tv_client_phone.text}: ${Client.currentClient!!.phone}"
        tv_bill_date.text = "${tv_bill_date.text}: ${actualBill.date}"
        tv_total_bill.text = "${tv_total_bill.text} ${actualBill.totalCost}"
        iniciarRecyclerVirew(Bill.productsList, this, rv_bill_detail)
    }

    fun iniciarRecyclerVirew (lista : ArrayList<Product>, actividad: BillDetailFragment, recyclerView: RecyclerView){
        val adaptadorProducto = BillDetailAdapter(
            lista,
            actividad,
            recyclerView
        )
        recyclerView.adapter = adaptadorProducto
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(actividad.context)

        adaptadorProducto.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
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
           // throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
         * @return A new instance of fragment BillDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BillDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
