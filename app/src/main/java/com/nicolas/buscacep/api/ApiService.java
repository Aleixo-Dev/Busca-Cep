package com.nicolas.buscacep.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static ICepService iCepService;

    public static ICepService getiCepService() {

        if (iCepService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://viacep.com.br/ws/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            iCepService = retrofit.create(ICepService.class);
        }
        return iCepService;
    }
}
