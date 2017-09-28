package io.github.dector.rldb.details.view.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.github.dector.rldb.R


typealias Uuid = String

class ItemDetailsController(itemUuid: Uuid) : Controller() {

    constructor(state: Bundle) : this(state.getString("uuid", "") )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup) =
            inflater.inflate(R.layout.controller_item_details, container, false)
}