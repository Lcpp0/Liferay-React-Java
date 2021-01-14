package com.consulta.general.api.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.consulta.general.api.api.ConsultaGeneralApi;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import utils.api.api.UtilsApi;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		immediate = true,
		service = ConsultaGeneralApi.class
)
public class ConsultaGeneralApiImpl implements ConsultaGeneralApi {

    @Override
    public JSONObject getPlanets() throws IOException, PortalException {
        Map<String, String> parametros = new HashMap<>();
        JSONObject response = JSONFactoryUtil.createJSONObject(api.getMethod("https://swapi.dev/api/planets", parametros));
//        System.out.println("Response "+ response);
        return response;
//        return null;
    }

    @Reference
    private UtilsApi api;
    
}
