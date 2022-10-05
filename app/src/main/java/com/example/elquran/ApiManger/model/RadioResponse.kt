package com.example.elquran.ApiManger.model

import com.google.gson.annotations.SerializedName

data class RadioResponse(

	@field:SerializedName("radios")
	val radios: List<RadiosChannels?>? = null
)

data class RadiosChannels(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("radio_url")
	val radioUrl: String? = null
)
