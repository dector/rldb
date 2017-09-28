package io.github.dector.rldb.details.view.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R

class ItemDetailsController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false)
}