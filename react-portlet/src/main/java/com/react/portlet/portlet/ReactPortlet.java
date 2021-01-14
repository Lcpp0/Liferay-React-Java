package com.react.portlet.portlet;

import com.react.portlet.constants.ReactPortletKeys;
import com.consulta.general.api.api.ConsultaGeneralApi;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author lprada
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=React",
		"com.liferay.portlet.header-portlet-css=/css/index.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ReactPortletKeys.React,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ReactPortlet extends MVCPortlet {
	
	Log LOGGER = LogFactoryUtil.getLog(ReactPortlet.class);

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		System.out.println("Portlet React");
		System.out.println("Api "+api);
		
		JSONObject planetList = null;
		try {
			planetList = api.getPlanets();
		} catch (PortalException e) {
			e.printStackTrace();
		}
		
		
		JSONArray resultsPlanetList = planetList.getJSONArray("results");
//		System.out.println("Planet List "+planetList);
		System.out.println("Planet List results "+resultsPlanetList);

		renderRequest.setAttribute(
			"mainRequire",
			_npmResolver.resolveModuleName("react-portlet") + " as main");
		
		renderRequest.setAttribute("resultsPlanetList", resultsPlanetList);

		super.doView(renderRequest, renderResponse);
	}
	
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		
		System.out.println("*****¨*¨*¨**¨**¨*¨*¨**¨*INFO SERVER RESOURCE****************************************************************");
		
		if(resourceRequest.getResourceID().equals("resultsPlanetListURL")) {
			
			LOGGER.info("Server resource ");
		}
		
	}


	@Reference
	private NPMResolver _npmResolver;
	
	@Reference
	private ConsultaGeneralApi api;

}