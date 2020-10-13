package com.example.soal1.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.soal1.BaseActivity
import com.example.soal1.R
import com.example.soal1.global.Preferences
import com.example.soal1.ui.main.MainViewModelFactory
import com.example.soal1.viewmodel.main.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.reflect.Type

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var logoutId = 0
    lateinit var menu : Menu

    private lateinit var mainViewModel : MainViewModel
    var listener = object : Preferences.URLBuilder.GenericResponseListener {
        override fun onSuccessResponse(str: String) {
            var jsonArray = JSONArray()
            val type: Type = object : TypeToken<List<String>>() {}.type
            try{
                var menuList : List<String> =  Gson().fromJson(str, type)


                GlobalScope.launch(Dispatchers.Main) {
                    menu = nav_view.menu
                    menuList.forEach {
                        menu.add(Menu.NONE, logoutId, Menu.NONE, it)
                    }

                    nav_view.setNavigationItemSelectedListener(this@MainActivity)
                }
            }catch (exception: Exception){

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProviders.of(
            this, MainViewModelFactory(
                preferences.getIp(),
                listener
            )
        ).get(MainViewModel::class.java)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
            mainViewModel.getMenu()

        }, 1000)


    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(nav_view == null) return false
        startActivity(Intent(this, LoginActivity::class.java))
        return true
    }
}