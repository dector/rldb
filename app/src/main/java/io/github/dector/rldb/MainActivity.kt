package io.github.dector.rldb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, root_container, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(ListController()))
    }

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }
}

class ListController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_list, container, false)
}

class ItemDetailsController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false)
}