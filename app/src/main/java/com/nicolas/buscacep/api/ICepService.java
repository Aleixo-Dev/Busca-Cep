package com.nicolas.buscacep.api;

import com.nicolas.buscacep.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICepService {

    @GET("{cep}/json/")
    Call<Cep> obterCep(@Path("cep") String cep);

}
