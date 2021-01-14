package utils.api.api;

import java.io.IOException;
import java.util.Map;

/**
 * @author lprada
 */
public interface UtilsApi {
	public String getMethod(String url, Map<String, String> parametros) throws IOException;
    public String postMethod(String url, String body) throws IOException;
    public String putMethod(String url, String body) throws IOException;
}