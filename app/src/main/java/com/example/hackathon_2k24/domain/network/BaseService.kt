package com.example.hackathon_2k24.domain.network

import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Salman Saifi on 27/02/24.
 * Contact at zach.salmansaifi@gmail.com.
 */

interface BaseService {
    @GET(ServiceMethods.PRODUCTS)
    suspend fun products(
    ): Response<List<ResponseBean>?>

}