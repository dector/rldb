package io.github.dector.rldb.favourites.view.controllers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R


class FavouritesController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup)
            = inflater.inflate(R.layout.controller_favourites, container, false)
}