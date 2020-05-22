package com.example.localstore.views

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.example.localstore.R
import com.example.localstore.model.Bill
import com.example.localstore.model.Product

class HomeActivity : AppCompatActivity(),
    HomeFragment.OnFragmentInteractionListener,
    ProductsFragment.OnFragmentInteractionListener,
    ShopCartFragment.OnFragmentInteractionListener,
    BillsFragment.OnFragmentInteractionListener
{
    override fun onFragmentInteraction(uri: Uri) {
    }

    private lateinit var drawer : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.draw_layout)

        var navigationView = findViewById<NavigationView>(R.id.nav_view)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).addToBackStack( "tag" ).commit()
                }
                R.id.item_products -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProductsFragment()).addToBackStack( "tag" ).commit()
                }
                R.id.item_cart -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ShopCartFragment()).addToBackStack( "tag" ).commit()
                }
                R.id.item_bills -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BillsFragment()).addToBackStack( "tag" ).commit()
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }

        var toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.item_home)
        }

        Product.adapter.getAll()
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

}
