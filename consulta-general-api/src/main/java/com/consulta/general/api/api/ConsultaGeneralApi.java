package com.consulta.general.api.api;

import java.io.IOException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author lprada
 */
public interface ConsultaGeneralApi {

    public JSONObject getPlanets() throws IOException, PortalException;
}