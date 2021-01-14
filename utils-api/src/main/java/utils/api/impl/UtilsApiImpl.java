package utils.api.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;

import utils.api.api.UtilsApi;

@Component(
		immediate = true,
		service = UtilsApi.class
)
public class UtilsApiImpl implements UtilsApi{

	@Override
    public String getMethod(String url, Map<String, String> parametros) throws IOException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : parametros.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder().setPath(url).setParameters(params);
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader("Content-type", "application/json; charset=utf-8");

            ResponseHandler<String> responseHandler = response ->{
                int status = response.getStatusLine().getStatusCode();
                if(status >= 200 && status < 300){
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                }else{
                    throw new ClientProtocolException("Unexpected response status: "+ status);
                }
            };
            String responseBody = httpClient.execute(httpGet, responseHandler);
            return responseBody;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String postMethod(String url, String body) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");

            StringEntity stringEntity = new StringEntity(body);
            httpPost.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = response ->{
                int status = response.getStatusLine().getStatusCode();
                if(status >= 200 && status < 300){
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                }else{
                    throw new ClientProtocolException("Unexpected response status: "+ status);
                }
            };
            String responseBody = httpClient.execute(httpPost, responseHandler);
            System.out.println("----------------------");
            System.out.println(responseBody);
            return responseBody;
        } 
    }

    @Override
	public String putMethod(String url, String body) throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder().setPath(url);
            URI uri = builder.build();
            HttpPut httpPut = new HttpPut(uri);
            httpPut.addHeader("Content-type", "application/json; charset=utf-8");

            StringEntity stringEntity = new StringEntity(body);
            httpPut.setEntity(stringEntity);

            ResponseHandler<String> responseHandler = response ->{
                int status = response.getStatusLine().getStatusCode();
                if(status >= 200 && status < 300){
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                }else{
                    throw new ClientProtocolException("Unexpected response status: "+ status);
                }
            };
            String responseBody = httpClient.execute(httpPut, responseHandler);
            System.out.println("----------------------");
            System.out.println(responseBody);
            return responseBody;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
	}
}
